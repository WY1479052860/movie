package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.SearchAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ByKeywordBean;
import com.bw.movie.contract.search.ISearchContract;
import com.bw.movie.presenter.search.SearchPresenter;
import com.bw.movie.utils.NetUtils;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity implements ISearchContract.IView {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.search_rv)
    RecyclerView searchRv;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            BasePresenter presenter = getPresenter();
            String name = etSearch.getText().toString();
            if(presenter instanceof SearchPresenter){
                ((SearchPresenter) presenter).GetSearch(name,1,10);
            }

            LinearLayoutManager manager = new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false);
            searchRv.setLayoutManager(manager);
        }
    };



    @Override
    protected BasePresenter initPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,2000);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void GetSearchSuccess(ByKeywordBean bean) {
        List<ByKeywordBean.ResultBean> result = bean.getResult();
        if(result==null){
            Toast.makeText(this, "搜索为空·", Toast.LENGTH_SHORT).show();
        }else{
            SearchAdapter adapter = new SearchAdapter(SearchActivity.this, result);
            searchRv.setAdapter(adapter);
        }

    }

    @Override
    public void GetSearchFailure(String str) {

    }
}
