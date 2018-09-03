package com.qin.myutils.mvp_dagger.ui;

public interface ILoginView {
    void showLoginDialog();
    void hideLoginDialog();
    String getUserName();
    String getPassword();
    void toSuccessAct();
    void loginFailure();
}
