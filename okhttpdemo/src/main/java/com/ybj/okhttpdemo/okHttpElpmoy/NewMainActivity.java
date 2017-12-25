package com.ybj.okhttpdemo.okHttpElpmoy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ybj.okhttpdemo.R;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        getRequest();

    }

    /**
     * 原生应用
     */
    private void getRequest() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://gc.ditu.aliyun.com/geocoding?a=成都市";

        Request build = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(build)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("TAG", "失败" +
                                "");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Log.e("TAG", "陈宫");
                        if(response.body() != null) {
                            response.body().close();
                        }
                    }
                });

    }

    /**
     * 头部信息打印
     * @param hs
     */
    private void printHeader(Headers hs){
        Map<String,List<String>> headers = hs.toMultimap();

        for(Map.Entry<String,List<String>> entry : headers.entrySet()){
            Log.d("MainActivity",entry.getKey());
            for (String val : entry.getValue()){
                Log.d("MainActivity",val);
            }
        }
    }


}
