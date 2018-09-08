package com.qin.myutils.mvp_gericity.presenter;

import com.qin.myutils.mvp.bean.User;
import com.qin.myutils.mvp.model.OnLoginListener;
import com.qin.myutils.mvp_gericity.base.UserContract;
import com.qin.myutils.mvp_gericity.model.UserModelImpl;

public class UserPreenter implements UserContract.Presenter<UserContract.View> {

    private UserContract.View mView;
    private UserModelImpl mUserModel;

    public UserPreenter(UserContract.View view) {
        mView = view;
        mUserModel = new UserModelImpl();
    }

    @Override
    public void login() {
        mUserModel.login(mView.getUserName(), mView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                mView.hideLoginDialog();
                mView.toSuccessAct();
            }

            @Override
            public void loginFailure() {
                mView.hideLoginDialog();
                mView.loginFailure();
            }
        });
    }

    @Override
    public void onDettach(UserContract.View v) {
        this.mView = v;
    }

    @Override
    public void detach() {
        mView = null;
    }
}
