package com.qin.myutils.retrofit.base.model;

import android.support.annotation.DrawableRes;

/**
 *
 */

public interface PagerModel {

    Class[] getFragments();

    String[] getTabString();

    @DrawableRes
    int[] getTabDrawables();

    boolean isAnimation();
}
