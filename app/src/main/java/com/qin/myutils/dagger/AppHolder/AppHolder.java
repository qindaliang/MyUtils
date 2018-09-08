package com.qin.myutils.dagger.AppHolder;

import com.qin.myutils.dagger.component.AppComponent;

public class AppHolder {
    private static AppComponent mAppComponent;

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }
}
