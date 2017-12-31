package com.ybj.mydagger2demo.third;

import com.ybj.mydagger2demo.ThirdActivity;
import com.ybj.mydagger2demo.ano.ActivityScope;
import com.ybj.mydagger2demo.four.AppComponent;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@ActivityScope
@Component(modules = {TestSingletonModule.class},dependencies = AppComponent.class)
public interface ThirdComponent {

    void inject(ThirdActivity thirdActivity);

}
