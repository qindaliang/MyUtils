package com.qin.myutils.retrofit.base.model;

/**
 *
 */

public interface LoadDataModel<T> {
    T loadData(int pageNum, int pageSize, boolean isRefresh);
}
