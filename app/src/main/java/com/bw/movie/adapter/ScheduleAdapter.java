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
import com.bw.movie.bean.ScheduleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/19 15:07
 */
public class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ScheduleBean.ResultBean> result;


    public ScheduleAdapter(Context context, List<ScheduleBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.schedule_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).schedName.setText("电影:"+result.get(position).getName());
        ((ViewHolder)holder).schedDirector.setText("导演:"+result.get(position).getDirector());
        ((ViewHolder)holder).schedStarring.setText("主演:"+result.get(position).getStarring());
        Glide.with(context).load(result.get(position).getImageUrl()).into(((ViewHolder) holder).schedIv);
        ((ViewHolder)holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.getData(result.get(position));
            }
        });
    }
    public void setmOnItemClick(onItemClick onItemClick){
        mOnItemClick=onItemClick;
    }
    private onItemClick mOnItemClick;
    public interface onItemClick{
        void getData(ScheduleBean.ResultBean resultBean);
    }
    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sched_iv)
        ImageView schedIv;
        @BindView(R.id.sched_name)
        TextView schedName;
        @BindView(R.id.sched_director)
        TextView schedDirector;
        @BindView(R.id.sched_starring)
        TextView schedStarring;
        @BindView(R.id.ll)
        LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
