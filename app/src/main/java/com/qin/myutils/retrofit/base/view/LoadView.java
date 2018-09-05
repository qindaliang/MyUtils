package com.qin.myutils.retrofit.base.view;


/**
 *
 */

public interface LoadView extends BaseView {
    void showSuccess();

    void showEmpty();

    void showError(Throwable throwable, boolean isShowError);

    void showError(Throwable throwable);

    void showLoading();

    void triggerInit();

    LoadingPager getLoadPager();


}
