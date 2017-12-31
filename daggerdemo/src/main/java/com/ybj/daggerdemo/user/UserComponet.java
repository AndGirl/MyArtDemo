package com.ybj.daggerdemo.user;

import com.ybj.daggerdemo.MainActivity;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Component(modules = {UserModule.class})
public interface UserComponet {

    void inject(MainActivity activity);

}
