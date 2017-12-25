package com.ybj.okhttpdemo.okHttp;

import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by 杨阳洋 on 2017/12/23.
 * 回调成功的方法包括成功失败出错
 */

public class BaseCallback<T> {

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            return null;
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public Type mType;


    public BaseCallback(){

        mType = getSuperclassTypeParameter(this.getClass());

    }

    public void onSuccess(T t){}

    public void onError(int code){}

    public void onFailure(Call call, IOException e){}

}
