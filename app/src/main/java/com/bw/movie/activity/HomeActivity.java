package com.bw.movie.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.HomeFragment;
import com.bw.movie.fragment.MyFragment;
import com.bw.movie.utils.CustomTabEntityDemo;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tab)
    CommonTabLayout tab;
    private List<Fragment> list=new ArrayList<>();
    private ArrayList<CustomTabEntity> customTabEntityDemos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        HomeFragment homeFragment = new HomeFragment();
        CinemaFragment cinemaFragment = new CinemaFragment();
        MyFragment myFragment = new MyFragment();
        list.add(homeFragment);
        list.add(cinemaFragment);
        list.add(myFragment);

        CustomTabEntityDemo demo1 = new CustomTabEntityDemo("电影",R.mipmap.movie_red,R.mipmap.movie_bai);
        CustomTabEntityDemo demo2 = new CustomTabEntityDemo("影院",R.mipmap.yingyuan_red,R.mipmap.yingyuan_bai);
        CustomTabEntityDemo demo3 = new CustomTabEntityDemo("我的",R.mipmap.my_red,R.mipmap.movie_bai);
        customTabEntityDemos.add(demo1);
        customTabEntityDemos.add(demo2);
        customTabEntityDemos.add(demo3);
        tab.setTabData(customTabEntityDemos);
        //适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //  CommonTabLayout 监听器
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        //vp的监听器
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                tab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

}
