package com.bw.movie.adapter;

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
import com.bw.movie.bean.QueryCinemaBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/5 23:09
 */
public class QueryCinemaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<QueryCinemaBean.ResultBean> result;


    public QueryCinemaAdapter(Context context, List<QueryCinemaBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.querycinema_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).address.setText(result.get(position).getAddress());
        ((ViewHolder) holder).name.setText(result.get(position).getName());
        Glide.with(context).load(result.get(position).getLogo()).into(((ViewHolder) holder).iv);
        ((ViewHolder) holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.getData(result.get(position).getCinemaId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public void setmOnItemClick(onItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    private onItemClick mOnItemClick;

    public interface onItemClick {
        void getData(int id);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.address)
        TextView address;
        @BindView(R.id.ll)
        LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
