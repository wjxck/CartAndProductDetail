package com.example.disanzhouzhoukaomoni.cart.view;

import com.example.disanzhouzhoukaomoni.bean.CartBean;
import com.example.disanzhouzhoukaomoni.productdetail.view.IView;

/**
 * Created by dell on 2018-03-03  10:56
 */

public interface ICartView extends IView {
    void getCarts(CartBean cartBean);
}
