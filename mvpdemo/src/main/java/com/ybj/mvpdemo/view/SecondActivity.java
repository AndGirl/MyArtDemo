package com.ybj.mvpdemo.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ybj.mvpdemo.R;
import com.ybj.mvpdemo.presenter.second.SecondBasePresenterImpl;

public class SecondActivity extends AppCompatActivity implements SecondBaseView{

    private SecondBasePresenterImpl mSecondBasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final LinearLayout second = (LinearLayout) findViewById(R.id.second);

        mSecondBasePresenter = new SecondBasePresenterImpl();

        mSecondBasePresenter.attachView(this);

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSecondBasePresenter.startDialog(new Dialog(SecondActivity.this));
            }
        });

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(SecondActivity.this, msg , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialong(String msg) {
        showToast(msg);
    }
}
