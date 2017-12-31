package com.ybj.mydagger2demo.four;

import com.ybj.mydagger2demo.FourActivity;
import com.ybj.mydagger2demo.ano.ActivityScope;
import com.ybj.mydagger2demo.third.TestSingletonModule;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@ActivityScope
@Component(modules = {TestSingletonModule.class},dependencies = AppComponent.class)
public interface FourComponent {

        void inject(FourActivity fourActivity);

}
