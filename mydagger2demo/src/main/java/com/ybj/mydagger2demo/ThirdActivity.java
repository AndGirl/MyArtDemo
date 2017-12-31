package com.ybj.mydagger2demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ybj.mydagger2demo.third.DaggerThirdComponent;
import com.ybj.mydagger2demo.third.TestSingleton;
import com.ybj.mydagger2demo.third.TestSingletonModule;

import javax.inject.Inject;

public class ThirdActivity extends AppCompatActivity {

    @Inject
    TestSingleton mTestSingleton;

    @Inject
    TestSingleton mSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        DaggerThirdComponent.builder().testSingletonModule(new TestSingletonModule(getApplication()))
                    .appComponent(((MyApplication)getApplication()).getAppComponent())
                    .build().inject(this);

        mTestSingleton.test();
        mSingleton.test();

        Log.e("TAG", "mTestSingleton ================= " + mTestSingleton);
        Log.e("TAG", "mSingleton ================= " + mSingleton);

        findViewById(R.id.thrid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, FourActivity.class);
                startActivity(intent);
            }
        });

    }
}
