package com.bw.movie.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.bean.DetailsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/2 21:01
 */
public class StillsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    DetailsBean.ResultBean result;
    private View view;
    private Bitmap bitmap;
    boolean toBig = false;
    private ImageView iv;
    private TextView tv;
    private Dialog mLoadingDialog;
    public StillsAdapter(Context context, DetailsBean.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.stills_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String s = result.getPosterList().get(position);
        Uri uri = Uri.parse(s);
        ((ViewHolder)holder).iv.setImageURI(uri);

        ((ViewHolder)holder).iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoadingDialog == null) {
                    mLoadingDialog = new Dialog(context);
                    if (mLoadingDialog.isShowing() == false) {
                        View view = View.inflate(context, R.layout.dialog_loading, null);
                        iv = view.findViewById(R.id.iv_loading);
                        tv = view.findViewById(R.id.tv);
                        Glide.with(context).load(s).into(iv);
                        mLoadingDialog.addContentView(view,
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        mLoadingDialog.show();
                    }
                }else {
                    Dialog dialog = new Dialog(context);
                    if (dialog.isShowing() == false) {
                        View view = View.inflate(context, R.layout.dialog_loading, null);
                        iv = view.findViewById(R.id.iv_loading);
                        tv = view.findViewById(R.id.tv);
                        Glide.with(context).load(s).into(iv);
                        dialog.addContentView(view,
                                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                        dialog.show();
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.getPosterList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
