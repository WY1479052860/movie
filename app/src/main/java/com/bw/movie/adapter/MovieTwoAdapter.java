package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.DetailsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/29 23:15
 */
public class MovieTwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DetailsBean.ResultBean.MovieActorBean> movieActor;


    public MovieTwoAdapter(Context context, List<DetailsBean.ResultBean.MovieActorBean> movieActor) {
        this.context = context;
        this.movieActor = movieActor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.introduce_item_one, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(movieActor.get(position).getPhoto()).into(((ViewHolder)holder).introduceItemOneIv);
        ((ViewHolder)holder).introduceItemOneName.setText(movieActor.get(position).getName());
        ((ViewHolder)holder).introduceItemOneTt.setText(movieActor.get(position).getRole());
    }

    @Override
    public int getItemCount() {
        return movieActor.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.introduce_item_one_iv)
        ImageView introduceItemOneIv;
        @BindView(R.id.introduce_item_one_name)
        TextView introduceItemOneName;
        @BindView(R.id.introduce_item_one_tt)
        TextView introduceItemOneTt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
