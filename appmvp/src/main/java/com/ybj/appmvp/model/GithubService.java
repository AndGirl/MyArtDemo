package com.ybj.appmvp.model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by 杨阳洋 on 2017/12/29.
 */

public interface GithubService {

    @GET("users/{username}/repos")
    Observable<List<Repository>> publicRepositories(@Path("username") String username);

    @GET
    Observable<List<Repo>> javaRepositories(@Url String url);

    class Factory{
        public static GithubService create(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(GithubService.class);
        }
    }

}
