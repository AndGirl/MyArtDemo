package com.ybj.daggerdemo.user;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */

@Module
public class UserModule {

    @Provides
    public ApiService provideApiService(){
        return new ApiService();
    }

}
