package com.ybj.daggerdemo.user;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Ivan on 2016/12/1.
 */

public class ApiService {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient mOkHttpClient;

    //第二种方法
    //@Inject
    public ApiService(OkHttpClient okHttpClient){

        Log.d("ApiService","ApiService");

        this.mOkHttpClient = okHttpClient;

    }

    public void login(){
        Log.d("ApiService--login","" +mOkHttpClient);
    }

    
    public  void  register(){

        // 云端保存数据

        Log.d("ApiService--User","" +mOkHttpClient);

        RequestBody body = RequestBody.create(JSON, "");
        Request request = new Request.Builder()
                .url("")
                .post(body)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
}
