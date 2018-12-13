package com.ivyzh.baselibrary.http;

public interface HttpCallBack<String> {
    void onError(Exception e);

    void onSuccess(String result);
}
