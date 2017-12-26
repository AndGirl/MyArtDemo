package com.ybj.mvpdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ybj.mvpdemo.R;
import com.ybj.mvpdemo.model.User;
import com.ybj.mvpdemo.presenter.main.MainBasePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainBaseView {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    private MainBasePresenterImpl mainPresenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenterImpl = new MainBasePresenterImpl(this);

        mainPresenterImpl.attachView(this);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(mEtUsername.getText().toString(),mEtPwd.getText().toString());
                mainPresenterImpl.login(user);
            }
        });

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void loginFail(String msg) {
        showToast(msg);
    }
}
