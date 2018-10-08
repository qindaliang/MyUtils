package com.qin.myutils.retrofit.test;

/**
 * Create by qindl
 * on 2018/10/8
 */
public class BaseResponse<T> {
    private String resultcode;
    private String reason;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    private T result;


}
