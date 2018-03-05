package com.example.disanzhouzhoukaomoni.cart.model;

import com.example.disanzhouzhoukaomoni.bean.CartBean;
import com.example.disanzhouzhoukaomoni.cart.model.imodel.ICartModel;
import com.example.disanzhouzhoukaomoni.net.OnNetListener;
import com.example.disanzhouzhoukaomoni.net.RetrofitHelper;
import com.example.disanzhouzhoukaomoni.net.ServiceApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2018-03-03  10:57
 */

public class CartModel implements ICartModel {
    @Override
    public void getCarts(String uid, final OnNetListener<CartBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getServiceApi();
        serviceApi.getCarts(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CartBean>() {
                    @Override
                    public void accept(CartBean cartBean) throws Exception {
                        onNetListener.onSuccess(cartBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onNetListener.onFailure(throwable);
                    }
                });
    }
}
