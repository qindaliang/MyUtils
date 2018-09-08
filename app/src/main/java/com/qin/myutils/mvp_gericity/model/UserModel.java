package com.qin.myutils.mvp_gericity.model;

import com.qin.myutils.mvp.model.OnLoginListener;


public interface UserModel{
    void login(String name, String pwd, OnLoginListener onLoginListener);
}
