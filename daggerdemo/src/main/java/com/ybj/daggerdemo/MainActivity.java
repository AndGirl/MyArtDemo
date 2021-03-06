package com.ybj.daggerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ybj.daggerdemo.user.ApiService;

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
    ApiService mApiService;
    @Inject
    ApiService mApiServiceRelease;

//    @Inject
//    UserManager mUserManager;

    /**
     * treu:测试
     */
    private boolean is_dev = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DaggerUserComponet.builder().userModule(new UserModule(this))
//                .httpModule(new HttpModule())
//                .appComponent(((MyApplication)getApplication()).getAppComponent())
//                .build().inject(this);
        Log.e("TAG", "mApiService_Dev = " + mApiService);
        Log.e("TAG", "mApiServiceRelease = " + mApiServiceRelease);

//        if(is_dev) {
//            mApiService.register();
//        }else {
//            mApiServiceRelease.register();
//        }

        //需要传参
//        DaggerUserComponet.builder().userModule(new UserModule(this))
//                .httpModule(new HttpModule())
//                .build().inject(this);
//        mUserManager.register();

    }
}
