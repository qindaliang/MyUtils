package com.qin.myutils.files;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class Cache {
    /**
     * getCacheDir from Environment
     * @param context
     * @return
     */
    public File getCacheDir(Context context) {
        Log.i("getCacheDir", "cache sdcard state: " + Environment.getExternalStorageState());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                Log.i("getCacheDir", "cache dir: " + cacheDir.getAbsolutePath());
                return cacheDir;
            }
        }
        File cacheDir = context.getCacheDir();
        //  File cacheDir = super.getCacheDir();
        Log.i("getCacheDir", "cache dir: " + cacheDir.getAbsolutePath());
        return cacheDir;
    }
}
