package com.ybj.daggerdemo;

import com.ybj.daggerdemo.ano.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@ActivityScope
@Subcomponent(modules = UtilModule.class)
public interface CComponent {

    void inject(MainActivity mainActivity);

}
