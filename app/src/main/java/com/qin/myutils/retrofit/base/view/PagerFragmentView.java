package com.qin.myutils.retrofit.base.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

/**
 *
 */

public interface PagerFragmentView extends PagerView {

    ViewPager getViewPager();

    FragmentManager getFgManager();

    Class<Fragment>[] getFragments();

}
