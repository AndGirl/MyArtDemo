package com.ybj.daggerdemo;

import com.ybj.daggerdemo.ano.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Module
public class UtilModule {
    @Provides
    @ActivityScope
    public Gson provide(){
        return new Gson();
    }

}
