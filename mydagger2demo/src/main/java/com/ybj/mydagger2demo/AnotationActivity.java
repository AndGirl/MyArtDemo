package com.ybj.mydagger2demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ybj.mydagger2demo.ano.Release;
import com.ybj.mydagger2demo.ano.Test;
import com.ybj.mydagger2demo.anotation.DaggerAnotationComponent;
import com.ybj.mydagger2demo.anotation.TestAnotatio;

import javax.inject.Inject;

/**
 * 测试别名
 */

public class AnotationActivity extends AppCompatActivity {

    @Test
    @Inject
    TestAnotatio mTestAnotatio;

    @Release
    @Inject
    TestAnotatio mTestAnotatioRealease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotation);

        TextView second = (TextView) findViewById(R.id.second);

        DaggerAnotationComponent.create().inject(this);

        mTestAnotatio.test();
        mTestAnotatioRealease.test();

        Log.e("TAG", "mTestAnotatio ============ " + mTestAnotatio);
        Log.e("TAG", "mTestAnotatioRealease ============ " + mTestAnotatioRealease);

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnotationActivity.this,ThirdActivity.class));
            }
        });

    }
}
