package com.ybj.mydagger2demo.four;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Application getApplication();

}
