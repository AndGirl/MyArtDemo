package com.ybj.daggerdemo.user;

import android.content.Context;

/**
 * Created by Ivan on 2016/12/1.
 */

public class UserStore {

    public UserStore() {
    }

    private  Context mContext;
    public UserStore(Context context){

        this.mContext = context;

    }
    public void  register(){

        //本地保存数据

    }
}
