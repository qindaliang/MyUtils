package com.qin.myutils.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.qin.myutils.dagger.scopes.ActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity providerActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providerContext() {
        return mActivity;
    }
}
