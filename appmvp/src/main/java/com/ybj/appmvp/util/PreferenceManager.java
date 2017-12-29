package com.ybj.appmvp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 杨阳洋 on 2017/12/29.
 * SP存储工具类
 * int,long,boolean，String类型数据的存储
 * 检查key是否存在
 * remove key的操作
 */

public class PreferenceManager {

    /**
     * 获取SP存储
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context context){
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 首次存储数据
     * @param context
     * @param key
     * @return
     */
    public static boolean isFirstTime(Context context,String key){
        if(getBoolean(context,key,false)) {
            return false;
        }else{
            putBoolean(context,key,true);
            return true;
        }
    }

    public static boolean contains(Context context,String key){
        return PreferenceManager.getSharedPreferences(context).contains(key);
    }

    public static boolean getBoolean(Context context,String key,boolean defaultBoolean){
        return PreferenceManager.getSharedPreferences(context).getBoolean(key,defaultBoolean);
    }

    /**
     * Boolean类型数据的存储
     * @param context:上下文
     * @param key:对应存储数据的key
     * @param vaule：存储的值
     * @return
     */
    public static boolean putBoolean(Context context,String key,boolean vaule){
        SharedPreferences.Editor editor =
                PreferenceManager.getSharedPreferences(context).edit();
        editor.putBoolean(key,vaule);
        return editor.commit();
    }

    public static int getInt(Context context,String key,int defaultInt){
        return PreferenceManager.getSharedPreferences(context).getInt(key,defaultInt);
    }

    public static boolean putInt(Context context,String key,int vaule){
        SharedPreferences.Editor edit = PreferenceManager.getSharedPreferences(context).edit();
        edit.putInt(key,vaule);
        return edit.commit();
    }

    public static long getLong(final Context context, final String key, final long defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getLong(key, defaultValue);
    }

    public static Long getLong(final Context context, final String key, final Long defaultValue) {
        if (PreferenceManager.getSharedPreferences(context).contains(key)) {
            return PreferenceManager.getSharedPreferences(context).getLong(key, 0);
        } else {
            return null;
        }
    }

    public static boolean putLong(final Context context, final String key, final long pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();

        editor.putLong(key, pValue);

        return editor.commit();
    }

    public static String getString(final Context context, final String key, final String defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getString(key, defaultValue);
    }

    public static boolean putString(final Context context, final String key, final String pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();

        editor.putString(key, pValue);

        return editor.commit();
    }

    public static boolean remove(final Context context, final String key) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();

        editor.remove(key);

        return editor.commit();
    }


}
