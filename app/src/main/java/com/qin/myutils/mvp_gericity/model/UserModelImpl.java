package com.qin.myutils.mvp_gericity.model;

import com.qin.myutils.mvp.bean.User;
import com.qin.myutils.mvp.model.OnLoginListener;

public class UserModelImpl implements UserModel {

    @Override
    public void login(String name, String pwd, OnLoginListener onLoginListener) {
        if (name.equals("qin")&&pwd.equals("000")){
            User user = new User();
            user.userName = "qin";
            user.passWord = "000";
            onLoginListener.loginSuccess(user);
        }
        else {
            onLoginListener.loginFailure();
        }
    }
}
