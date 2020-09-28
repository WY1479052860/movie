package com.bw.movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.base.App;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.UploadHeadPicBean;
import com.bw.movie.utils.NetUtils;
import com.bw.movie.utils.SPUilt;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wildma.pictureselector.PictureSelector;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * 上传头像
 */
public class PersonalinformationActivity extends BaseActivity {
    @BindView(R.id.tv_tou)
    TextView tvTou;
    @BindView(R.id.tv_ni)
    TextView tvNi;
    @BindView(R.id.tv_xing)
    TextView tvXing;
    @BindView(R.id.tv_chu)
    TextView tvChu;
    @BindView(R.id.iv_mine_userinfo)
    ImageView ivMineUserinfo;
    @BindView(R.id.tv_mine_userinfo_name)
    TextView tvMineUserinfoName;
    @BindView(R.id.tv_mine_userinfo_sex)
    TextView tvMineUserinfoSex;
    @BindView(R.id.tv_mine_userinfo_time)
    TextView tvMineUserinfoTime;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_shou)
    TextView tvShou;
    @BindView(R.id.tv_you)
    TextView tvYou;
    @BindView(R.id.tv_mine_userinfo_phone)
    TextView tvMineUserinfoPhone;
    @BindView(R.id.tv_mine_userinfo_eamil)
    TextView tvMineUserinfoEamil;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personalinformation;
    }

    @Override
    protected void initData() {
        SharedPreferences login = App.getContext().getSharedPreferences("login", MODE_PRIVATE);
        String headPic = login.getString("headPic", "");
        String sex = login.getString("sex", "");
        String nickName = login.getString("nickName", "");
        Long lastLoginTime = login.getLong("lastLoginTime", 0);
        String email = login.getString("email", "");

        Glide.with(this).load(headPic).apply(new RequestOptions().circleCrop()).into(ivMineUserinfo);
        tvMineUserinfoName.setText(nickName);
        if(sex.equals("1")){
            tvMineUserinfoSex.setText("男");
        }else{
            tvMineUserinfoSex.setText("女");
        }
        Date date = new Date(lastLoginTime);
        String format = new SimpleDateFormat("yy-MM-dd").format(date);
        tvMineUserinfoTime.setText(format);
        tvMineUserinfoPhone.setText("13218906130");
        tvMineUserinfoEamil.setText(email);

        SharedPreferences image = App.getContext().getSharedPreferences("image", MODE_PRIVATE);
        String headPath = image.getString("headPath", "");
        Glide.with(PersonalinformationActivity.this).load(headPath).into(ivMineUserinfo);
    }
    @OnClick(R.id.iv_mine_userinfo_back)
    public void setIv_fh(){
        finish();
    }
    @OnClick(R.id.iv_mine_userinfo)
    public void setIv_sdv(){
        PictureSelector
                .create(this, PictureSelector.SELECT_REQUEST_CODE)
                .selectPicture(true, 200, 200, 1, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==PictureSelector.SELECT_REQUEST_CODE){
            if(data!=null){
                String dataStringExtra = data.getStringExtra(PictureSelector.PICTURE_PATH);
                Glide.with(this).load(dataStringExtra).apply(new RequestOptions().circleCrop()).into(ivMineUserinfo);
                File file=new File(dataStringExtra);
                ArrayList<File> files = new ArrayList<>();
                files.add(file);
                HashMap<String, String> map = new HashMap<>();
                RequestBody body = NetUtils.getInstance().getRequestBody(files, map);
                NetUtils.getInstance().getApis().getUpLoadHeadPicBean(body)
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UploadHeadPicBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(UploadHeadPicBean uploadHeadPicBean) {
                                String message = uploadHeadPicBean.getMessage();
                                Toast.makeText(PersonalinformationActivity.this, message, Toast.LENGTH_SHORT).show();
                                String headPath = uploadHeadPicBean.getHeadPath();
                                SharedPreferences sp = getSharedPreferences("image", MODE_PRIVATE);
                                SharedPreferences.Editor edit = sp.edit();
                                edit.putString("headPath",headPath);
                                edit.commit();
                                EventBus.getDefault().postSticky(headPath);


                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }
    }
}
