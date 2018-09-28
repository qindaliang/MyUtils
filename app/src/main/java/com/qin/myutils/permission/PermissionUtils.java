package com.qin.myutils.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by qindl
 * on 2018/9/15
 */
public class PermissionUtils {

    private Activity mContext;
    private String[] permission = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.SEND_SMS};

    public PermissionUtils(Activity context) {
        mContext = context;
    }

    public void checkPermissions() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(mContext, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(mContext, deniedPermissions, 100);
        }
    }

    public void onPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkGPSIsOpen()) {
                    new AlertDialog.Builder(mContext)
                            .setTitle("温馨提示")
                            .setMessage("此应用需要获取定位权限才能正常使用！")
                            .setNegativeButton("取消",
                                    (dialog, which) -> mPermissionListener.onCancel())
                            .setPositiveButton("前往设置",
                                    (dialog, which) -> mPermissionListener.onSetting())
                            .setCancelable(false)
                            .show();
                }
                break;
        }
    }

    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private PermissionListener mPermissionListener;

    public interface PermissionListener {
        void onCancel();

        void onSetting();
    }

    public void setPermissionListener(PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
    }

}
