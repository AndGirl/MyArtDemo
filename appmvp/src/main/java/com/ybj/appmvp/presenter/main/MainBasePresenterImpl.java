package com.ybj.appmvp.presenter.main;

import android.util.Log;

import com.ybj.appmvp.model.GithubService;
import com.ybj.appmvp.model.Repo;
import com.ybj.appmvp.view.MainBaseView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 杨阳洋 on 2017/12/30.
 */

public class MainBasePresenterImpl implements MainBasePresenter {

    private MainBaseView mainView;
    private List<Repo> repoList;
    private Disposable mSubscribe;

    @Override
    public void attachView(MainBaseView view) {
        mainView = view;
    }

    @Override
    public void detachView() {
        mainView = null;
        if(mSubscribe != null) {
            Log.e("TAG", "mSubscribe start=====" + mSubscribe.toString());
            mSubscribe.dispose();
            Log.e("TAG", "mSubscribe end=====" + mSubscribe.toString());
        }
    }

    @Override
    public void loadGitHubJava() {
        mainView.showPreogerss();

        String url = "http://github.laowch.com/json/java_daily" ;
        mSubscribe = GithubService.Factory.create().javaRepositories(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Repo>>() {
                    @Override
                    public void accept(List<Repo> repos) throws Exception {
                        repoList = repos;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mainView.showErrorMessage();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (repoList != null) {
                            mainView.showRecyclerView(repoList);
                        }
                    }
                });

    }

}
