package com.bw.movie.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.ReleaseBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/22 23:06
 */
public class ReleaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ReleaseBean.ResultBean> list;


    public ReleaseAdapter(Context context, List<ReleaseBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.index_item_tow, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImageUrl()).into(((ViewHolder)holder).indexItemTowIv);
        ((ViewHolder)holder).indexItemTowData.setText(list.get(position).getWhetherReserve()+"月上映");
        ((ViewHolder)holder).indexItemTowLike.setText(list.get(position).getWantSeeNum()+"万人想看");
        ((ViewHolder)holder).indexItemTowName.setText(list.get(position).getName());
        ((ViewHolder)holder).indexItemTowBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSetonItem.getData(list.get(position).getMovieId());
            }
        });
    }
    public void setmSetonItem(setOnItem setonItem){
        mSetonItem=setonItem;
    }
    private setOnItem mSetonItem;
    public interface setOnItem{
        void getData(int id);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.index_item_tow_iv)
        ImageView indexItemTowIv;
        @BindView(R.id.index_item_tow_name)
        TextView indexItemTowName;
        @BindView(R.id.index_item_tow_data)
        TextView indexItemTowData;
        @BindView(R.id.index_item_tow_like)
        TextView indexItemTowLike;
        @BindView(R.id.index_item_tow_but)
        Button indexItemTowBut;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
