package com.bw.movie.fragment.cinema;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaInfoActivity;
import com.bw.movie.adapter.cinemas.RecommendMovieAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendCinemasBean;
import com.bw.movie.contract.cinemas.CinemasContract;
import com.bw.movie.presenter.cinemas.CinemasPresenter;
import com.bw.movie.utils.SPUilt;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/**
 * @author WangYi
 * @description:推荐影院
 * @date :2020/5/28 20:37
 */
public class RecommendMovieFragment extends BaseFragment implements CinemasContract.IView {
    @BindView(R.id.rv)
    RecyclerView rv;
    private RecommendMovieAdapter adapter;

    @Override
    protected BasePresenter initPresenter() {
        return new CinemasPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.recommendmovie_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof CinemasPresenter){
            ((CinemasPresenter) presenter).GetRecommendMovie(1,10);
        }
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);

    }

    @Override
    public void GetRecommendMovieSuccess(RecommendCinemasBean bean) {
        List<RecommendCinemasBean.ResultBean> result = bean.getResult();
        adapter = new RecommendMovieAdapter(getActivity(), result);
        rv.setAdapter(adapter);
        adapter.setmSetOnClickItem(new RecommendMovieAdapter.setOnClickItem() {
            @Override
            public void getData(int id) {
                Toast.makeText(getActivity(), ""+id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CinemaInfoActivity.class);
                SPUilt.putInt(getContext(),"into","id",id);
                startActivity(intent);
            }
        });

    }

    @Override
    public void GetNearbySuccess(NearbyBean bean) {

    }
}
