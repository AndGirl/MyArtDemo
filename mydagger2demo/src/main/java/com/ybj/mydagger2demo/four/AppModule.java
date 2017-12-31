package com.ybj.mydagger2demo.four;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2017/12/31.
 * 全局使用
 */
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideAllApplication(){
        return mApplication;
    }

}
