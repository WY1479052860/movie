package com.bw.movie.fragment.cinema;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.cinemas.LeftAdapter;
import com.bw.movie.adapter.cinemas.RightAdapter;
import com.bw.movie.base.App;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.AreaBean;
import com.bw.movie.bean.NearBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUilt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description: 左右联动
 * @date :2020/5/28 20:46
 */
public class AreaFragment extends BaseFragment {
    @BindView(R.id.rv_left)
    RecyclerView rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.area_fragment;
    }

    @Override
    protected void initView(View view) {
        //eventBus 如果没有注册 就注册
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initData() {
        NetUtils.getInstance().getApis().getArea().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AreaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AreaBean areaBean) {
                        List<AreaBean.ResultBean> result = areaBean.getResult();
                        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                        rvLeft.setLayoutManager(manager);
                        LeftAdapter adapter = new LeftAdapter(getActivity(), result);
                        rvLeft.setAdapter(adapter);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setRvLeft(AreaBean.ResultBean bean){
        //获取到的id
        int regionId = bean.getRegionId();
        SharedPreferences qy = App.getContext().getSharedPreferences("qy", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = qy.edit();
        edit.putInt("regionId",regionId);
        edit.commit();
        NetUtils.getInstance().getApis().getNear(regionId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NearBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NearBean nearBean) {
                //右边
                LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                rvRight.setLayoutManager(manager);
                List<NearBean.ResultBean> result = nearBean.getResult();
                RightAdapter rightAdapter = new RightAdapter(getActivity(), result);
                rvRight.setAdapter(rightAdapter);


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
