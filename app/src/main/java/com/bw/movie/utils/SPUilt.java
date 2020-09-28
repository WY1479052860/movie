package com.bw.movie.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUilt {
    public static void putString(Context context, String name, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.commit();
    }
    public static String getString(Context context,String name,String key){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }
    public static void putInt(Context context,String name,String key,int value){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key,value);
        edit.commit();
    }

    public static int getInt(Context context,String name,String key){
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getInt(key,0);
    }
}
