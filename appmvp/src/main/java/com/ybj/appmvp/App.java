package com.ybj.appmvp;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ybj.appmvp.util.FavoReposHelper;

/**
 * Created by 杨阳洋 on 2017/12/29.
 */

public class App extends Application {

    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        FavoReposHelper.init(this);
    }

    public static App getContext(){
        return context;
    }

    public static Gson getGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

}
