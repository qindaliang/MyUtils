package com.qin.myutils.retrofit.base.presenter;

import android.view.View;

/**
 *
 */

public final class EmptyPresenter extends BasePresenter {

    @Override
    public void onCreate() {
    }

    @Override
    public void onRefreshData() {

    }

    @Override
    public View getContentView() {
        return null;
    }

    @Override
    public void cancelNetWork() {

    }

    @Override
    public void netWorkError(Throwable throwable) {

    }
}
