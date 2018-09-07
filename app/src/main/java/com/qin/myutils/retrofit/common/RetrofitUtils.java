package com.qin.myutils.retrofit.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qin.myutils.retrofit.cookie.CookieManger;
import com.qin.myutils.retrofit.exception.RetryWhenNetworkException;
import com.qin.myutils.retrofit.interceptor.HttpCacheInterceptor;
import com.qin.myutils.retrofit.interceptor.HttpHeaderInterceptor;
import com.qin.myutils.retrofit.interceptor.LoggingInterceptor;
import com.qin.myutils.retrofit.upload.BaseApi;
import com.qin.myutils.retrofit.upload.HttpOnNextListener;
import com.qin.myutils.retrofit.upload.ProgressSubscriber;
import com.qin.myutils.utils.ContextUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */

public class RetrofitUtils {

    private static String API_HOST;

    public static OkHttpClient.Builder getOkHttpClientBuilder() {
        File cacheFile = new File(ContextUtils.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        return new OkHttpClient.Builder()
                .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HttpHeaderInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                // .sslSocketFactory(SslContextFactory.getSSLSocketFactoryForTwoWay())
                // https认证 如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                // .hostnameVerifier(new SafeHostnameVerifier())
               // .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
               // .cookieJar(new CookieManger(ContextUtils.getContext()))
                .cache(cache);
    }

    public static Retrofit.Builder getRetrofitBuilder(String baseUrl) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        OkHttpClient okHttpClient = RetrofitUtils.getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl);



    }

    public static void doUpload(BaseApi basePar,String baseUrl){
        /*rx处理*/
        ProgressSubscriber subscriber = new ProgressSubscriber(basePar);
        Observable observable = basePar.getObservable(getRetrofitBuilder(baseUrl).build())
                /*失败后的retry配置*/
                .retryWhen(new RetryWhenNetworkException(basePar.getRetryCount(),
                        basePar.getRetryDelay(), basePar.getRetryIncreaseDelay()))
                /*生命周期管理*/
//                .compose(basePar.getRxAppCompatActivity().bindToLifecycle())
                .compose(basePar.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.PAUSE))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                /*结果判断*/
                .map((Function) basePar);

        /*链接式对象返回*/
        SoftReference<HttpOnNextListener> httpOnNextListener = basePar.getListener();
        if (httpOnNextListener != null && httpOnNextListener.get() != null) {
            httpOnNextListener.get().onNext(observable);
        }
        /*数据回调*/
        observable.subscribe((Observer) subscriber);
    }

//    public static OkHttpClient.Builder getDefaultOkHttpClientBuilder() {
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }
//        return new OkHttpClient.Builder()
//                .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
//                .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
//                .retryOnConnectionFailure(true)
//                .addInterceptor(interceptor)
//                .build();
//    }
//
//    public static Retrofit.Builder getDefaultRetrofitBuilder(String baseUrl) {
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
//        OkHttpClient okHttpClient = RetrofitUtils.getOkHttpClientBuilder().build();
//        return new Retrofit.Builder()
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .baseUrl(baseUrl);
//    }

    /**
     * 生成对应的RequestBody
     *
     * @param param 参数
     * @return RequestBody
     */
    public static RequestBody createRequestBody(int param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(long param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(float param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(Object param) {
        return createRequestBody(String.valueOf(param));
    }

    public static RequestBody createRequestBody(double param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(String param) {
        return RequestBody.create(MediaType.parse("text/plain"), param);
    }

    protected RequestBody createRequestBody(File param) {
        return RequestBody.create(MediaType.parse("image/*"), param);
    }

    public static HashMap<String, RequestBody> createRequestBody(HashMap<String, Object> params) {
        HashMap<String, RequestBody> RequestBodyHashMap = new HashMap<>();
        Set<String> strings = params.keySet();
        for (String str : strings) {
            Object param = params.get(str);
            RequestBodyHashMap.put(str, createRequestBody(param));
        }
        return RequestBodyHashMap;
    }

    /**
     * 次方法获取的bitmap为原始大小,图片文件过大可能造成oom
     *
     * @param images 图片集合
     */
    public static HashMap<String, RequestBody> creatRequestBodyImagesFiles(List<String> images) {
        if (images == null) {
            return null;
        }
        HashMap<String, RequestBody> photoRequestMap = new HashMap<>();
        int size = images.size();
        for (int i = 0; i < size; i++) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = BitmapFactory.decodeFile(images.get(i));
            //转化为二进制流数组
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            bitmap.recycle();
            photoRequestMap.put("file" + i + "\";filename=\"" +
                    System.currentTimeMillis(), RequestBody.create(MediaType.parse("multipart/form-data"), byteArray));
        }
        return photoRequestMap;
    }

    /**
     * 建议调用此方法前,先将bitmap压缩.
     *
     * @param images 图片集合
     */
    public static HashMap<String, RequestBody> creatRequestBodyBitmap(List<Bitmap> images) {
        if (images == null) {
            return null;
        }
        HashMap<String, RequestBody> photoRequestMap = new HashMap<>();
        int size = images.size();
        for (int i = 0; i < size; i++) {
            Bitmap bitmap = images.get(i);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //转化为二进制流数组
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            photoRequestMap.put("file" + i + "\";filename=\"" +
                    System.currentTimeMillis(), RequestBody.create(MediaType.parse("multipart/form-data"), byteArray));
        }
        return photoRequestMap;
    }

    private RequestBody buildMultipartFormRequestBody(List<File> files, String filesKey, HashMap<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        Set<String> strings = params.keySet();
        for (String key : strings) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                    RequestBody.create(null, params.get(key)));
        }
        if (files == null) {
            files = new ArrayList<>();
        }
        int size = files.size();
        if (size == 0) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + filesKey + "\""),
                    RequestBody.create(null, "[]"));
        }
        for (int i = 0; i < size; i++) {
            //TODO 根据文件名设置contentType
            builder.addPart(Headers.of("Content-Disposition",
                    "form-data; name=\"" + filesKey + "\"; fileName=\"" + System.currentTimeMillis() + "\""),
                    RequestBody.create(MediaType.parse("multipart/form-data"), files.get(i)));
        }
        return builder.build();
    }
}
