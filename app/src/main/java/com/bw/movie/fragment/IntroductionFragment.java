package com.bw.movie.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.MovieAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.contract.details.DetailsContract;
import com.bw.movie.presenter.details.DetailsPresenter;
import com.bw.movie.utils.SPUilt;

import butterknife.BindView;

/**
 * @author WangYi
 * @description: 影院信息--介绍
 * @date :2020/5/29 21:43
 */
public class IntroductionFragment extends BaseFragment implements DetailsContract.IView {


    @BindView(R.id.introduce_rv_rv)
    RecyclerView introduceRvRv;

    @Override
    protected BasePresenter initPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.introduction_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        int id = SPUilt.getInt(getActivity(), "into", "id");
        BasePresenter presenter = getPresenter();
        if (presenter instanceof DetailsPresenter) {
            ((DetailsPresenter) presenter).GetDetails(id);
        }


    }

    @Override
    public void GetDetailsSuccess(DetailsBean bean) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        introduceRvRv.setLayoutManager(layoutManager);
        DetailsBean.ResultBean result = bean.getResult();
        MovieAdapter adapter = new MovieAdapter(getContext(), result);
        introduceRvRv.setAdapter(adapter);


    }

    @Override
    public void GetDetailsFailure(String str) {

    }
}
