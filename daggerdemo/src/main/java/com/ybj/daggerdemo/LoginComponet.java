package com.ybj.daggerdemo;

import com.ybj.daggerdemo.ano.ActivityScope;
import com.ybj.daggerdemo.user.UserModule;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@ActivityScope
@Component(modules = UserModule.class,dependencies = AppComponent.class)
public interface LoginComponet {

    void inject(LoginActivity loginActivity);

}
