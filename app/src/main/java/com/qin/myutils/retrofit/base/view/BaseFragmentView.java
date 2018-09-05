package com.qin.myutils.retrofit.base.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 *
 */
public interface BaseFragmentView extends UIView {
    Bundle getBundle();
    Fragment getFragment();
}
