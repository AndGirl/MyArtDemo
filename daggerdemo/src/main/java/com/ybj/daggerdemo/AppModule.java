package com.ybj.daggerdemo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by 杨阳洋 on 2017/12/31.
 *
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder()
                .build();
    }

}
