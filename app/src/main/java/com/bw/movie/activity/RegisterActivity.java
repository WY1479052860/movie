package com.bw.movie.activity;


import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.IRegisterContract;
import com.bw.movie.presenter.RegisterPresenter;
import com.bw.movie.utils.EncryptUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements IRegisterContract.IView {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.register_pwd)
    EditText register_pwd;
    @BindView(R.id.et_captcha)
    EditText etCaptcha;
    @BindView(R.id.Captcha)
    Button Captcha;
    @BindView(R.id.tv_zhu)
    TextView tvZhu;
    @BindView(R.id.bt_register)
    Button btRegister;
    @Override
    protected BasePresenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {

    }
    //点击文字跳转
    @OnClick(R.id.tv_zhu)
    public void setTvZhu(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    //点击发送验证码
    @OnClick(R.id.Captcha)
    public void setCaptcha(){
        String email = etEmail.getText().toString();
        BasePresenter presenter = getPresenter();
        if(presenter instanceof RegisterPresenter){
            ((RegisterPresenter) presenter).GetEmail(email);
        }
    }
    //点击注册
    @OnClick(R.id.bt_register)
    public void setBtRegister(){
        BasePresenter presenter = getPresenter();
        String name = etName.getText().toString();
        String pwd = register_pwd.getText().toString();
        String email = etEmail.getText().toString();
        String captcha = etCaptcha.getText().toString();
        //加密
        String encrypt = EncryptUtil.encrypt(pwd);
        if (presenter instanceof RegisterPresenter) {
            ((RegisterPresenter) presenter).GetRegister(name,encrypt,email,captcha);
        }
    }
    //注册成功
    @Override
    public void GetRegisterSuccess(RegisterBean bean) {
        String message = bean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void GetRegisterFailure(String str) {
    }
    //发送邮箱成功
    @Override
    public void GetEmailSuccess(EmailBean bean) {
        String message = bean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }

}
