package com.qin.myutils;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.qin.myutils.dagger.AppHolder.AppHolder;
import com.qin.myutils.dagger.component.AppComponent;
import com.qin.myutils.dagger.component.DaggerAppComponent;
import com.qin.myutils.dagger.module.AppModule;
import com.qin.myutils.greendao.data.DaoMaster;
import com.qin.myutils.greendao.data.DaoSession;
import com.qin.myutils.utils.ContextUtils;

public class MyApplication extends Application {

    private static MyApplication mInStance;
    private AppComponent mAppComponent;
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent().inject(this);
        ContextUtils.init(this);
        mInStance = this;
        setupGreenDao();
    }

    public static MyApplication getInstance(){
        return  mInStance;
    }
    private void setupGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "shopping", null);
        SQLiteDatabase sqLiteDatabase = devOpenHelper.getWritableDatabase();
        mDaoSession = new DaoMaster(sqLiteDatabase).newSession();
    }

    public DaoSession getDaoSeesion() {
        return mDaoSession;
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
            AppHolder.setAppComponent(mAppComponent);
        }
        return mAppComponent;
    }
}
