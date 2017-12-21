package com.ybj.okhttpdemo;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 杨阳洋 on 2017/12/22.
 * 测试GET方法
 */

public class GetExample {

    //1.创建一个客户端
    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        //2.Requests
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        //3.Response
        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    public static void main(String [] args) throws IOException {
        GetExample getExample = new GetExample();
        String response = getExample.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }

}
