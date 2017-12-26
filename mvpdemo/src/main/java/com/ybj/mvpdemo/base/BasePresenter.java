package com.ybj.mvpdemo.base;

/**
 * Created by 杨阳洋 on 2017/12/26.
 * 作为View与Model交互的中间纽带，处理与用户交互负责的逻辑
 */

public interface BasePresenter<T>{

    //绑定View
    void attachView(T v);

    //移除View
    void detachView();

}
