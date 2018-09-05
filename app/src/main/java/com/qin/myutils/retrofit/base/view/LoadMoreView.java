package com.qin.myutils.retrofit.base.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 *
 */

public interface LoadMoreView {
    Context getContext();
    @Nullable
    SwipeRefreshLayout getSwipeRefreshLayout();

    RecyclerView getRecyclerView();

}
