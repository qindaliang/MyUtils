package com.qin.myutils.retrofit;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    //base_user http://www.izaodao.com/Api/AppFiftyToneGraph/videoLink

    @POST("AppFiftyToneGraph/videoLink")
    Call<RetrofitEntity> getAllVedio(@Body boolean no);

    @POST("AppFiftyToneGraph/videoLink")
    Observable<RetrofitEntity> getRxAllVedio(@Body boolean no);
}
