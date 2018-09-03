package com.qin.myutils.dagger.component;

import com.qin.myutils.dagger.DaggerActivity;
import com.qin.myutils.dagger.module.ActivityModule;
import com.qin.myutils.dagger.scopes.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(DaggerActivity mainActivity);
    //void inject(...Activity ...Activity);
}
