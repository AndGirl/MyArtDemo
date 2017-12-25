package com.ybj.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit简单使用
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * @QueryMap Map<String, String> options
         * 这片文章不知道被转载过多少次
         * https://juejin.im/entry/58aac9710ce463006b13914d
         */
        //getRetrofit();

        postRetrofit();



    }

    private void postRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.189:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Uart uart = retrofit.create(Uart.class);
        uart.login(new UartParam("uart","123456"))
                .enqueue(new Callback<BaseResult>() {
                    @Override
                    public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {
                        Log.e("TAG", "成功" + response.body().getMessage().toString());
                    }

                    @Override
                    public void onFailure(Call<BaseResult> call, Throwable t) {
                        Log.e("TAG", "失败");
                    }
                });

        uart.editUser(1,"uart").enqueue(new Callback<BaseResult>() {
            @Override
            public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {
                Log.e("TAG", "陈宫" + response.body().getMessage().toString());
            }

            @Override
            public void onFailure(Call<BaseResult> call, Throwable t) {
                Log.e("TAG", "失败");
            }
        });

    }

    private void getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Uart uart = retrofit.create(Uart.class);

        Call<List<UartBean>> girl = uart.getUserInfo("AndGirl","desc");

//        简单使用逻辑
//        Map<String,String> map = new HashMap<>();
//
//        map.put("id","1");
//        map.put("name","cniao5");
//
//        Call<User> userCall = uart.getUserInfoWithMap(map);


        girl.enqueue(new Callback<List<UartBean>>() {
            @Override
            public void onResponse(Call<List<UartBean>> call, Response<List<UartBean>> response) {
                response.body().toString();
                response.body().get(0).getBlobs_url();
                Toast.makeText(MainActivity.this, "1111", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<UartBean>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "2222", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
