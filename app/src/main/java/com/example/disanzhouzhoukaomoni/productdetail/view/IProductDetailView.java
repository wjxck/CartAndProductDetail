package com.example.disanzhouzhoukaomoni.productdetail.view;

import com.example.disanzhouzhoukaomoni.bean.AddCartBean;
import com.example.disanzhouzhoukaomoni.bean.ProductDetailBean;

/**
 * Created by dell on 2018-03-03  10:02
 */

public interface IProductDetailView extends IView {
    void getDetail(ProductDetailBean productDetailBean);

    void addCart(AddCartBean addCartBean);
}
