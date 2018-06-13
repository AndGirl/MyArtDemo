package com.ybj.rxdownloadingdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Downloading;
import zlc.season.rxdownload3.core.Failed;
import zlc.season.rxdownload3.core.Normal;
import zlc.season.rxdownload3.core.Status;
import zlc.season.rxdownload3.core.Succeed;
import zlc.season.rxdownload3.core.Suspend;
import zlc.season.rxdownload3.core.Waiting;
import zlc.season.rxdownload3.extension.ApkInstallExtension;
import zlc.season.rxdownload3.extension.ApkOpenExtension;

public class BasicDownloadActivity extends AppCompatActivity {

    private static final String iconUrl = "http://p5.qhimg.com/dr/72__/t01a362a049573708ae.png";
    private static final String url = "http://shouji.360tpcdn.com/170922/9ffde35adefc28d3740d4e16612f078a/com.tencent.tmgp.sgame_22011304.apk";
    private Status currentStatus = new Status();
    private ProgressBar mProgressBar;
    private TextView mPercentText;
    private TextView mSizeText;
    private Button mActionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_download);

        ImageView imageView = (ImageView) findViewById(R.id.img);
        Button finishBtn = (Button) findViewById(R.id.finish);
        mActionBtn = (Button) findViewById(R.id.action);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mPercentText = (TextView) findViewById(R.id.percent);
        mSizeText = (TextView) findViewById(R.id.size);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchClick();
            }
        });

        Picasso.with(this).load(iconUrl).into(imageView);

        initData();

    }

    private void initData() {
        RxDownload.INSTANCE.create(url, true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Status>() {
                    @Override
                    public void accept(@NonNull Status status) throws Exception {
                        Log.e("TAG", status.toString());
                        currentStatus = status;
                        setProgress(status);
                        setActionText(status);
                    }
                });
    }

    private void setProgress(Status status) {
        mProgressBar.setMax((int) status.getTotalSize());
        mProgressBar.setProgress((int) status.getDownloadSize());

        mPercentText.setText(status.percent());
        mSizeText.setText(status.formatString());
    }

    private void setActionText(Status status) {
        String text = "";
        if (status instanceof Normal) {
            text = "开始";
        } else if (status instanceof Suspend) {
            text = "已暂停";
        } else if (status instanceof Waiting) {
            text = "等待中";
        } else if (status instanceof Downloading) {
            text = "暂停";
        } else if (status instanceof Failed) {
            text = "失败";
        } else if (status instanceof Succeed) {
            text = "安装";
        } else if (status instanceof ApkInstallExtension.Installing) {
            text = "安装中";
        } else if (status instanceof ApkInstallExtension.Installed) {
            text = "打开";
        }
        mActionBtn.setText(text);
    }

    /**
     * 不同状态不同处理方式
     */
    private void dispatchClick() {
        if (currentStatus instanceof Normal) {
            start();
        } else if (currentStatus instanceof Suspend) {
            start();
        } else if (currentStatus instanceof Failed) {
            start();
        } else if (currentStatus instanceof Downloading) {
            stop();
        } else if (currentStatus instanceof Succeed) {
            install();
        } else if (currentStatus instanceof ApkInstallExtension.Installed) {
            open();
        }
    }

    private void start() {
        RxDownload.INSTANCE.start(url).subscribe();
    }

    private void stop() {
        RxDownload.INSTANCE.stop(url).subscribe();
    }

    private void install() {
        RxDownload.INSTANCE.extension(url, ApkInstallExtension.class).subscribe();
    }

    private void open() {
        //TODO: open app
        RxDownload.INSTANCE.extension(url, ApkOpenExtension.class).subscribe();
    }

}
