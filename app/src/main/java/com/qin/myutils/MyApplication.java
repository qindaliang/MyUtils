package com.qin.myutils;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.qin.myutils.dagger.AppHolder.AppHolder;
import com.qin.myutils.dagger.component.AppComponent;
import com.qin.myutils.dagger.component.DaggerAppComponent;
import com.qin.myutils.dagger.module.AppModule;
import com.qin.myutils.greendao.data.DaoMaster;
import com.qin.myutils.greendao.data.DaoSession;
import com.qin.myutils.realm.gridview.City;
import com.qin.myutils.utils.ContextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
        Realm.init(this);
        RealmConfiguration build = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(load());
                    }
                })
                .build();
        Realm.setDefaultConfiguration(build);
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

    public  List<City> load(){
        try {
            InputStream stream = getAssets().open("cities.json");
            Gson gson = new GsonBuilder().create();
            List<City> cities = gson.fromJson(new JsonParser().parse(new InputStreamReader(stream)), new TypeToken<List<City>>() {
            }.getType());
            return cities;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
