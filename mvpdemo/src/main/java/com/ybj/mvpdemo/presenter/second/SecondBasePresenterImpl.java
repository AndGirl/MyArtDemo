package com.ybj.mvpdemo.presenter.second;

import android.app.Dialog;

import com.ybj.mvpdemo.view.SecondBaseView;

/**
 * Created by 杨阳洋 on 2017/12/26.
 */

public class SecondBasePresenterImpl implements SecondBasePresenter {

    private SecondBaseView mSecondBaseView;

    @Override
    public void attachView(SecondBaseView v) {
        this.mSecondBaseView = v;
    }

    @Override
    public void detachView() {
        mSecondBaseView = null;
    }


    @Override
    public void startDialog(Dialog dialog) {
        dialog.show();
    }

    @Override
    public void endDialog(Dialog dialog) {

    }
}
