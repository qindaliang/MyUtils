package com.qin.myutils.mvp.model;

import com.qin.myutils.mvp.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);
    void loginFailure();
}
