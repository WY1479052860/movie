package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.fragment.CinemaDetailsFragment;
import com.bw.movie.fragment.TheaterReviewFragment;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUilt;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CinemaInfoActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.bt_cinema)
    Button btCinema;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    private List<String> tab = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_cinema_info;
    }

    @Override
    protected void initData() {
        tab.add("影院详情");
        tab.add("影院评价");
        tabs.addTab(tabs.newTab().setText(tab.get(0)));
        tabs.addTab(tabs.newTab().setText(tab.get(1)));
        list.add(new CinemaDetailsFragment());
        list.add(new TheaterReviewFragment());
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        tabs.setupWithViewPager(vp);
        int id = SPUilt.getInt(this, "into", "id");
        NetUtils.getInstance().getApis().getCinemaInfo(id)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(CinemaBean cinemaBean) {
                        Toast.makeText(CinemaInfoActivity.this, "走了", Toast.LENGTH_SHORT).show();
                        CinemaBean.ResultBean result = cinemaBean.getResult();
                        String label = result.getLabel();
                        String name = result.getName();
                        tv_name.setText(name);
                        tvInfo.setText(label);
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });




    }

    class MyAdapter extends FragmentPagerAdapter {
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
            return tab.get(position);
        }
    }

}
