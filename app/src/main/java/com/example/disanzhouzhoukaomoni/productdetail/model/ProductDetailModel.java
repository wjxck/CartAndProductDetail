package com.example.disanzhouzhoukaomoni.productdetail.model;

import com.example.disanzhouzhoukaomoni.bean.AddCartBean;
import com.example.disanzhouzhoukaomoni.bean.ProductDetailBean;
import com.example.disanzhouzhoukaomoni.net.OnNetListener;
import com.example.disanzhouzhoukaomoni.net.RetrofitHelper;
import com.example.disanzhouzhoukaomoni.net.ServiceApi;
import com.example.disanzhouzhoukaomoni.productdetail.model.imodel.IProductDetailModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2018-03-03  09:57
 */

public class ProductDetailModel implements IProductDetailModel {
    /**
     * 商品详情
     *
     * @param pid
     * @param onNetListener
     */
    @Override
    public void getProductDetail(String pid, final OnNetListener<ProductDetailBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getServiceApi();
        serviceApi.getProductDetai(pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductDetailBean>() {
                    @Override
                    public void accept(ProductDetailBean productDetailBean) throws Exception {
                        onNetListener.onSuccess(productDetailBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onNetListener.onFailure(throwable);
                    }
                });
    }

    /**
     * 添加购物车
     *
     * @param pid
     * @param uid
     * @param onNetListener
     */
    @Override
    public void addCart(String pid, String uid, final OnNetListener<AddCartBean> onNetListener) {
        ServiceApi serviceApi = RetrofitHelper.getServiceApi();
        serviceApi.addCart(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AddCartBean>() {
                    @Override
                    public void accept(AddCartBean addCartBean) throws Exception {
                        onNetListener.onSuccess(addCartBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onNetListener.onFailure(throwable);
                    }
                });

    }
}
