package com.qin.myutils.receiver;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.qin.myutils.R;
import com.qin.myutils.permission.PermissionUtils;
import com.qin.myutils.receiver.gps.GpsBroadcastReceiver;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ReceiverActivity extends AppCompatActivity {

    private PermissionUtils mPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        mPermission = new PermissionUtils(this);
        mPermission.checkPermissions();
        mPermission.setPermissionListener(new PermissionUtils.PermissionListener() {
            @Override
            public void onCancel() {
                finish();
            }

            @Override
            public void onSetting() {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, 100);
            }
        });
        IntentFilter filter = new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION);
        registerReceiver(new GpsBroadcastReceiver(),filter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Log.i("EXCEPTION","onActivityResult");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        }else{
                            mPermission.onPermissionGranted(permissions[i]);
                        }
                    }
                }
                break;
        }
    }
}
