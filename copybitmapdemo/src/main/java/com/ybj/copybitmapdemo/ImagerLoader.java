package com.ybj.copybitmapdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 杨阳洋 on 2017/12/7.
 * 实现图片的三级缓存，图片的同步异步加载
 */

public class ImagerLoader {

    private static final String TAG = "ImagerLoader";

    //最大缓存数
    private static final int MAX_CACHE_SIZE = 1024 * 1024 * 50;

    private Context mContext;

    //内存缓存
    private LruCache<String, Bitmap> mLruCache;

    //磁盘缓存
    private DiskLruCache mDiskLruCache;

    //IO流大小
    private static final int IO_BUFFER_SIZE = 8 * 1024;

    private static final int DISK_CACHE_INDEX = 0;

    private static final int TAG_KEY_URI = R.id.imageloader_uri;

    //CPU核数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    //核心线程数
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;

    //最大线程数CPU_COUNT * 2 + 1
    private static final int MAXIMUN_POOL_SIZE = CPU_COUNT * 2 + 1;

    //超时时长10s
    private static final long KEEP_ALIVE = 10L;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "ImageLoader#" + mCount.getAndIncrement());
        }
    };

    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAXIMUN_POOL_SIZE,
            KEEP_ALIVE, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), sThreadFactory);

    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {

            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            imageView.setImageBitmap(result.bitmap);

        }
    };

    public static final int MESSAGE_POST_RESULT = 1;

    public ImagerLoader(Context context) {

        mContext = context.getApplicationContext();

        init();

    }

    //初始化内存缓存和磁盘缓存
    private void init() {

        //初始化磁盘缓存

        //当前进程可用内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //内存缓存容量为当前进程可用内存的1/8
        int cacheSize = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                //sizeOf方法则完成了Bitmap对象的大小计算
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };

        //磁盘缓存初始化
        //创建路径
        File diskCacheFile = getDiskFileCachePath(mContext, "bitmap");
        if (!diskCacheFile.exists()) {
            diskCacheFile.mkdirs();
        }
        //校验磁盘空间是否充足
        if (verifyDiskMemory(diskCacheFile) > MAX_CACHE_SIZE) {
            try {
                mDiskLruCache = DiskLruCache.open(diskCacheFile, 1, 1, MAX_CACHE_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 网络获取图片
     *
     * @param url       图片资源
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap loadBitmapFromHttp(String url, long reqWidth, long reqHeight) {

        //首先判断该方法不是在主线程中运行
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.e(TAG, "不能在主线程中进行UI的操作");
            return null;
        }

        //磁盘缓存是否初始化
        if (mDiskLruCache == null) {
            return null;
        }

        String urlBitmap = hashKeyFormUrl(url);
        try {
            //Editor提供了commit和abort方法来提交和撤销对文件系统的写操作
            DiskLruCache.Editor editor = mDiskLruCache.edit(urlBitmap);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
                if (downloadUrlToStream(url, outputStream)) {
                    editor.commit();
                } else {
                    editor.abort();
                }
                mDiskLruCache.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadBitmapFromDiskCache(url, reqWidth, reqHeight);
    }

    /**
     * 获取DiskCache的图片
     *
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap loadBitmapFromDiskCache(String url, long reqWidth, long reqHeight) {
        //首先判断该方法不是在主线程中运行
        if (Looper.getMainLooper() == Looper.myLooper()) {
            Log.e(TAG, "不能在主线程中进行UI的操作");
            return null;
        }

        //磁盘缓存是否初始化
        if (mDiskLruCache == null) {
            return null;
        }
        Bitmap bitmap = null;
        String key = hashKeyFormUrl(url);
        try {
            //磁盘缓存的读取通过SnapShot来完成，得到对应的FileInputStream，但是它无法进行压缩，需要通过FileDescriptor来加载压缩图片
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot != null) {
                FileInputStream inputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
                FileDescriptor fd = inputStream.getFD();
                bitmap = ImagerResize.getBitmapFromFile(fd, reqWidth, reqHeight);
                if (bitmap != null) {
                    //加载到内存
                    addBitmapToLruCache(key, bitmap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 网络下载图片
     *
     * @param url          url
     * @param outputStream
     * @return
     */
    private boolean downloadUrlToStream(String url, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            URL bitmapUrl = new URL(url);
            urlConnection = (HttpURLConnection) bitmapUrl.openConnection();
            bis = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            bos = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);
            int length;
            while ((length = bis.read()) != -1) {
                bos.write(length);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            MyUtils.close(bis);
            MyUtils.close(bos);
        }
        return false;
    }

    /**
     * 对url路径做一个MD5加密，防止特殊字符影响其加密过程
     *
     * @param url
     * @return
     */
    private String hashKeyFormUrl(String url) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }

    /**
     * 十六进制转换
     *
     * @param bytes
     * @return
     */
    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private long verifyDiskMemory(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        }
        StatFs statFs = new StatFs(path.getPath());
        return statFs.getBlockCountLong() * statFs.getAvailableBlocksLong();
    }

    /**
     * 创建存储路劲
     *
     * @param context
     * @param uniqueName
     */
    private File getDiskFileCachePath(Context context, String uniqueName) {
        //判断是否有挂载SD
        boolean externalStorageValid = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        String cachePath;
        if (externalStorageValid) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 添加内存缓存图片
     *
     * @param key    url地址
     * @param bitmap 图片
     */
    private void addBitmapToLruCache(String key, Bitmap bitmap) {
        if (getBitmapLruCache(key) != null) {
            mLruCache.put(key, bitmap);
        }
    }

    /**
     * 获取图片紫资源
     *
     * @param key url资源
     * @return 图片
     */
    private Bitmap getBitmapLruCache(String key) {
        return mLruCache.get(key);
    }

    /**
     * 同步加载图片
     *
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap loadSynchronizationBitmap(String url, long reqWidth, long reqHeight) {
        Bitmap bitmap = getBitmapLruCache(hashKeyFormUrl(url));
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = loadBitmapFromDiskCache(url, reqWidth, reqHeight);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = loadBitmapFromHttp(url, reqWidth, reqHeight);
        if (bitmap == null) {
            bitmap = downloadBitmapFromUrl(url);
        }
        return bitmap;
    }

    /**
     * 下载图片资源从URL
     *
     * @param urlString
     * @return
     */
    private Bitmap downloadBitmapFromUrl(String urlString) {
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;

        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(),
                    IO_BUFFER_SIZE);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (final IOException e) {
            Log.e(TAG, "Error in downloadBitmap: " + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            MyUtils.close(in);
        }
        return bitmap;
    }

    /**
     * 异步记载图片
     * 采用线程池和Handler来实现。主要是为了避免频繁的上下滑动产生大量的线程，这样不利于整体效率的提升
     *
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public void bindAsynchronizationBitmap(final String url, final ImageView imagerView, final long reqWidth, final long reqHeight) {

        final Bitmap bitmapLruCache = getBitmapLruCache(hashKeyFormUrl(url));
        if (bitmapLruCache != null) {
            imagerView.setImageBitmap(bitmapLruCache);
            return;
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadSynchronizationBitmap(url, reqWidth, reqHeight);
                if (bitmap != null) {
                    LoaderResult loaderResult = new LoaderResult(imagerView, url, bitmap);
                    Message message = handler.obtainMessage(MESSAGE_POST_RESULT, loaderResult);
                    handler.sendMessage(message);
                }
            }
        };

        THREAD_POOL_EXECUTOR.execute(runnable);

    }

    /**
     * build a new instance of ImageLoader
     *
     * @param context
     * @return a new instance of ImageLoader
     */
    public static ImagerLoader build(Context context) {
        return new ImagerLoader(context);
    }

}
