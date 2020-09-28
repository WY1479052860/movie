package com.bw.movie.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.cinema.AreaFragment;
import com.bw.movie.fragment.cinema.NearbyFragment;
import com.bw.movie.fragment.cinema.RecommendMovieFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/22 20:03
 */
public class CinemaFragment extends BaseFragment {
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<String> tabs = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.cinema_fragment;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {
        tabs.add("推荐影院");
        tabs.add("附近影院");
        tabs.add("海淀区");
        tab.addTab(tab.newTab().setText(tabs.get(0)));
        tab.addTab(tab.newTab().setText(tabs.get(1)));
        tab.addTab(tab.newTab().setText(tabs.get(2)));
        list.add(new RecommendMovieFragment());
        list.add(new NearbyFragment());
        list.add(new AreaFragment());

        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
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
