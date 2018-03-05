package com.example.disanzhouzhoukaomoni.productdetail.presenter;

import com.example.disanzhouzhoukaomoni.bean.AddCartBean;
import com.example.disanzhouzhoukaomoni.bean.ProductDetailBean;
import com.example.disanzhouzhoukaomoni.net.OnNetListener;
import com.example.disanzhouzhoukaomoni.productdetail.model.ProductDetailModel;
import com.example.disanzhouzhoukaomoni.productdetail.model.imodel.IProductDetailModel;
import com.example.disanzhouzhoukaomoni.productdetail.view.IProductDetailView;

/**
 * Created by dell on 2018-03-03  10:04
 */

public class ProductDetailPresenter extends BasePresenter<IProductDetailView> {

    private IProductDetailModel iProductDetailModel;

    public ProductDetailPresenter(IProductDetailView view) {
        super(view);
    }

    @Override
    protected void init() {
        iProductDetailModel = new ProductDetailModel();
    }

    public void getDetail(String pid) {
        iProductDetailModel.getProductDetail(pid, new OnNetListener<ProductDetailBean>() {
            @Override
            public void onSuccess(ProductDetailBean productDetailBean) {
                if (productDetailBean.getCode().equals("0")) {
                    view.getDetail(productDetailBean);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    public void addCart(String uid, String pid) {
        iProductDetailModel.addCart(pid, uid, new OnNetListener<AddCartBean>() {
            @Override
            public void onSuccess(AddCartBean addCartBean) {
                if (addCartBean.getCode().equals("0")) {
                    view.addCart(addCartBean);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    /**
     * 防止内存泄漏
     */
    @Override
    public void onDetach() {
        if (view != null) {
            view = null;
        }
    }
}
