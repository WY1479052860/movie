package com.bw.movie.adapter.cinemas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.RecommendCinemasBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/28 21:51
 */
public class RecommendMovieAdapter extends RecyclerView.Adapter<RecommendMovieAdapter.Holder> {
    Context context;
    List<RecommendCinemasBean.ResultBean> list;



    public RecommendMovieAdapter(Context context, List<RecommendCinemasBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recommendmovie_item, null);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvXin.setText(list.get(position).getAddress());
        Glide.with(context).load(list.get(position).getLogo()).into(holder.iv);
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetOnClickItem.getData(list.get(position).getId());
            }
        });


    }
    public void setmSetOnClickItem(setOnClickItem setOnClickItem){
        mSetOnClickItem=setOnClickItem;
    }
    private setOnClickItem mSetOnClickItem;
    public interface setOnClickItem{
        void getData(int id);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_xin)
        TextView tvXin;
        @BindView(R.id.ll)
        LinearLayout ll;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
