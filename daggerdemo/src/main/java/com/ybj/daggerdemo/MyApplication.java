package com.ybj.daggerdemo;

import android.app.Application;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}
