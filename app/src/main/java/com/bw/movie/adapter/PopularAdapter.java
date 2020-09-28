package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.PopularBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/22 23:44
 */
public class PopularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<PopularBean.ResultBean> list;



    public PopularAdapter(Context context, List<PopularBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.index_item_three, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getHorizontalImage()).into(((ViewHolder)holder).ivImage);
        ((ViewHolder)holder).tvName.setText(list.get(position).getDirector());
        ((ViewHolder)holder).tvPf.setText(list.get(position).getScore()+"分");


        ((ViewHolder) holder).indexItemThreeName.setText(list.get(position).getName());
        ((ViewHolder) holder).indexItemThreePf.setText(list.get(position).getScore() + "分");
        Glide.with(context).load(list.get(position).getImageUrl()).into(((ViewHolder) holder).indexItemThreeIv);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_pf)
        TextView tvPf;
        @BindView(R.id.index_item_three_iv)
        ImageView indexItemThreeIv;
        @BindView(R.id.index_item_three_pf)
        TextView indexItemThreePf;
        @BindView(R.id.index_item_three_name)
        TextView indexItemThreeName;
        @BindView(R.id.index_item_three_gp)
        Button indexItemThreeGp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
