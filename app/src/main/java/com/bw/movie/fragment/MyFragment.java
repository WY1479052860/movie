package com.bw.movie.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.PersonalinformationActivity;
import com.bw.movie.activity.UserReserveActivity;
import com.bw.movie.base.App;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.UserReserveBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUilt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/22 20:03
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.iv_mine_systemmsg)
    ImageView ivMineSystemmsg;
    @BindView(R.id.iv_mine_head)
    ImageView ivMineHead;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.iv_mine_userinfo)
    ImageView ivMineUserinfo;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.iv_wodedianyinpiao)
    ImageView ivWodedianyinpiao;
    @BindView(R.id.ticket)
    RelativeLayout ticket;
    @BindView(R.id.iv_mine_follow)
    ImageView ivMineFollow;
    @BindView(R.id.iv_mine_order)
    ImageView ivMineOrder;
    @BindView(R.id.ll_wodeyuyue)
    LinearLayout llWodeyuyue;
    @BindView(R.id.iv_mine_record)
    ImageView ivMineRecord;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.iv_mine_history)
    ImageView ivMineHistory;
    @BindView(R.id.iv_mine_comment)
    ImageView ivMineComment;
    @BindView(R.id.iv_mine_idea)
    ImageView ivMineIdea;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.iv_mine_setting)
    ImageView ivMineSetting;
    @BindView(R.id.ll3)
    LinearLayout ll3;
    @BindView(R.id.rl3)
    RelativeLayout rl3;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.my_fragment;
    }

    @Override
    protected void initView(View view) {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }

    @Override
    protected void initData() {
        SharedPreferences iv = App.getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        String headPic = iv.getString("headPic", "");
        String nickName = iv.getString("nickName", "");
        Glide.with(getContext()).load(headPic).apply(new RequestOptions().circleCrop()).into(ivMineHead);
        tvMineName.setText(nickName);

        llWodeyuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserReserveActivity.class);
                startActivity(intent);
            }
        });
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PersonalinformationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setId(String str){
        Glide.with(getActivity()).
                load(str).into(ivMineHead);

    }
}
