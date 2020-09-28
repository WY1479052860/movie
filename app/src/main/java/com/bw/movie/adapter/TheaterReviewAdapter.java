package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.CinemaCommentBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description: 影院评论适配器
 * @date :2020/6/4 22:58
 */
public class TheaterReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<CinemaCommentBean.ResultBean> list;


    public TheaterReviewAdapter(Context context, List<CinemaCommentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.theaterreview_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getCommentHeadPic())
                .apply(RequestOptions.bitmapTransform(new CircleCrop())).
                into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).tvName.setText(list.get(position).getCommentUserName());
        ((ViewHolder)holder).tvCity.setText(list.get(position).getCommentContent());
        String date = new SimpleDateFormat("yy--MM--dd").format(
                new Date(list.get(position).getCommentTime()));
        ((ViewHolder)holder).tvTime.setText(date+"");

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
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_city)
        TextView tvCity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
