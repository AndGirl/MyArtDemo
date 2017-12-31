package com.ybj.mydagger2demo;

import android.app.Application;

import com.ybj.mydagger2demo.four.AppComponent;
import com.ybj.mydagger2demo.four.AppModule;
import com.ybj.mydagger2demo.four.DaggerAppComponent;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}
