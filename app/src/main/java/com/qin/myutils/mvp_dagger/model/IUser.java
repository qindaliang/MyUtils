package com.qin.myutils.mvp_dagger.model;

public interface IUser {
    void login(String userName, String passWord, OnLoginListener onLoginListener);
}
