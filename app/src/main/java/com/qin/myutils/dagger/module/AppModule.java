package com.qin.myutils.dagger.module;

import android.app.Application;
import android.content.Context;

import com.qin.myutils.dagger.MyApplication;
import com.qin.myutils.dagger.scopes.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    MyApplication mApplication;

    public AppModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    MyApplication providerApplication(){
        return mApplication;
    }

    @Provides
    @ApplicationContext
    @Singleton
    Context providerContext(){
        return mApplication;
    }
}
