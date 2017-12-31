package com.ybj.mydagger2demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ybj.mydagger2demo.main.DaggerMainComponent;
import com.ybj.mydagger2demo.main.MainModule;
import com.ybj.mydagger2demo.main.TestBeanOne;
import com.ybj.mydagger2demo.main.TestBeanTwo;
import com.ybj.mydagger2demo.main.TestManager;

import javax.inject.Inject;

/**
 * dagger2是一种快速依赖注入器
 *
 * 简单简绍
 * @Inject：（使用依赖）
 * 通常需要在依赖的地方使用这个注解。
 * @Module:
 * Modules类里面的方法专门提供依赖，定义一个类用@Module注解，这样Dagger在构造类的实例时候
 * 就知道从哪里找到需要的依赖。
 * @Provide(提供依赖)
 * 在modules中，我们定义的方法时用这个注解，以此来告诉Dagger我们想要构造对象并提供这些依赖
 * @Componet
 * Components从根本上来说是一个注入器，也可以说是@Inject和@Module的桥梁，它的主要作用是连接着两个部分。
 * @Named()
 * 区分不同实例
 */

public class MainActivity extends AppCompatActivity {


    @Inject
    TestManager mTestManager;
    @Inject
    TestBeanOne mTestBeanOne;
    @Inject
    TestBeanTwo mTestBeanTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView main = (TextView) findViewById(R.id.main);

        DaggerMainComponent.builder().mainModule(new MainModule(this))
                .build()
                .inject(this);

        mTestManager.register();

        Log.e("TAG", "============================================");
        mTestBeanOne.register();
        mTestBeanTwo.register();

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AnotationActivity.class));
            }
        });

    }
}
