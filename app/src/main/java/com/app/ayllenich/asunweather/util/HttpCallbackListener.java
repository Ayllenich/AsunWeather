package com.app.ayllenich.asunweather.util;

/**
 * Created by jasn on 15/12/15.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
