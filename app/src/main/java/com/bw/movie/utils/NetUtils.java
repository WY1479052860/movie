package com.bw.movie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.bw.movie.base.App;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author WangYi
 * @description: 网络工具类
 * @date :2020/5/19 19:04
 */
public class NetUtils {
     private Apis apis;
    //静态内部类单例模式
    private NetUtils(){
        initRetrofit();
    }

    public static class SingleInstance{
        private static final NetUtils INSTANCE=new NetUtils();
    }
    public static NetUtils getInstance() {
        return SingleInstance.INSTANCE;
    }
    public boolean isNet(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info!=null){
            return true;
        }
        return false;
    }
    private void initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(new WorkInter())
                .addInterceptor(httpLoggingInterceptor);
        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://mobile.bwstudent.com/movieApi/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apis=retrofit.create(Apis.class);
    }
    public Apis getApis() {
        return apis;
    }


    private class WorkInter implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            SharedPreferences login = App.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
            String userId = login.getString("userId", "");
            String sessionId = login.getString("sessionId", "");
            if(TextUtils.isEmpty(userId)||TextUtils.isEmpty(sessionId)){
                 return chain.proceed(request);
            }
            Request build = request.newBuilder().addHeader("userId", userId)
                    .addHeader("sessionId", sessionId)
                    .build();
            Log.i("xxx",userId);
            Log.i("xxx",sessionId);
            return chain.proceed(build);
        }
    }
    public RequestBody getRequestBody(List<File>files, HashMap<String,String>map){
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String,String>entry:map.entrySet()){
            builder.addFormDataPart(entry.getKey(), entry.getValue() + "");
        }
        for (int i = 0; i < files.size(); i++) {
            builder.addFormDataPart("image",files.get(i).getName(),
                    RequestBody.create(MediaType.parse("image/jepg"),files.get(i)));
        }
        return builder.build();
    }




}
