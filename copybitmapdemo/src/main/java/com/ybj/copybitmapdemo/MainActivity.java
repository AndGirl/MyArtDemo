package com.ybj.copybitmapdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> photoList = new ArrayList<String>();
    private int mScreenWidth;
    private int mSpace;
    private int mMImageWidth = 0;
    private boolean mWifi = false;
    private boolean mCanGetBitmapFromNetWork = false;
    private ImagerLoader mImagerLoader;
    private GridView mImageGrid;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        mImagerLoader = ImagerLoader.build(this);

    }

    private void initView() {
        mImageGrid = (GridView) findViewById(R.id.gridView1);
        mAdapter = new ImageAdapter(this);
        mImageGrid.setAdapter(mAdapter);

        if(!mWifi) {
            new AlertDialog.Builder(this)
                    .setMessage("初次使用会从网络下载大概5MB的图片，确认要下载吗？")
                    .setTitle("uart-温馨提醒")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mCanGetBitmapFromNetWork = true;
                            mAdapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("否",null)
                    .show();
        }

    }

    private class ImageAdapter extends BaseAdapter{
        private LayoutInflater mLayoutInflater;

        public ImageAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return photoList.size();
        }

        @Override
        public String getItem(int position) {
            return photoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.image_list_item,parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            mImagerLoader.bindAsynchronizationBitmap(photoList.get(position),holder.imageView,mMImageWidth,mMImageWidth);
            return convertView;
        }

        private class ViewHolder {
            public ImageView imageView;

            public ViewHolder(View view) {

                imageView = (ImageView)view.findViewById(R.id.image);

            }
        }

    }

    private void initData() {
        String[] imageUrls = {
                "http://b.hiphotos.baidu.com/zhidao/pic/item/a6efce1b9d16fdfafee0cfb5b68f8c5495ee7bd8.jpg",
                "http://pic47.nipic.com/20140830/7487939_180041822000_2.jpg",
                "http://pic41.nipic.com/20140518/4135003_102912523000_2.jpg",
                "http://img2.imgtn.bdimg.com/it/u=1133260524,1171054226&fm=21&gp=0.jpg",
                "http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg",
                "http://pic42.nipic.com/20140618/9448607_210533564001_2.jpg",
                "http://pic10.nipic.com/20101027/3578782_201643041706_2.jpg",
                "http://img2.3lian.com/2014/c7/51/d/26.jpg",
                "http://img3.3lian.com/2013/c1/34/d/93.jpg",
                "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
                "http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1210/17/c1/spcgroup/14468225_1350443478079_1680x1050.jpg",
                "http://pic41.nipic.com/20140518/4135003_102025858000_2.jpg",
                "http://pic.58pic.com/58pic/13/00/22/32M58PICV6U.jpg",
                "http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=429e7b1b92ef76c6d087f32fa826d1cc/7acb0a46f21fbe09cc206a2e69600c338744ad8a.jpg",
                "http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg",
                "http://photo.loveyd.com/uploads/allimg/080618/1110324.jpg",
                "http://img4.duitang.com/uploads/item/201404/17/20140417105820_GuEHe.thumb.700_0.jpeg",
                "http://img4.duitang.com/uploads/item/201404/17/20140417105856_LTayu.thumb.700_0.jpeg",
                "http://img04.tooopen.com/images/20130723/tooopen_20530699.jpg",
                "http://pic.dbw.cn/0/01/33/59/1335968_847719.jpg",
                "http://a.hiphotos.baidu.com/image/pic/item/a8773912b31bb051a862339c337adab44bede0c4.jpg",
                "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
                "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
                "http://pic.58pic.com/58pic/11/25/04/91v58PIC6Xy.jpg",
                "http://img3.3lian.com/2013/c2/32/d/101.jpg",
                "http://pic25.nipic.com/20121210/7447430_172514301000_2.jpg",
                "http://img02.tooopen.com/images/20140320/sy_57121781945.jpg",
                "http://static.fdc.com.cn/avatar/sns/1486263782969.png",
                "http://static.fdc.com.cn/avatar/sns/1485055822651.png",
                "http://static.fdc.com.cn/avatar/sns/1486194909983.png",
                "http://static.fdc.com.cn/avatar/sns/1486194996586.png",
                "http://static.fdc.com.cn/avatar/sns/1486195059137.png",
                "http://static.fdc.com.cn/avatar/sns/1486173497249.png",
                "http://static.fdc.com.cn/avatar/sns/1486173526402.png",
                "http://static.fdc.com.cn/avatar/sns/1486173639603.png",
                "http://static.fdc.com.cn/avatar/sns/1486172566083.png",
        };

        for (String imageUrl : imageUrls){
            photoList.add(imageUrl);
        }
        mScreenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        mSpace = (int) MyUtils.dp2px(this, 20f);
        mMImageWidth = (mScreenWidth - mSpace) / 3;
        mWifi = MyUtils.isWifi(this);
        if(mWifi) {
            mCanGetBitmapFromNetWork = true;
        }

    }
}
