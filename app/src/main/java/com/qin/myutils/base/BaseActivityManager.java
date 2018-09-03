package com.qin.myutils.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class BaseActivityManager {

    private BaseActivityManager mManager = null;
    private List<Activity> mActivityList = new ArrayList<>();

    private BaseActivityManager() {
    }

    public BaseActivityManager getInstance() {
        if (mManager == null) {
            synchronized (BaseActivityManager.class) {
                if (mManager == null) {
                    mManager = new BaseActivityManager();
                }
            }
        }
        return mManager;
    }

    public synchronized void add(Activity activity){
        mActivityList.add(activity);
    }

    public int size(){
        return mActivityList.size();
    }
    public synchronized void removeActivity(Activity activity) {
        if (mActivityList.contains(activity)) {
            mActivityList.remove(activity);
        }
    }
    public synchronized void clear() {
        for (int i = mActivityList.size() - 1; i > -1; i--) {
            Activity activity = mActivityList.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivityList.size();
        }
    }

    public synchronized void clearTop() {
        for (int i = mActivityList.size() - 2; i > -1; i--) {
            Activity activity = mActivityList.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivityList.size() - 1;
        }
    }

    public void AppExit(Context context) {
        try {
            clear();
            ActivityManager activityMgr =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public boolean isAppExit() {
        return mActivityList == null || mActivityList.isEmpty();
    }
}
