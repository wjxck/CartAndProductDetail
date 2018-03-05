package com.example.disanzhouzhoukaomoni.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.disanzhouzhoukaomoni.R;
import com.example.disanzhouzhoukaomoni.bean.AddCartBean;
import com.example.disanzhouzhoukaomoni.bean.ProductDetailBean;
import com.example.disanzhouzhoukaomoni.productdetail.adapter.ProductDetailRlvAdapter;
import com.example.disanzhouzhoukaomoni.productdetail.presenter.ProductDetailPresenter;
import com.example.disanzhouzhoukaomoni.productdetail.view.IProductDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 展示商品详情
 */
public class MainActivity extends AppCompatActivity implements IProductDetailView {

    @BindView(R.id.xq_rlv)
    RecyclerView xqRlv;
    @BindView(R.id.bt_addcart)
    Button btAddcart;
    private ProductDetailPresenter productDetailPresenter;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        productDetailPresenter = new ProductDetailPresenter(this);
        productDetailPresenter.getDetail("80");
        //播放视频
        String url = "http://txmov2.a.yximgs.com/upic/2017/06/22/23/BMjAxNzA2MjIyMzEyMThfNzAyMzQ4Ml8yNDU3OTA1MjA1XzJfMw==_b.mp4";
        new PlayerView(this)
                .setTitle("妖王现世")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(url)
                .startPlay();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        xqRlv.setLayoutManager(layoutManager);
    }

    //点击事件
    @OnClick(R.id.bt_addcart)
    public void onViewClicked() {
        productDetailPresenter.addCart("72", "80");
        //加购成功后，跳转到购物车页面
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        startActivity(intent);
    }

    //展示商品详情
    @SuppressLint("WrongConstant")
    @Override
    public void getDetail(ProductDetailBean productDetailBean) {
        if (productDetailBean.getCode().equals("0")) {
            Toast.makeText(MainActivity.this, productDetailBean.getMsg(), Toast.LENGTH_SHORT).show();

            ProductDetailBean.DataBean dataBean = productDetailBean.getData();
            List<ProductDetailBean.DataBean> dataBeanList = new ArrayList<>();
            dataBeanList.add(dataBean);

            ProductDetailRlvAdapter rlvAdapter = new ProductDetailRlvAdapter(this, dataBeanList);
            xqRlv.setAdapter(rlvAdapter);
        } else {
            Toast.makeText(MainActivity.this, productDetailBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    //添加购物车
    @Override
    public void addCart(AddCartBean addCartBean) {
        if (addCartBean.getCode().equals("0")) {
            Toast.makeText(getApplicationContext(), addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        ButterKnife.bind(this).unbind();

        //防止内存泄漏
        productDetailPresenter.onDetach();
    }


}
