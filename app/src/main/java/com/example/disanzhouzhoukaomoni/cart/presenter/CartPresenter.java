package com.example.disanzhouzhoukaomoni.cart.presenter;

import com.example.disanzhouzhoukaomoni.bean.CartBean;
import com.example.disanzhouzhoukaomoni.cart.model.CartModel;
import com.example.disanzhouzhoukaomoni.cart.model.imodel.ICartModel;
import com.example.disanzhouzhoukaomoni.cart.view.ICartView;
import com.example.disanzhouzhoukaomoni.net.OnNetListener;
import com.example.disanzhouzhoukaomoni.productdetail.presenter.BasePresenter;

/**
 * Created by dell on 2018-03-03  10:56
 */

public class CartPresenter extends BasePresenter<ICartView> {

    private ICartModel iCartModel;

    public CartPresenter(ICartView view) {
        super(view);
    }

    @Override
    protected void init() {
        iCartModel = new CartModel();
    }

    public void getCarts(String uid) {
        iCartModel.getCarts(uid, new OnNetListener<CartBean>() {
            @Override
            public void onSuccess(CartBean cartBean) {
                if (cartBean.getCode().equals("0")) {
                    view.getCarts(cartBean);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    @Override
    public void onDetach() {
        if (view != null) {
            view = null;
        }
    }
}
