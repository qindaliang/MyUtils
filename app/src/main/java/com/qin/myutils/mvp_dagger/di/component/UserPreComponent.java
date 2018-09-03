package com.qin.myutils.mvp_dagger.di.component;

import com.qin.myutils.mvp_dagger.di.module.UserPreModule;
import com.qin.myutils.mvp_dagger.ui.MvpActivity;

import dagger.Component;

@Component(modules = UserPreModule.class)
public interface UserPreComponent {
    void inject(MvpActivity mvpActivity);
}
