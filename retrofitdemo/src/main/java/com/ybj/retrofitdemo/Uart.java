package com.ybj.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 杨阳洋 on 2017/12/25.
 */

public interface Uart {

    @GET("users/{user}/repos")
    Call<List<UartBean>> getUserInfo(@Path("user") String user
    , @Query("sort")String sort);

    @POST("login/json")
    Call<BaseResult> login(@Body UartParam param);

}
