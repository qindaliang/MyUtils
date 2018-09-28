package com.qin.myutils.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qin.myutils.R;

import io.realm.Realm;

public class RealmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserRealm userRealm = realm.createObject(UserRealm.class);
        userRealm.setAge("20");
        userRealm.setName("qqq");
        realm.commitTransaction();
    }
}
