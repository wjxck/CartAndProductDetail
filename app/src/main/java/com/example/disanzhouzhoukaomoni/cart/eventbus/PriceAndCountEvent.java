package com.example.disanzhouzhoukaomoni.cart.eventbus;

/**
 * Created by dell on 2018-03-03  11:22
 */

public class PriceAndCountEvent {
    private int price;
    private int count;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
