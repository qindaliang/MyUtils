package com.qin.myutils.mvp_gericity.model;

public class BaseModel <T> {
    public T mBasePresenter;

    public T getBasePresenter() {
        return mBasePresenter;
    }

    public void setBasePresenter(T basePresenter) {
        mBasePresenter = basePresenter;
    }
}
