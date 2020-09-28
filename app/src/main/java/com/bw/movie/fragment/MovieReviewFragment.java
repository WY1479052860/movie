package com.bw.movie.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieAdapter;
import com.bw.movie.adapter.MovieReviewAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.MyMovieBean;
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
 * @description: 影院介绍--影评
 * @date :2020/5/29 21:45
 */
public class MovieReviewFragment extends BaseFragment {
    @BindView(R.id.movie_rv)
    RecyclerView movieRv;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.moviereview_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        int id = SPUilt.getInt(getContext(), "into", "id");
        NetUtils.getInstance().getApis().getMyMovie(id,1, 10)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyMovieBean myMovieBean) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                        movieRv.setLayoutManager(layoutManager);
                        List<MyMovieBean.ResultBean> result = myMovieBean.getResult();
                        MovieReviewAdapter adapter = new MovieReviewAdapter(getActivity(), result);
                        movieRv.setAdapter(adapter);

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
