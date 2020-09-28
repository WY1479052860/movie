package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.RenYingBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/22 22:36
 */
public class HotReAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<RenYingBean.ResultBean> result;


    public HotReAdapter(Context context, List<RenYingBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.index_item_one, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).indexItemOneName.setText(result.get(position).getName());
        ((ViewHolder)holder).indexItemOnePf.setText(result.get(position).getScore()+"");
        Glide.with(context).load(result.get(position).getImageUrl()).into(((ViewHolder) holder).indexItemOneIv);
        ((ViewHolder)holder).indexItemOneGp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msetOnClick.OnClick(result.get(position).getMovieId());
            }
        });
    }
    private setOnClicked msetOnClick;
    public void setOnClick(setOnClicked setOnClicked){
        msetOnClick = setOnClicked;
    }
    public interface setOnClicked{
        void OnClick(int id);
    }



    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.index_item_one_iv)
        ImageView indexItemOneIv;
        @BindView(R.id.index_item_one_pf)
        TextView indexItemOnePf;
        @BindView(R.id.index_item_one_name)
        TextView indexItemOneName;
        @BindView(R.id.index_item_one_gp)
        Button indexItemOneGp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
