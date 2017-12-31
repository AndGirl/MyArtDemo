package com.ybj.mydagger2demo.third;

import android.app.Application;
import android.util.Log;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */

public class TestSingleton {

    private Application mAllApplication;

    public TestSingleton(Application allApplication) {
        mAllApplication = allApplication;
    }

    public void test(){
        Log.e("TAG", "test()");
        Log.e("TAG", "======================mAllApplication ====" + mAllApplication);


    }

}
