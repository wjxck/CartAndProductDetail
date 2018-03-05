package com.example.disanzhouzhoukaomoni.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.disanzhouzhoukaomoni.R;
import com.example.disanzhouzhoukaomoni.bean.CartBean;
import com.example.disanzhouzhoukaomoni.cart.adapter.MyCartAdapter;
import com.example.disanzhouzhoukaomoni.cart.eventbus.MessageEvent;
import com.example.disanzhouzhoukaomoni.cart.eventbus.PriceAndCountEvent;
import com.example.disanzhouzhoukaomoni.cart.presenter.CartPresenter;
import com.example.disanzhouzhoukaomoni.cart.view.ICartView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends AppCompatActivity implements ICartView {

    @BindView(R.id.fan)
    ImageView mFan;
    @BindView(R.id.bj)
    TextView mBj;
    @BindView(R.id.elv)
    ExpandableListView mElv;
    @BindView(R.id.check_all)
    CheckBox mCheckAll;
    @BindView(R.id.share)
    Button mShare;
    @BindView(R.id.file)
    Button mFile;
    @BindView(R.id.dele)
    Button mDele;
    @BindView(R.id.caozuo)
    LinearLayout mCaozuo;
    @BindView(R.id.price_all)
    TextView mPriceAll;
    @BindView(R.id.js)
    Button mJs;
    @BindView(R.id.jiesuan)
    RelativeLayout mJiesuan;
    private List<List<CartBean.DataBean.ListBean>> lists = new ArrayList<>();
    private MyCartAdapter adapter;
    private Boolean bj = false;
    private AlertDialog show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        CartPresenter cartPresenter = new CartPresenter(this);
        cartPresenter.getCarts("72");

        mCaozuo.setVisibility(View.GONE);

        mCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.changeAllListCbState(mCheckAll.isChecked());
            }
        });
    }

    @OnClick({R.id.bj, R.id.share, R.id.file, R.id.dele, R.id.js})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bj:
                if (bj == false) {
                    bj = true;
                    mCaozuo.setVisibility(View.VISIBLE);
                    mJiesuan.setVisibility(View.GONE);
                    mBj.setText("完成");
                } else {
                    bj = false;
                    mCaozuo.setVisibility(View.GONE);
                    mJiesuan.setVisibility(View.VISIBLE);
                    mBj.setText("编辑");
                }
                break;
            case R.id.share:
                break;
            case R.id.file:
                break;
            case R.id.dele:
                break;
            case R.id.js:
                break;
        }
    }

    @Override
    public void getCarts(CartBean cartBean) {
        double price = 0;
        int num = 0;

        Toast.makeText(getApplicationContext(), cartBean.getCode(), Toast.LENGTH_SHORT).show();
        List<CartBean.DataBean> data = cartBean.getData();

        for (int i = 0; i < data.size(); i++) {
            data.get(i).setBj("编辑");
            data.get(i).setWC("完成");
            List<CartBean.DataBean.ListBean> list = data.get(i).getList();
            lists.add(list);
        }
        //设置适配器
        adapter = new MyCartAdapter(this, data, lists);
        mElv.setAdapter(adapter);

        for (int i = 0; i < data.size(); i++) {
            //默认二级列表展开
            mElv.expandGroup(i);
        }
        //取消小箭头
        mElv.setGroupIndicator(null);
        //默认全选
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                CartBean.DataBean.ListBean listBean = lists.get(i).get(j);
                price += listBean.getNum() * listBean.getPrice();
                num += listBean.getNum();
            }
        }
        mJs.setText("去支付(" + num + ")");
        mPriceAll.setText("￥" + price);
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckAll.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mJs.setText("去支付(" + event.getCount() + ")");
        mPriceAll.setText("￥" + event.getCount() * event.getPrice());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        ButterKnife.bind(this).unbind();
        EventBus.getDefault().unregister(this);

    }

}
