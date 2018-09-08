package com.qin.myutils.mvp_gericity.base;

public interface BaseContract {

    interface BasePresenter<T> {
        void onDettach(T v);
        void detach();
    }

    interface BaseView {
        void showError();
    }
}
