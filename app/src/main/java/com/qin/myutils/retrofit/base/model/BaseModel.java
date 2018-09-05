package com.qin.myutils.retrofit.base.model;

import com.qin.myutils.retrofit.base.presenter.BasePresenter;

/**
 *
 */

public class BaseModel<T extends BasePresenter> {
    protected T mPresenter;

    public BaseModel(T presenter) {
        mPresenter = presenter;
    }


}
