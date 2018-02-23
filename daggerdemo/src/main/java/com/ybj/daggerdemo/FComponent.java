package com.ybj.daggerdemo;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Singleton
@Component(modules = AppModule.class)
public interface FComponent {

    CComponent getChildComponent();

}
