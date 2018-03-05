package com.example.disanzhouzhoukaomoni.productdetail.presenter;

import com.example.disanzhouzhoukaomoni.productdetail.view.IView;

/**
 * Created by dell on 2018-03-03  10:04
 * <p>
 * Presenter基类
 */

public abstract class BasePresenter<T extends IView> {
    protected T view;

    public BasePresenter(T view) {
        this.view = view;
        init();
    }

    protected abstract void init();

    public abstract void onDetach();
}
