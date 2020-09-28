package com.bw.movie.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/20 20:30
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {
    P presenter;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), getLayout(), null);
        presenter=initPresenter();
        bind = ButterKnife.bind(this, view);
        initView(view);
        initData();
        return view;
    }
    public P getPresenter() {
        return presenter;
    }
    protected abstract P initPresenter();
    protected abstract int getLayout();
    protected abstract void initView(View view);
    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.deatchView();
            presenter=null;
        }
    }
}
