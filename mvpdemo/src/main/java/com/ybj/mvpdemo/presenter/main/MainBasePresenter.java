package com.ybj.mvpdemo.presenter.main;

import com.ybj.mvpdemo.base.BasePresenter;
import com.ybj.mvpdemo.model.User;
import com.ybj.mvpdemo.view.MainBaseView;

/**
 * Created by 杨阳洋 on 2017/12/26.
 */

public interface MainBasePresenter extends BasePresenter<MainBaseView>{

    void login(User user);

}
