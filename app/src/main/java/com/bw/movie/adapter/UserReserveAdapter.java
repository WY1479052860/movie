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
import com.bw.movie.bean.UserReserveBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/5 21:36
 */
public class UserReserveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<UserReserveBean.ResultBean> result;


    public UserReserveAdapter(Context context, List<UserReserveBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.userreserve_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).appintName.setText(result.get(position).getName());
        Glide.with(context).load(result.get(position).getImageUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(((ViewHolder) holder).appintIv);
        String date = new SimpleDateFormat("yy--MM--dd").format(
                new Date(result.get(position).getReleaseTime()));
        ((ViewHolder)holder).appintTime.setText(date+"上映");
        ((ViewHolder)holder).appintIt.setText(result.get(position).getWantSeeNum()+"人想看");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.appint_iv)
        ImageView appintIv;
        @BindView(R.id.appint_name)
        TextView appintName;
        @BindView(R.id.appint_time)
        TextView appintTime;
        @BindView(R.id.appint_it)
        TextView appintIt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
