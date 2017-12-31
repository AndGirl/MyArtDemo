package com.ybj.daggerdemo.user;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */

public class UserManager {

    private ApiService mApiService;
    private UserStore mUserStore;

    public UserManager(ApiService apiService,UserStore userStore) {
        mApiService = apiService;
        mUserStore = userStore;
    }

    public void register(){
        mApiService.register();
        mUserStore.register();
    }

}


