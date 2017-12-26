package com.ybj.mvpdemo.presenter.second;

import android.app.Dialog;

import com.ybj.mvpdemo.base.BasePresenter;
import com.ybj.mvpdemo.view.SecondBaseView;

/**
 * Created by 杨阳洋 on 2017/12/26.
 */

public interface SecondBasePresenter extends BasePresenter<SecondBaseView> {

    void startDialog(Dialog dialog);

    void endDialog(Dialog dialog);

}
