package com.ybj.daggerdemo;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by 杨阳洋 on 2017/12/31.
 * @Singleton:单例
 */

@Module
public class HttpModule {

    @Provides
    public OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder().build();
    }

}
