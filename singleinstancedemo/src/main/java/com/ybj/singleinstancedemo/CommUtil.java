package com.ybj.singleinstancedemo;

import android.content.Context;

/**
 * Created by 杨阳洋 on 2018/2/9.
 */

public class CommUtil {

    private static CommUtil instance;
    private Context context;

    private CommUtil(Context context){
        this.context = context;
    }

    public static CommUtil getInstance(Context context){
        if(instance == null) {
            instance = new CommUtil(context);
        }
        return instance;
    }


}
