package com.qin.myutils.retrofit.upload;

import com.qin.myutils.retrofit.common.BaseResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * 测试接口service-上传相关
 */

public interface HttpUploadService {
    /*上传文件*/
    @Multipart
    @POST("AppYuFaKu/uploadHeadImg")
    Observable<BaseResponse<UploadResulte>> uploadImage(@Part("uid") RequestBody uid, @Part("auth_key") RequestBody auth_key,
                                                        @Part MultipartBody.Part file);
}
