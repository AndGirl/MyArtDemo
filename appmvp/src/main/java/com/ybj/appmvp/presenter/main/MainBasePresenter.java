package com.ybj.appmvp.presenter.main;

import com.ybj.appmvp.base.BasePresenter;
import com.ybj.appmvp.view.MainBaseView;

/**
 * Created by 杨阳洋 on 2017/12/30.
 * 处理MainActivity当中的业务逻辑,只为MainActivity服务
 */

public interface MainBasePresenter extends BasePresenter<MainBaseView> {

    void loadGitHubJava();

}
