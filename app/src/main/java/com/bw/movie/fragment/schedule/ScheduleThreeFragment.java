package com.bw.movie.fragment.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.ScheduleAdapter;
import com.bw.movie.base.App;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.utils.NetUtils;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/19 14:46
 */
public class ScheduleThreeFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.schedulethree_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("in_id", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);
        NetUtils.getInstance().getApis().getSchedule(id, 1, 10)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScheduleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScheduleBean scheduleBean) {
                        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                        rv.setLayoutManager(manager);
                        List<ScheduleBean.ResultBean> result = scheduleBean.getResult();
                        ScheduleAdapter adapter = new ScheduleAdapter(getContext(), result);
                        rv.setAdapter(adapter);


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
