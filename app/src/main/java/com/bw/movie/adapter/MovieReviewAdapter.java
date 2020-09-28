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
import com.bw.movie.bean.MyMovieBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/2 21:32
 */
public class MovieReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<MyMovieBean.ResultBean> result;


    public MovieReviewAdapter(Context context, List<MyMovieBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.review_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).commentName.setText(result.get(position).getCommentUserName());
        ((ViewHolder)holder).commentTxt.setText(result.get(position).getCommentTime()+"");
        ((ViewHolder)holder).commentTxt1.setText(result.get(position).getCommentContent());
        Glide.with(context)
                .load(result.get(position).getCommentHeadPic())
                .apply(new RequestOptions().circleCrop())
                .into(((ViewHolder) holder).commentIv);

    }


    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.comment_iv)
        ImageView commentIv;
        @BindView(R.id.comment_name)
        TextView commentName;
        @BindView(R.id.comment_txt)
        TextView commentTxt;
        @BindView(R.id.comment_txt1)
        TextView commentTxt1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
