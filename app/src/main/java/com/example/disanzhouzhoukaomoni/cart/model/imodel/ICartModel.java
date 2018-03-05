package com.example.disanzhouzhoukaomoni.cart.model.imodel;

import com.example.disanzhouzhoukaomoni.bean.CartBean;
import com.example.disanzhouzhoukaomoni.net.OnNetListener;

/**
 * Created by dell on 2018-03-03  10:57
 */

public interface ICartModel {
    public void getCarts(String uid, OnNetListener<CartBean> onNetListener);
}
