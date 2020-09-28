package com.bw.movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.QueryCinemaAdapter;
import com.bw.movie.base.App;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.QueryCinemaBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUilt;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 选座购票
 */
public class SeatSelectionActivity extends BaseActivity {

    @BindView(R.id.vv)
    JCVideoPlayerStandard vv;
    @BindView(R.id.name)
    TextView tvName;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.one)
    LinearLayout one;
    @BindView(R.id.score)
    TextView tvScore;
    @BindView(R.id.two)
    LinearLayout two;
    @BindView(R.id.dao)
    TextView dao;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.rv)
    RecyclerView rv;
    private List<String> tabs=new ArrayList<>();


    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_seat_selection;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String score = intent.getStringExtra("score");
        String dao = intent.getStringExtra("dao");
        String id = intent.getStringExtra("id");
        String ima = intent.getStringExtra("ima");
        String time = intent.getStringExtra("time");


        tvName.setText(name);
        tvScore.setText(score+"分");
        this.dao.setText(dao);
        this.time.setText(time);
        vv.setUp(ima, JCVideoPlayer.SCREEN_LAYOUT_LIST,"");
        tabs.clear();
        tabs.add("海淀区");
        tabs.add("今天 05-14");
        tabs.add("价格最低");
        tabs.add("🍭");

        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));
        tab.addTab(tab.newTab().setText(tabs.get(3)));
        NetUtils.getInstance().getApis().getQueryBean(Integer.valueOf(id),5,1,10)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryCinemaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryCinemaBean queryCinemaBean) {
                        LinearLayoutManager manager = new LinearLayoutManager(SeatSelectionActivity.this, RecyclerView.VERTICAL, false);
                        rv.setLayoutManager(manager);
                        List<QueryCinemaBean.ResultBean> result = queryCinemaBean.getResult();
                        QueryCinemaAdapter adapter = new QueryCinemaAdapter(SeatSelectionActivity.this, result);
                        rv.setAdapter(adapter);
                        adapter.setmOnItemClick(new QueryCinemaAdapter.onItemClick() {
                            @Override
                            public void getData(int id) {
                                Intent intent = new Intent(SeatSelectionActivity.this, ScheduleActivity.class);
                                SharedPreferences sp = getSharedPreferences("in_id", MODE_PRIVATE);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putInt("id",id);
                                edit.commit();
                                startActivity(intent);
                            }
                        });



                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

}
