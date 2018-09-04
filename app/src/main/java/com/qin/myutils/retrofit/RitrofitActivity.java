package com.qin.myutils.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.qin.myutils.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RitrofitActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    private String BASE_URL = " http://www.izaodao.com/Api/";
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ritrofit);
        ButterKnife.bind(this);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(5000, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mApiService.getAllVedio(true).enqueue(new Callback<RetrofitEntity>() {
                    @Override
                    public void onResponse(Call<RetrofitEntity> call, Response<RetrofitEntity> response) {
                        Log.i("msg","onResponse\n");
                        Log.i("msg",response.body().getData().toString());
                    }

                    @Override
                    public void onFailure(Call<RetrofitEntity> call, Throwable t) {
                        Log.i("msg","onFailure\n");
                    }
                });
                break;
            case R.id.btn2:
                mApiService.getRxAllVedio(true)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<RetrofitEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("msg","onSubscribe\n");
                    }

                    @Override
                    public void onNext(RetrofitEntity r) {
                        Log.i("msg","onNext\n");
                        Log.i("msg", r.getData().toString()+"onNext\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("msg","onError\n");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("msg","onComplete\n");
                    }
                });
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
