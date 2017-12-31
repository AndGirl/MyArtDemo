package com.ybj.mydagger2demo.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2017/12/31.
 */
@Module
public class MainModule {

    private Context mContext;

    public MainModule(Context context) {
        mContext = context;
    }

    @Provides
    public TestManager provideTestManager(TestBeanOne one , TestBeanTwo two){
        Log.e("TAG", "provideTestManager构造器");
        Toast.makeText(mContext, "Context被使用了哦", Toast.LENGTH_SHORT).show();
        return new TestManager(one,two);
    }

    @Provides
    public TestBeanOne provideTestBeanOne(){
        Log.e("TAG", "provideTestBeanOne构造器");
        return new TestBeanOne();
    }

    @Provides
    public TestBeanTwo provideTestBeanTwo(){
        Log.e("TAG", "provideTestBeanTwo构造器");
        return new TestBeanTwo();
    }


}
