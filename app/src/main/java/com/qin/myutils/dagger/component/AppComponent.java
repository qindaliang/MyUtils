package com.qin.myutils.dagger.component;

import android.content.Context;

import com.qin.myutils.dagger.MyApplication;
import com.qin.myutils.dagger.module.AppModule;
import com.qin.myutils.dagger.scopes.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MyApplication myApplication);

    @ApplicationContext
    Context getContext();
    MyApplication getMyApplication();
}
