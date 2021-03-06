package com.ybj.okhttpdemo.okHttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by 杨阳洋 on 2017/12/23.
 */

public class OkHttpManager {

    private static OkHttpManager instance;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Gson mGson;

    public OkHttpManager() {
        initOkHttp();

        mHandler = new Handler(Looper.getMainLooper());
        mGson = new Gson();

    }

    public static synchronized OkHttpManager getInstance() {
        if (instance == null) {
            instance = new OkHttpManager();
        }
        return instance;
    }

    private void initOkHttp() {
        mOkHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30000, TimeUnit.SECONDS)
                .connectTimeout(30000, TimeUnit.SECONDS).build();
    }


    public void request(SimpleHttpClient client, final BaseCallback callback) {

        if (callback == null) {
            throw new NullPointerException("callback is null");
        }

        mOkHttpClient.newCall(client.buildRequest()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendOnFailureMessage(callback, call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (callback.mType == null || callback.mType == String.class) {
                        sendOnSuccessMessage(callback, result);
                    } else {
                        sendOnSuccessMessage(callback, mGson.fromJson(result, callback.mType));
                    }
                    if (response.body() != null) {
                        response.body().close();
                    }
                } else {
                    sendOnErrorMessage(callback, response.code());
                }
            }
        });

    }


    private void sendOnFailureMessage(final BaseCallback callback, final Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(call, e);
            }
        });


    }

    private void sendOnErrorMessage(final BaseCallback callback, final int code) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(code);
            }
        });
    }

    private void sendOnSuccessMessage(final BaseCallback callback, final Object obj) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(obj);
            }
        });
    }
}
