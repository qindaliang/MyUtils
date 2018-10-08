package com.qin.myutils.retrofit.test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Create by qindl
 * on 2018/10/8
 */
public interface ApiService {
    @GET("cook/{category}?key=6831faf6e2ac46b22d5652fb220c8cfc")
    Call<BaseResponse<Menu>> getMenu1(@Path("category") String category);

    @GET("cook/{category}?key=6831faf6e2ac46b22d5652fb220c8cfc")
    Call<Menu> getMenu(@Path("category") String category);
}
