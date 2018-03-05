package com.example.disanzhouzhoukaomoni.productdetail.model.imodel;

import com.example.disanzhouzhoukaomoni.bean.AddCartBean;
import com.example.disanzhouzhoukaomoni.bean.ProductDetailBean;
import com.example.disanzhouzhoukaomoni.net.OnNetListener;
import com.example.disanzhouzhoukaomoni.productdetail.model.ProductDetailModel;

/**
 * Created by dell on 2018-03-03  09:57
 */

public interface IProductDetailModel {
    public void getProductDetail(String pid, OnNetListener<ProductDetailBean> onNetListener);

    public void addCart(String pid, String uid, OnNetListener<AddCartBean> onNetListener);

}
