package com.example.disanzhouzhoukaomoni.net;

/**
 * Created by dell on 2018-03-03  09:55
 * <p>
 * 接口回调类
 */

public interface OnNetListener<T> {
    void onSuccess(T t);

    void onFailure(Throwable throwable);
}
