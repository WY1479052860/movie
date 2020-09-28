package com.bw.movie.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public  abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
   P presenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter=initPresenter();
        bind = ButterKnife.bind(this);
        initData();
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract P initPresenter();
    protected abstract int getLayout();
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.deatchView();
            presenter=null;
        }
        bind.unbind();
    }
}
