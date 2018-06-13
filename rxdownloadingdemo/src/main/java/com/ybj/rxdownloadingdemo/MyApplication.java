package com.ybj.rxdownloadingdemo;

import android.app.Application;

import zlc.season.rxdownload3.core.DownloadConfig;
import zlc.season.rxdownload3.extension.ApkInstallExtension;
import zlc.season.rxdownload3.extension.ApkOpenExtension;

/**
 * Created by 杨阳洋 on 2018/6/13.
 * 在APP启动时添加您的配置,就像这样:
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DownloadConfig.Builder builder = DownloadConfig.Builder.Companion.create(this)
                .enableDb(true)//启动数据库
                .enableService(true)//启用服务
                .enableNotification(true)//启用通知
                .addExtension(ApkInstallExtension.class)//扩展
                .addExtension(ApkOpenExtension.class);
        DownloadConfig.INSTANCE.init(builder);

    }
}
