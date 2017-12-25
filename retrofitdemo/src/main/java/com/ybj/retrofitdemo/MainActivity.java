package com.ybj.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Uart uart = retrofit.create(Uart.class);

        Call<List<UartBean>> girl = uart.getUserInfo("AndGirl");

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
