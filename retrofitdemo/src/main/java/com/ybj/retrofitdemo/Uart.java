package com.ybj.retrofitdemo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 杨阳洋 on 2017/12/25.
 */

public interface Uart {

    @GET("users/{user}/repos")
    Call<List<UartBean>> getUserInfo(@Path("user") String user
    , @Query("sort")String sort);

    @POST("login/json")
    Call<BaseResult> login(@Body UartParam param);

    @GET("user/info")
    Call<User>  getUserInfoWithMap(@QueryMap Map<String,String> params);

    @FormUrlEncoded
    @Headers({"User-Agent: www.cniao5.com","my_name:cniao5"})
    @POST("user/edit")
    Call<BaseResult> editUser(@Field("id") int user_id
            , @Field("username") String user_name);

}
