package com.ybj.daggerdemo.user;

import com.ybj.daggerdemo.AppComponent;
import com.ybj.daggerdemo.MainActivity;
import com.ybj.daggerdemo.ano.ActivityScope;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@ActivityScope
@Component(modules = {UserModule.class},dependencies = AppComponent.class)
public interface UserComponet {

    void inject(MainActivity activity);

}
