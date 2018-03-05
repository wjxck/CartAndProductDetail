package com.example.disanzhouzhoukaomoni.net;

import com.example.disanzhouzhoukaomoni.bean.AddCartBean;
import com.example.disanzhouzhoukaomoni.bean.CartBean;
import com.example.disanzhouzhoukaomoni.bean.DeleteCartBean;
import com.example.disanzhouzhoukaomoni.bean.ProductDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dell on 2018-03-03  09:42
 */

public interface ServiceApi {
    /**
     * 商品详情
     *
     * @param pid
     * @return
     */
    @GET(UrlApi.PRODUCTDETAIL_URL)
    Observable<ProductDetailBean> getProductDetai(@Query("pid") String pid);

    /**
     * 添加购物车
     *
     * @param uid
     * @param pid
     * @return
     */
    @GET(UrlApi.ADDCART_URL)
    Observable<AddCartBean> addCart(@Query("uid") String uid, @Query("pid") String pid);

    /**
     * 查询购物车
     *
     * @param uid
     * @return
     */
    @GET(UrlApi.CART_URL)
    Observable<CartBean> getCarts(@Query("uid") String uid);

    /**
     * 删除购物车
     *
     * @param uid
     * @param pid
     * @return
     */
    @GET(UrlApi.DELETECART_URL)
    Observable<DeleteCartBean> deleteCart(@Query("uid") String uid, @Query("pid") String pid);
}
