package com.ybj.mydagger2demo.main;

import com.ybj.mydagger2demo.MainActivity;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2017/12/31.
 * 可以有多个Module
 */
@Component(modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);

}
