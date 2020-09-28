package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.DiscussBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUilt;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 写评论页面
 */
public class WriteCommentActivity extends BaseActivity {

    @BindView(R.id.name)
    TextView tvName;
    @BindView(R.id.fen)
    TextView fen;
    @BindView(R.id.rb)
    RatingBar rb;
    @BindView(R.id.tv)
    EditText tv;
    @BindView(R.id.bu)
    Button bu;
    double a=0.0;
    private String pf;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_write_comment;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                fen.setText("我的评分 ("+rating+"分)");
                a=rating;
            }
        });
        tvName.setText(name);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id  = SPUilt.getInt(WriteCommentActivity.this, "into", "id");
                String s = tv.getText().toString();
                NetUtils.getInstance().getApis().getmovieComment(id,s,a)
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<DiscussBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(DiscussBean discussBean) {
                                String message = discussBean.getMessage();
                                Toast.makeText(WriteCommentActivity.this, ""+message, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });



            }
        });
    }
}
