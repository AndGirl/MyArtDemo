package com.ybj.daggerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ybj.daggerdemo.user.UserManager;
import com.ybj.daggerdemo.user.UserModule;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    UserManager mUserManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DaggerLoginComponet.builder().userModule(new UserModule(this))
                .appComponent(((MyApplication)getApplication()).getAppComponent())
                .build().inject(this);

        mUserManager.login();
    }
}
