package com.ybj.mesvgdemo;

import android.app.Application;

import com.mikepenz.iconics.Iconics;

/**
 * Created by 杨阳洋 on 2017/12/20.
 */

public class UartApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //only required if you add a custom or generic font on your own
        Iconics.init(getApplicationContext());

        //register custom fonts like this (or also provide a font definition file)
        Iconics.registerFont(new CustomFont());

    }
}
