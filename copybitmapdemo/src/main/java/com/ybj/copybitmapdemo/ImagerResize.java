package com.ybj.copybitmapdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by 杨阳洋 on 2017/12/7.
 * 主要实现图片压缩的功能
 * 图片的来源
 * 采样率的处理
 * 图片重新发布
 */

public class ImagerResize {

    public ImagerResize() {
    }

    /**
     * 从资源路径加载图片
     * @param res 资源路径
     * @param resId 资源ID
     * @param reqWidth 实际图片宽度
     * @param reqHeight 实际图片高度
     * @return 图片资源
     */
    public static Bitmap getBitmapFromResources(Resources res,int resId,long reqWidth,long reqHeight){

        //获取到图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置inJustDecodeBounds图片大小可以改变
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);

        //计算图片大小
        int calculateSample = calculateSample(options, reqWidth, reqHeight);
        options.inSampleSize = calculateSample;

        //重新加载图片
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(res,resId,options);

        return bitmap;

    }

    //这里简单对FileDescriptor做一个描述：表示文件描述符。当FileDescriptor表示某文件时，我们可以通俗的将FileDescriptor看成是该文件。但是，我们不能直接通过FileDescriptor对该文件进行操作；若需要通过FileDescriptor对该文件进行操作，则需要新创建FileDescriptor对应的FileOutputStream，再对文件进行操作。
    public static Bitmap getBitmapFromFile(FileDescriptor file, long reqWidth, long reqHeight){
        //原理同从资源获取图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(file, null, options);

        int calculateSample = calculateSample(options, reqWidth, reqHeight);
        options.inSampleSize = calculateSample;

        //重新加载图片
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFileDescriptor(file,null,options);

        return bitmap;

    }

    /**
     * 计算采样率
     * @param options 设置图片参数
     * @param reqWidth 实际图片宽度
     * @param reqHeight 实际图片高度
     * @return 采样率（int）
     */
    private static int calculateSample(BitmapFactory.Options options, long reqWidth, long reqHeight) {
        //首先提供一个默认采样率
        if(reqHeight < 0 || reqWidth < 0) {
            return 1;
        }

        //图片真实宽高
        if(options != null) {
            int realHeight = options.outHeight;
            int realWidth = options.outWidth;

            //提供一个默认采样率
            int inSamples = 1;

            if(realHeight > reqHeight && realWidth > reqWidth) {
                final int halfHeight = realHeight / 2;
                final int halfWidth = realWidth / 2;

                while ((halfHeight/inSamples) >= reqHeight
                        && (halfWidth/inSamples) >= reqWidth){
                    inSamples *= 2;
                }
                return inSamples;
            }

        }else{
            return 1;
        }

        return 1;

    }

}
