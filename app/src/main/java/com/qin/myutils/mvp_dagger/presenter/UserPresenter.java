package com.qin.myutils.mvp_dagger.presenter;

import com.qin.myutils.mvp_dagger.bean.User;
import com.qin.myutils.mvp_dagger.model.OnLoginListener;
import com.qin.myutils.mvp_dagger.model.UserModel;
import com.qin.myutils.mvp_dagger.ui.ILoginView;

public class UserPresenter {

    private UserModel mUserModel;
    private ILoginView mILoginView;

    public UserPresenter(ILoginView iLoginView) {
        mUserModel = new UserModel();
        mILoginView = iLoginView;
    }

    public void login(){
        mILoginView.showLoginDialog();
        mUserModel.login(mILoginView.getUserName(), mILoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                mILoginView.hideLoginDialog();
                mILoginView.toSuccessAct();
            }

            @Override
            public void loginFailure() {
                mILoginView.hideLoginDialog();
                mILoginView.loginFailure();
            }
        });
    }
}
