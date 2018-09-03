package com.qin.myutils.mvp_dagger.di.module;

import com.qin.myutils.mvp_dagger.model.UserModel;
import com.qin.myutils.mvp_dagger.presenter.UserPresenter;
import com.qin.myutils.mvp_dagger.ui.ILoginView;
import com.qin.myutils.mvp_dagger.ui.MvpActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class UserPreModule {
    private MvpActivity mActivity;

    public UserPreModule(MvpActivity activity) {
        mActivity = activity;
    }

    @Provides
    MvpActivity providerActivity(){
        return mActivity;
    }

    @Provides
    UserPresenter providerUserPresenter(MvpActivity mvpActivity){
        return new UserPresenter(mvpActivity);
    }

    @Provides
    UserModel providerUserModule(){
        return new UserModel();
    }
}
