package com.ybj.horizonaldatepicker;

import android.app.Application;
import android.content.Context;

/**
 * Created by 杨阳洋 on 2018/6/4.
 */

public class MyApplication extends Application {
    private static MyApplication Instance;

    public static MyApplication getApplication() {
        return Instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
    }

    public static Context getContext() {
        return Instance.getApplicationContext();
    }
}
