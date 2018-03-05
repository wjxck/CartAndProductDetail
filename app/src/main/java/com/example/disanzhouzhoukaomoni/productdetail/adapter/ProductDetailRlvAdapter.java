package com.example.disanzhouzhoukaomoni.productdetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.disanzhouzhoukaomoni.R;
import com.example.disanzhouzhoukaomoni.bean.ProductDetailBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dell on 2018-03-03  10:14
 */

public class ProductDetailRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ProductDetailBean.DataBean> dataList;
    private LayoutInflater inflater;

    public ProductDetailRlvAdapter(Context context, List<ProductDetailBean.DataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_detail_rlv_adapter_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        ProductDetailBean.DataBean dataBean = dataList.get(position);
        String[] images = dataBean.getImages().split("\\!");
        myViewHolder.mDetailAdapterSdv.setImageURI(images[0]);
        myViewHolder.mTvDetailTitle.setText(dataBean.getTitle());
        myViewHolder.mTvDetailPrice.setText("￥" + dataBean.getBargainPrice());
        myViewHolder.mTvDetailSj.setText("我是商家" + dataBean.getSellerid());
    }

    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.detail_adapter_sdv)
        SimpleDraweeView mDetailAdapterSdv;
        @BindView(R.id.tv_detail_title)
        TextView mTvDetailTitle;
        @BindView(R.id.tv_detail_price)
        TextView mTvDetailPrice;
        @BindView(R.id.tv_detail_sj)
        TextView mTvDetailSj;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
