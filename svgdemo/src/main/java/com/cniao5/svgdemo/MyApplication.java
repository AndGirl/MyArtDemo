package com.cniao5.svgdemo;

import android.app.Application;

import com.cniao5.svgdemo.typeface.Cniao5Font;
import com.mikepenz.iconics.Iconics;

/**
 * Created by Ivan on 2016/12/14.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        //only required if you add a custom or generic font on your own
        Iconics.init(getApplicationContext());

        //register custom fonts like this (or also provide a font definition file)
        Iconics.registerFont(new Cniao5Font());
    }
}
