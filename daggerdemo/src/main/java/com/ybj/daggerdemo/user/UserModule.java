package com.ybj.daggerdemo.user;

import android.content.Context;
import android.util.Log;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */

@Module
public class UserModule {

//    @Provides
//    public ApiService provideApiService(){
//        Log.e("TAG", "provideApiService()");
//        return new ApiService();
//    }

    private Context mContext;

    public UserModule(Context context){
        this.mContext = context;
    }

    @Provides
    public String url(){
        return "www.baidu.com";
    }

    //第一种方法
    @Provides
    public UserStore provideUserStore(){
        Log.e("TAG", "provideUserStore()");
        return new UserStore(this.mContext);
    }

    @Provides
    public UserManager provideUserManager(ApiService apiService,UserStore userStore){
        return new UserManager(apiService,userStore);
    }

}
