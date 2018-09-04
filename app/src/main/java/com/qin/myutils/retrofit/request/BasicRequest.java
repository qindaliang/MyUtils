package com.qin.myutils.retrofit.request;

import com.qin.myutils.utils.ContextUtils;
import com.qin.myutils.utils.SpUtils;

/**
 *
 */
public class BasicRequest {
    public String token = (String) SpUtils.getInstance(ContextUtils.getContext()).getString("token", "");

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
