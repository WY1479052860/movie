package com.bw.movie.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUilt;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description: 影院详情
 * @date :2020/6/4 22:21
 */
public class CinemaDetailsFragment extends BaseFragment {
    @BindView(R.id.cinemade_dizhi)
    TextView cinemadeDizhi;
    @BindView(R.id.cinemade_phone)
    TextView cinemadePhone;
    @BindView(R.id.cinemade_luxian)
    TextView cinemadeLuxian;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.cinemainfo_item;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        int id = SPUilt.getInt(getContext(), "into", "id");
        NetUtils.getInstance().getApis().getCinemaInfo(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CinemaBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CinemaBean cinemaBean) {
                String phone = cinemaBean.getResult().getPhone();
                String vehicleRoute = cinemaBean.getResult().getVehicleRoute();
                String address = cinemaBean.getResult().getAddress();
                cinemadePhone.setText(phone);
                cinemadeDizhi.setText(address);
                cinemadeLuxian.setText(vehicleRoute);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("xxx",e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
