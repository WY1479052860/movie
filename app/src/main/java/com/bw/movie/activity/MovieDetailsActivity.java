package com.bw.movie.activity;


import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.bean.Value;
import com.bw.movie.contract.details.DetailsContract;
import com.bw.movie.custom.DrawerLayout;
import com.bw.movie.fragment.IntroductionFragment;
import com.bw.movie.fragment.MovieReviewFragment;
import com.bw.movie.fragment.NoticeFragment;
import com.bw.movie.fragment.StillsFragment;
import com.bw.movie.presenter.details.DetailsPresenter;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 电影详情页面
 */
public class MovieDetailsActivity extends BaseActivity implements DetailsContract.IView {


    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.di)
    TextView di;
    @BindView(R.id.heart)
    ImageView heart;
    @BindView(R.id.guan)
    TextView guan;
    @BindView(R.id.bu_write1)
    Button buWrite1;
    @BindView(R.id.buy)
    Button buy;
    @BindView(R.id.drawerHandle)
    ImageView drawerHandle;
    @BindView(R.id.names)
    TextView names;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.drawerContent)
    LinearLayout drawerContent;
    @BindView(R.id.drawer2)
    RelativeLayout drawer2;
    @BindView(R.id.dl)
    DrawerLayout dl;

    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    private DetailsBean.ResultBean result;
    private int i;;

    @Override
    protected BasePresenter initPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_movie_details;
    }

    @Override
    protected void initData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        tabs.add("介绍");
        tabs.add("预告");
        tabs.add("剧照");
        tabs.add("影评");
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));
        tab.addTab(tab.newTab().setText(tabs.get(3)));

        list.add(new IntroductionFragment());
        list.add(new NoticeFragment());
        list.add(new StillsFragment());
        list.add(new MovieReviewFragment());
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(myAdapter);
        tab.setupWithViewPager(vp);

    }

    //接口
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setId(Value value) {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof DetailsPresenter) {
            ((DetailsPresenter) presenter).GetDetails(value.getId());
        }
    }


    @Override
    public void GetDetailsSuccess(DetailsBean bean) {
        result = bean.getResult();
        Glide.with(this).load(result.getImageUrl()).into(iv);
        tv1.setText("评分：" + result.getScore() + "分");
        tv2.setText("评论：" + result.getCommentNum() + "万");
        name.setText(result.getName());
        time.setText(result.getMovieType() + "   " + result.getDuration());
        long releaseTime = result.getReleaseTime();
        String date = new SimpleDateFormat("yy--MM--dd").format(
                new Date(releaseTime));
        di.setText("20" + date + "  " + result.getPlaceOrigin() + "上映");
        names.setText(result.getName());
    }

    @Override
    public void GetDetailsFailure(String str) {
        Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();
    }
    @OnClick({R.id.bu_write1,R.id.bu_write2})
    public void setBuWrite2(View view){
        Intent intent = new Intent(MovieDetailsActivity.this, WriteCommentActivity.class);
        intent.putExtra("name",name.getText().toString());
        intent.putExtra("pf",tv1.getText().toString());
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.bt_buy2)
    public void setBuy(){
        i=i+1;
        Intent intent = new Intent(MovieDetailsActivity.this, SeatSelectionActivity.class);
        Value value=new Value(i);
        intent.putExtra("id",value.getId()+"");
        intent.putExtra("time",result.getDuration()+"");
        intent.putExtra("score",result.getScore()+"");
        intent.putExtra("dao",result.getMovieActor().get(0).getName());
        intent.putExtra("ima",result.getShortFilmList().get(0).getVideoUrl());
        intent.putExtra("name",result.getName());
        startActivity(intent);
    }

    class MyAdapter extends FragmentPagerAdapter{

            public MyAdapter(@NonNull FragmentManager fm) {
                super(fm);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs.get(position);

            }
        }
    }

