package com.cniao5.cniao5play.data.http;

import com.cniao5.cniao5play.bean.AppInfo;
import com.cniao5.cniao5play.bean.BaseBean;
import com.cniao5.cniao5play.bean.Category;
import com.cniao5.cniao5play.bean.IndexBean;
import com.cniao5.cniao5play.bean.LoginBean;
import com.cniao5.cniao5play.bean.PageBean;
import com.cniao5.cniao5play.bean.requestbean.LoginRequestBean;

import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ivan on 2016/12/30.
 */

public interface ApiService {


    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";



//
//    @GET("featured")
//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);



    @GET("featured2")
    public Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);


    @GET("index")
    public  Observable<BaseBean<IndexBean>> index();



     @GET("toplist")
    public  Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);

     @GET("game")
    public  Observable<BaseBean<PageBean<AppInfo>>> games(@Query("page") int page);


    @POST("login")
    Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);


    @GET("category")
    Observable<BaseBean<List<Category>>> getCategories();


    @GET("category/featured/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(@Path("categoryid") int categoryid,@Query("page") int page);



    @GET("app/{id}")
    Observable<BaseBean<AppInfo>> getAppDetail(@Path("id") int id);
















}
