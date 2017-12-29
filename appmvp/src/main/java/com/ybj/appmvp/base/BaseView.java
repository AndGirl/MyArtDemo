package com.ybj.appmvp.base;

/**
 * Created by 杨阳洋 on 2017/12/30.
 */

public interface BaseView<T> {

    /**
     * 等待
     */
    void showPreogerss();

    /**
     * 展示错误的信息
     */
    void showErrorMessage();

    /**
     * 展示RecyclerView的页面
     * @param t：展示页面传入的实际数据
     */
    void showRecyclerView(T t);

}
