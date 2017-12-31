package com.ybj.mydagger2demo.main;

import android.util.Log;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */

public class TestManager {

    private TestBeanOne mTestBeanOne;
    private TestBeanTwo mTestBeanTwo;

    public TestManager(TestBeanOne testBeanOne, TestBeanTwo testBeanTwo) {
        mTestBeanOne = testBeanOne;
        mTestBeanTwo = testBeanTwo;
    }

    public void register(){
        mTestBeanOne.register();
        mTestBeanTwo.register();
        Log.e("TAG", "TestManager register()");
    }

}
