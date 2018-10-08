package com.qin.myutils.retrofit.test;


import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by qindl
 * on 2018/10/8
 */
public class RetrofitUtil {
    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder().readTimeout(5000, TimeUnit.SECONDS)
                .connectTimeout(5000, TimeUnit.SECONDS).build();
    }
    public static Retrofit getRetrofit(){
        return new Retrofit.Builder().client(getOkHttpClient())
                .baseUrl("http://apis.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
