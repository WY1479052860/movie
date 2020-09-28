package com.bw.movie.fragment.cinema;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.cinemas.NearbyAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendCinemasBean;
import com.bw.movie.contract.cinemas.CinemasContract;
import com.bw.movie.presenter.cinemas.CinemasPresenter;
import com.bw.movie.utils.SPUilt;

import java.util.List;

import butterknife.BindView;

/**
 * @author WangYi
 * @description: 附近影院
 * @date :2020/5/28 20:45
 */
public class NearbyFragment extends BaseFragment implements CinemasContract.IView {
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected BasePresenter initPresenter() {
        return new CinemasPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.nearby_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof CinemasPresenter) {
            ((CinemasPresenter) presenter).GetNearbySuccess(null,null,1, 10);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);

    }

    @Override
    public void GetRecommendMovieSuccess(RecommendCinemasBean bean) {



    }

    @Override
    public void GetNearbySuccess(NearbyBean bean) {
//        Toast.makeText(getActivity(), "走了", Toast.LENGTH_SHORT).show();
        List<NearbyBean.ResultBean> result = bean.getResult();
        NearbyAdapter adapter = new NearbyAdapter(getActivity(), result);
        rv.setAdapter(adapter);

    }
}
