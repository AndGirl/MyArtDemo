package com.ybj.mvpdemo.presenter.main;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.ybj.mvpdemo.model.User;
import com.ybj.mvpdemo.view.MainBaseView;
import com.ybj.mvpdemo.view.SecondActivity;

/**
 * Created by 杨阳洋 on 2017/12/26.
 */

public class MainBasePresenterImpl implements MainBasePresenter {

    private MainBaseView mMainBaseView;
    private Context mContext;

    public MainBasePresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(MainBaseView v) {
        this.mMainBaseView = v;
    }

    @Override
    public void detachView() {
        mMainBaseView = null;
    }

    @Override
    public void login(User user) {
        if(!TextUtils.isEmpty(user.getName()) && !TextUtils.isEmpty(user.getPwd())) {
            if("uart".equals(user.getName()) && "uart".equals(user.getPwd())) {
                mMainBaseView.loginSuccess("成功");
                mContext.startActivity(new Intent(mContext, SecondActivity.class));
            }else{
                mMainBaseView.loginFail("失败");
            }
        }else{
            mMainBaseView.showToast("用户名不能为空");
        }
    }
}
