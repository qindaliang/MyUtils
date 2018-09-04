package com.qin.myutils.retrofit.request;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import com.qin.myutils.utils.KeyTools;

/**
 * Description:登录请求实体类
 */

public class LoginRequest extends BasicRequest {
    private String userId;
    private String password;
    private String appKey;

    public LoginRequest(Activity activity) {
        appKey = generateAppKey(activity);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * imei + timestamp md5 得到app key
     *
     * @param context
     * @return
     */
    public static String generateAppKey(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            String timeStamp = System.currentTimeMillis() + "";
            return KeyTools.getMD5(deviceId + timeStamp);
        }
        return null;
    }
}
