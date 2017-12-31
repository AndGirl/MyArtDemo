package com.ybj.mydagger2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ybj.mydagger2demo.four.DaggerFourComponent;
import com.ybj.mydagger2demo.third.TestSingleton;
import com.ybj.mydagger2demo.third.TestSingletonModule;

import javax.inject.Inject;

public class FourActivity extends AppCompatActivity {

    @Inject
    TestSingleton mTestSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        DaggerFourComponent.builder().testSingletonModule(new TestSingletonModule(getApplication()))
                .appComponent(((MyApplication)getApplication()).getAppComponent())
                .build().inject(this);

        mTestSingleton.test();
        Log.e("TAG", "mTestSingleton ==========" + mTestSingleton);

    }
}
