package com.bw.movie.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.NoticeAdapter;
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
 * @description: 影院信息--预告
 * @date :2020/5/29 21:43
 */
public class NoticeFragment extends BaseFragment implements DetailsContract.IView {
    @BindView(R.id.notice_rv)
    RecyclerView noticeRv;

    @Override
    protected BasePresenter initPresenter() {
        return new DetailsPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.notice_fragment;
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
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        noticeRv.setLayoutManager(manager);

    }

    @Override
    public void GetDetailsSuccess(DetailsBean bean) {
        List<DetailsBean.ResultBean.ShortFilmListBean> list = bean.getResult().getShortFilmList();
        NoticeAdapter noticeAdapter = new NoticeAdapter(getActivity(), list);
        noticeRv.setAdapter(noticeAdapter);


    }

    @Override
    public void GetDetailsFailure(String str) {

    }
}
