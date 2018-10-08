package com.qin.myutils.retrofit.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.qin.myutils.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitTestActivity extends AppCompatActivity {

    private static final String TAG = RetrofitTestActivity.class.getName();
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        mApiService = RetrofitUtil.getRetrofit().create(ApiService.class);
    }

    public void get(View view){
        Call<Menu> category = mApiService.getMenu("category");
        category.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
               // Log.i(TAG, "onResponse: "+response.body().toString());
                Menu body = response.body();
                Log.i(TAG, "onResponse: "+body.getResult().size());
                Log.i(TAG, "onResponse: "+body.getResult().get(1).getList().size());
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable c) {
                Log.i(TAG, "onResponse: "+c.getMessage());
            }
        });
    }
}
