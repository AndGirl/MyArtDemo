package com.ybj.okhttpdemo.okHttp;

/**
 * Created by 杨阳洋 on 2017/12/23.
 */

public interface ProgerssListener {

    void onProgress(int progress);
    void onDone(long totalSize);

}
