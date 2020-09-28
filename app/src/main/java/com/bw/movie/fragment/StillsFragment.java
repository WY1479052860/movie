package com.bw.movie.fragment;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.StillsAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.DetailsBean;
import com.bw.movie.contract.details.DetailsContract;
import com.bw.movie.presenter.details.DetailsPresenter;
import com.bw.movie.utils.SPUilt;

import java.util.List;

import butterknife.BindView;

/**
 * @author WangYi
 * @description: 影院详情--剧照
 * @date :2020/5/29 21:44
 */
public class StillsFragment extends BaseFragment implements DetailsContract.IView {
    @BindView(R.id.stills_rv)
    RecyclerView stillsRv;

    @Override
    protected BasePresenter initPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.stills_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        int id = SPUilt.getInt(getContext(), "into", "id");
        BasePresenter presenter = getPresenter();
        if (presenter instanceof DetailsPresenter) {
            ((DetailsPresenter) presenter).GetDetails(id);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        stillsRv.setLayoutManager(layoutManager);

    }

    @Override
    public void GetDetailsSuccess(DetailsBean bean) {
        DetailsBean.ResultBean result = bean.getResult();
        StillsAdapter adapter = new StillsAdapter(getActivity(), result);
        stillsRv.setAdapter(adapter);


    }

    @Override
    public void GetDetailsFailure(String str) {

    }
}
