package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.custom.SeatTable;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 选座页面
 */
public class SelectionActivity extends BaseActivity {

    @BindView(R.id.cu_name)
    TextView cuName;
    @BindView(R.id.cu_jcv)
    JCVideoPlayerStandard cuJcv;
    @BindView(R.id.mSearchView)
    SeatTable mSearchView;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_selection;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String trailerUrl = intent.getStringExtra("trailerUrl");
        String name = intent.getStringExtra("name");
        cuName.setText(name);
        cuJcv.setUp(trailerUrl, JCVideoPlayer.SCREEN_LAYOUT_LIST, "");

        mSearchView.setScreenName("8号厅荧幕");//设置屏幕名称
        mSearchView.setMaxSelected(3);//设置最多选中

        mSearchView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        mSearchView.setData(10,15);
    }



}
