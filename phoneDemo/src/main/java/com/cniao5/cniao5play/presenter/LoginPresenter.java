package com.cniao5.cniao5play.presenter;

import android.util.Log;

import com.cniao5.cniao5play.bean.LoginBean;
import com.cniao5.cniao5play.common.Constant;
import com.cniao5.cniao5play.common.rx.RxHttpReponseCompat;
import com.cniao5.cniao5play.common.rx.subscriber.ErrorHandlerSubscriber;
import com.cniao5.cniao5play.common.util.ACache;
import com.cniao5.cniao5play.common.util.VerificationUtils;
import com.cniao5.cniao5play.presenter.contract.LoginContract;
import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.presenter
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.LoginView> {



    @Inject
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {
        super(iLoginModel, loginView);
    }



    public void login(String phone,String pwd){


        Log.d("LoginPresenter","phone="+phone);
        Log.d("LoginPresenter","pwd="+pwd);

       if(!VerificationUtils.matcherPhoneNum(phone)){

           mView.checkPhoneError();
           return;
       }else {
           mView.checkPhoneSuccess();
       }


        mModel.login(phone,pwd).compose(RxHttpReponseCompat.<LoginBean>compatResult())
        .subscribe(new ErrorHandlerSubscriber<LoginBean>(mContext) {
            @Override
            public void onStart() {

                mView.showLoading();
            }

            @Override
            public void onCompleted() {

                mView.dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.dismissLoading();
            }

            @Override
            public void onNext(LoginBean loginBean) {
                mView.loginSuccess(loginBean);
                saveUser(loginBean);

                RxBus.get().post(loginBean.getUser());
            }
        });



    }


    private void saveUser(LoginBean bean){

        ACache aCache = ACache.get(mContext);

        aCache.put(Constant.TOKEN,bean.getToken());
        aCache.put(Constant.USER,bean.getUser());
    }

}
