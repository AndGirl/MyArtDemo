package com.ybj.okhttpdemo.okHttp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 杨阳洋 on 2017/12/23.
 * 对外提供一个可以调用的客户端
 */

public class SimpleHttpClient {

    private Builder mBuilder;

    private SimpleHttpClient(Builder builder) {
        this.mBuilder = builder;
    }

    public Request buildRequest() {

        Request.Builder builder = new Request.Builder();

        if (mBuilder.method == "GET") {
            builder.url(buildGetRequestParam());
            builder.get();
        } else if (mBuilder.method == "POST") {
            try {
                builder.post(buildRequestBody());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            builder.url(mBuilder.url);
        }


        return builder.build();
    }

    /**
     * 解析Url
     * @return
     */
    private String buildGetRequestParam() {

        if (mBuilder.mParams.size() <= 0)
            return this.mBuilder.url;

        Uri.Builder builder = Uri.parse(mBuilder.url).buildUpon();

        for (RequestParam p : mBuilder.mParams) {

            builder.appendQueryParameter(p.getKey(), p.getObj() == null ? "" : p.getObj().toString());
        }

        String url = builder.build().toString();

        return url;


    }

    private RequestBody buildRequestBody() throws JSONException {

        if (mBuilder.isJsonParam) {

            JSONObject jsonObj = new JSONObject();

            for (RequestParam p : mBuilder.mParams) {

                jsonObj.put(p.getKey(), p.getObj());
            }

            String json = jsonObj.toString();

            Log.d("SimpleRequest", "request json=" + json);
            return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        }


        FormBody.Builder builder = new FormBody.Builder();

        for (RequestParam p : mBuilder.mParams) {

            builder.add(p.getKey(), p.getObj() == null ? "" : p.getObj().toString());
        }

        return builder.build();

    }

    /**
     * 异步方法
     * @param callback
     */
    public void enqueue(BaseCallback callback) {
        OkHttpManager.getInstance().request(this, callback);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * 默认是GET方法
     */
    public static class Builder {

        private String url;//API
        private String method;//请求方法
        private boolean isJsonParam;//是否Json参数

        private List<RequestParam> mParams;//请求参数

        private Builder() {
            method = "GET";
        }

        public SimpleHttpClient build() {
            return new SimpleHttpClient(this);
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder get() {
            method = "GET";
            return this;
        }

        public Builder post() {
            method = "POST";
            return this;
        }


        /**
         * JSON 参数
         *
         * @return
         */
        public Builder json() {
            isJsonParam = true;
            return post();
        }

        public Builder addParam(String key, Object value) {
            if (mParams == null) {
                mParams = new ArrayList<>();
            }

            mParams.add(new RequestParam(key, value));

            return this;
        }
    }
}
