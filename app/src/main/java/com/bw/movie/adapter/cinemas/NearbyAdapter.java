package com.bw.movie.adapter.cinemas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.NearbyBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/28 22:39
 */
public class NearbyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<NearbyBean.ResultBean> list;


    public NearbyAdapter(Context context, List<NearbyBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.nearby_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).tvName.setText(list.get(position).getName());
        ((ViewHolder)holder).tvXin.setText(list.get(position).getAddress());
        Glide.with(context).load(list.get(position).getLogo()).into(((ViewHolder) holder).iv);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_xin)
        TextView tvXin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
