package com.qin.myutils.mvp.model;

public interface IUser {
    void login(String userName,String passWord,OnLoginListener onLoginListener);
}
