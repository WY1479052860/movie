package com.bw.movie.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.MovieDetailsActivity;
import com.bw.movie.activity.SearchActivity;
import com.bw.movie.adapter.HotReAdapter;
import com.bw.movie.adapter.PopularAdapter;
import com.bw.movie.adapter.ReleaseAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.PopularBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.RenYingBean;
import com.bw.movie.bean.ReserveBean;
import com.bw.movie.bean.Value;
import com.bw.movie.bean.XbannerBean;
import com.bw.movie.contract.homecontract.IXbannerContract;
import com.bw.movie.presenter.homepresenter.XbannerPresenter;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUilt;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/22 19:20
 */
public class HomeFragment extends BaseFragment implements IXbannerContract.IView {
    @BindView(R.id.xbn)
    XBanner xbn;
    @BindView(R.id.rv_ry)
    RecyclerView rvRy;
    @BindView(R.id.rv_sy)
    RecyclerView rvSy;
    @BindView(R.id.rv_rm)
    RecyclerView rvRm;
    @BindView(R.id.city)
    TextView city;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //异步获取定位结果
    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //解析定位结果
                    String address = amapLocation.getCity();
                    String substring = address.substring(0, 2);
                    city.setText(substring);
                }
            }
        }
    };
    private HotReAdapter adapter;

    @Override
    protected BasePresenter initPresenter() {
        return new XbannerPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @OnClick(R.id.search_home)
    public void setHomeSearch() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initData() {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},200);
        }else{
            //高德地图定位实现
            //初始化定位
            mLocationClient = new AMapLocationClient(getContext().getApplicationContext());
            //设置定位回调监听
            mLocationClient.setLocationListener(mLocationListener);
            //启动定位
                mLocationClient.startLocation();
            Toast.makeText(getActivity(),"已开启定位权限",Toast.LENGTH_LONG).show();
        }

        //返回的数据
        BasePresenter presenter = getPresenter();
        if (presenter instanceof XbannerPresenter) {
            ((XbannerPresenter) presenter).GetXBanner();
            ((XbannerPresenter) presenter).GetReYing(1, 10);
            ((XbannerPresenter) presenter).GetRelease(1, 10);
            ((XbannerPresenter) presenter).GetPopular(1, 10);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvRy.setLayoutManager(manager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvSy.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvRm.setLayoutManager(layoutManager1);
    }

    //XBanner
    @Override
    public void GetXBannerSuccess(XbannerBean bean) {
        List<XbannerBean.ResultBean> result = bean.getResult();
        xbn.setBannerData(result);
        xbn.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(result.get(position).getImageUrl())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                        .into((ImageView) view);
            }
        });

    }

    @Override
    public void GetXBannerFailure(String str) {

    }

    @Override
    public void GetReYingSuccess(RenYingBean bean) {
        List<RenYingBean.ResultBean> result = bean.getResult();
        adapter = new HotReAdapter(getContext(), result);
        rvRy.setAdapter(adapter);
        //条目点击事件
        adapter.setOnClick(new HotReAdapter.setOnClicked() {
            @Override
            public void OnClick(int id) {
                Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
                startActivity(intent);
                EventBus.getDefault().postSticky(new Value(id));
                SPUilt.putInt(getContext(), "into", "id", id);
            }
        });


    }

    @Override
    public void GetReYingFailure(String str) {

    }

    @Override
    public void GetReleaseSuccess(ReleaseBean bean) {
        List<ReleaseBean.ResultBean> result = bean.getResult();
        ReleaseAdapter adapter = new ReleaseAdapter(getContext(), result);
        rvSy.setAdapter(adapter);
        adapter.setmSetonItem(new ReleaseAdapter.setOnItem() {
            @Override
            public void getData(int id) {
                SPUilt.putInt(getContext(), "in", "id", id);
                NetUtils.getInstance().getApis().getReserve(id)
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ReserveBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ReserveBean reserveBean) {
                                String message = reserveBean.getMessage();
                                Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void GetPopularSuccess(PopularBean bean) {
        Log.i("xxx", "Aaa");
        List<PopularBean.ResultBean> result = bean.getResult();
        PopularAdapter adapter = new PopularAdapter(getContext(), result);
        rvRm.setAdapter(adapter);
    }
}
