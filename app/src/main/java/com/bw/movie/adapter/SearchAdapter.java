package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.ByKeywordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/3 22:35
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ByKeywordBean.ResultBean> result;


    public SearchAdapter(Context context, List<ByKeywordBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.sousuo_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(result.get(position).getImageUrl()).error(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(((ViewHolder)holder).iv);
        ((ViewHolder) holder).name.setText(result.get(position).getName());
        ((ViewHolder) holder).title.setText("导演:"+result.get(position).getDirector());
        ((ViewHolder) holder).zhuyan.setText("主演:"+result.get(position).getStarring());
        ((ViewHolder) holder).pingfen.setText("评分:"+result.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sousuo_iv)
        ImageView iv;
        @BindView(R.id.sousuo_name)
        TextView name;
        @BindView(R.id.sousuo_title)
        TextView title;
        @BindView(R.id.sousuo_zhuyan)
        TextView zhuyan;
        @BindView(R.id.sousuo_pingfen)
        TextView pingfen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
