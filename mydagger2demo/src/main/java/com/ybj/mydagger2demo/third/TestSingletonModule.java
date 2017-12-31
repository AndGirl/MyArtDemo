package com.ybj.mydagger2demo.third;

import android.app.Application;

import com.ybj.mydagger2demo.ano.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Module
public class TestSingletonModule {

    private Application mApplication;

    public TestSingletonModule(Application application){
        mApplication = application;
    }

    @ActivityScope
    @Provides
    public TestSingleton provideTestSingleton(){
        return new TestSingleton(mApplication);
    }

}
