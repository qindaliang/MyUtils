package com.qin.myutils.mvp_dagger.model;

import com.qin.myutils.mvp_dagger.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailure();
}
