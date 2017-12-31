package com.ybj.mydagger2demo.anotation;

import com.ybj.mydagger2demo.AnotationActivity;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Component(modules = AnotationModule.class)
public interface AnotationComponent {

    void inject(AnotationActivity mainActivity);

}
