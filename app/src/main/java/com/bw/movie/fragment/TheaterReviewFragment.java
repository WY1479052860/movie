package com.bw.movie.fragment;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.TheaterReviewAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CinemaCommentBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUilt;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description: 影院评论
 * @date :2020/6/4 22:21
 */
public class TheaterReviewFragment extends BaseFragment {
    @BindView(R.id.th_rv)
    RecyclerView thRv;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.theatereview_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        int anInt = SPUilt.getInt(getContext(), "into", "id");
        NetUtils.getInstance().getApis().getCinemaComment(anInt,1,10)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaCommentBean cinemaCommentBean) {
                        Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                        thRv.setLayoutManager(manager);
                        List<CinemaCommentBean.ResultBean> result = cinemaCommentBean.getResult();
                        TheaterReviewAdapter adapter = new TheaterReviewAdapter(getActivity(), result);
                        thRv.setAdapter(adapter);


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
