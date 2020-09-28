package com.bw.movie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.DetailsBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/2 16:32
 */
public class NoticeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DetailsBean.ResultBean.ShortFilmListBean> list;

    public NoticeAdapter(Context context, List<DetailsBean.ResultBean.ShortFilmListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.notice_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String videoUrl = list.get(position).getVideoUrl();
        ((ViewHolder)holder).vv.setUp(videoUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST,"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private  JCVideoPlayerStandard vv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vv = itemView.findViewById(R.id.vv);

        }
    }
}
