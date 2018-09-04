package com.qin.myutils.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    private static Toast mToast = null;

    public static void show(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void show(Context context,int resId, Object... args) {
        show(context,String.format(context.getResources().getString(resId), args));
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
