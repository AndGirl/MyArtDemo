package com.ybj.daggerdemo;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    OkHttpClient okhttpclient();

}
