package com.qin.myutils.mvp.model;

import com.qin.myutils.mvp.bean.User;

public class UserModel implements IUser {

    private User mUser;

    @Override
    public void login(String userName, String passWord, OnLoginListener onLoginListener) {
        if ("qin".equals(userName) && ("000".equals(passWord))) {
            mUser = new User();
            mUser.setUserName(userName);
            mUser.setPassWord(passWord);
            onLoginListener.loginSuccess(mUser);
        } else {
            onLoginListener.loginFailure();
        }
    }
}
