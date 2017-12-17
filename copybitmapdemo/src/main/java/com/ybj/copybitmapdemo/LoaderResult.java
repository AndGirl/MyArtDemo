package com.ybj.copybitmapdemo;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by 杨阳洋 on 2017/12/8.
 * Bean类
 */

public class LoaderResult {

    public ImageView imageView;
    public String uri;
    public Bitmap bitmap;

    public LoaderResult(ImageView imageView, String uri, Bitmap bitmap) {
        this.imageView = imageView;
        this.uri = uri;
        this.bitmap = bitmap;
    }

}
