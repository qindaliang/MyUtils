package com.qin.myutils.dagger;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qin.myutils.R;
import com.qin.myutils.dagger.AppHolder.AppHolder;
import com.qin.myutils.dagger.component.DaggerActivityComponent;
import com.qin.myutils.dagger.scopes.ApplicationContext;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    @Inject
    @ApplicationContext
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        DaggerActivityComponent.builder().appComponent(AppHolder.getAppComponent()).build().inject(this);
        Log.i("msg", mContext.toString());
    }
}
