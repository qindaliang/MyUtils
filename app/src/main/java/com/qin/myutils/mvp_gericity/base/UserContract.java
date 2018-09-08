package com.qin.myutils.mvp_gericity.base;

public interface UserContract{

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void login();
    }

    interface View extends BaseContract.BaseView{
        void showLoginDialog();
        void hideLoginDialog();
        String getUserName();
        String getPassword();
        void toSuccessAct();
        void loginFailure();
    }
}
