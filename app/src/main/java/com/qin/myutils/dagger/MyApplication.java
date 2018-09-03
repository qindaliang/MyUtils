package com.qin.myutils.dagger;

import android.app.Application;

import com.qin.myutils.dagger.AppHolder.AppHolder;
import com.qin.myutils.dagger.component.AppComponent;
import com.qin.myutils.dagger.component.DaggerAppComponent;
import com.qin.myutils.dagger.module.AppModule;

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent().inject(this);
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
            AppHolder.setAppComponent(mAppComponent);
        }
        return mAppComponent;
    }
}
