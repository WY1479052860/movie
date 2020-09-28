package com.bw.movie.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.ILoginContract;
import com.bw.movie.presenter.LoginPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.SPUilt;
import com.bw.movie.wxapi.WXEntryActivity;
import com.bw.movie.wxapi.WXUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements ILoginContract.IView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.Captcha)
    Button Captcha;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_wx)
    ImageButton btWx;

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {

    }
    //点击文字跳转
    @OnClick(R.id.tv_login)
    public void setTvLogin(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.bt_wx)
    public void setBtWx(){
        boolean success = WXUtils.success(this);
        if (success) {//请求第三方登录
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wx_login_duzun";
            WXUtils.reg(this).sendReq(req);
        }else{
            Toast.makeText(this, "没有微信", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.bt_login)
    public void setBtLogin(){
        BasePresenter presenter = getPresenter();
        String phone = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        String encrypt = EncryptUtil.encrypt(pwd);

        if(presenter instanceof LoginPresenter){
             ((LoginPresenter) presenter).GetLogin(phone,encrypt);
        }

    }

    @Override
    public void GetLoginSuccess(LoginBean bean) {
        String message = bean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if(bean.getMessage().equals("登陆成功")){
            LoginBean.ResultBean result = bean.getResult();
            LoginBean.ResultBean.UserInfoBean userInfo = result.getUserInfo();
            String headPic = userInfo.getHeadPic();
            long lastLoginTime = userInfo.getLastLoginTime();
            int sex = userInfo.getSex();
            String email = userInfo.getEmail();
            String nickName = userInfo.getNickName();
            SPUilt.putString(LoginActivity.this,"login","userinfo",result.getUserInfo()+"");
//            SPUilt.putString(LoginActivity.this,"login","userId",result.getUserId()+"");
//            SPUilt.putString(LoginActivity.this,"login","sessionId",result.getSessionId());
            SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            int userId = result.getUserId();
            String sessionId = result.getSessionId();
            edit.putString("userId", String.valueOf(userId));
            edit.putString("sessionId",sessionId);
            edit.putString("headPic",headPic);
            edit.putString("sex",String.valueOf(sex));
            edit.putLong("lastLoginTime",lastLoginTime);
            edit.putString("email",email);
            edit.putString("nickName",nickName);
            edit.commit();
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void GetLoginFailure(String str) {

    }
}
