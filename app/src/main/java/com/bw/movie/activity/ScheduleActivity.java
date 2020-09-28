package com.bw.movie.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.schedule.ScheduleOneFragment;
import com.bw.movie.fragment.schedule.ScheduleThreeFragment;
import com.bw.movie.fragment.schedule.ScheduleTwoFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 电影排期
 */
public class ScheduleActivity extends BaseActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<String> tab = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_schedule;
    }

    @Override
    protected void initData() {
        tab.add("5月14号");
        tab.add("5月15号");
        tab.add("5月16号");
        tabs.addTab(tabs.newTab().setText(tab.get(0)));
        tabs.addTab(tabs.newTab().setText(tab.get(1)));
        tabs.addTab(tabs.newTab().setText(tab.get(2)));
        fragmentList.add(new ScheduleOneFragment());
        fragmentList.add(new ScheduleTwoFragment());
        fragmentList.add(new ScheduleThreeFragment());
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(myAdapter);
        tabs.setupWithViewPager(vp);

    }
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tab.get(position);
        }
    }

}
