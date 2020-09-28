package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.DetailsBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/29 22:34
 */
public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    DetailsBean.ResultBean list;

    public MovieAdapter(Context context, DetailsBean.ResultBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.introduce_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).introduceJj.setText(list.getSummary());
        ((ViewHolder)holder).introduceName.setText(list.getMovieDirector().get(position).getName());
        Glide.with(context).load(list.getMovieDirector().
                get(position).getPhoto())
                .into(((ViewHolder) holder).introduceIv);
        GridLayoutManager manager = new GridLayoutManager(context, 4);
        ((ViewHolder)holder).introduceRvRv.setLayoutManager(manager);
        MovieTwoAdapter adapter = new MovieTwoAdapter(context, list.getMovieActor());
        ((ViewHolder)holder).introduceRvRv.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return list.getMovieDirector().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.introduce_jj)
        TextView introduceJj;
        @BindView(R.id.introduce_iv)
        ImageView introduceIv;
        @BindView(R.id.introduce_name)
        TextView introduceName;
        @BindView(R.id.introduce_rv_rv)
        RecyclerView introduceRvRv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
