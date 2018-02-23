package com.ybj.daggerdemo.user;

import android.content.Context;
import android.util.Log;

import com.ybj.daggerdemo.HttpModule;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */

@Module(includes = HttpModule.class)
public class UserModule {

    private Context mContext;

    public UserModule(Context context){
        this.mContext = context;

    }
//    @Singleton
    @Provides
    public ApiService provideApiServiceDev(OkHttpClient okHttpClient){
        Log.e("TAG", "ApiServiceDev");
        return new ApiService(okHttpClient);
    }

//    @Singleton
//    @Provides
//    public ApiService provideApiServiceForRelease(OkHttpClient okHttpClient){
//        Log.e("TAG", "===ApiServiceForRelease===");
//        return new ApiService(okHttpClient);
//    }

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
