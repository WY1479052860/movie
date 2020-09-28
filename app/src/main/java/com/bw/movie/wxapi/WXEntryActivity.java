package com.bw.movie.wxapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.WindowManager;
import android.widget.Toast;
import com.bw.movie.R;
import com.bw.movie.activity.HomeActivity;
import com.bw.movie.base.App;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.WeChat;
import com.bw.movie.contract.IWexinContract;
import com.bw.movie.presenter.WeXinPresenter;
import com.bw.movie.utils.SPUilt;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler,IWexinContract.IView {

    private IWXAPI iwxapi;
    private static String APP_ID = "wxb3852e6a6b7d9516";
    private String code;

    @Override
    protected BasePresenter initPresenter() {
        return new WeXinPresenter(this);
    }
    @Override
    protected int getLayout() {
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        iwxapi = WXAPIFactory.createWXAPI(this, APP_ID, false);
        iwxapi.handleIntent(getIntent(), this);
        return R.layout.activity_wxentry;
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK://代表成功
                code = ((SendAuth.Resp) baseResp).code;
//                Map<String, String> map = new HashMap();
//                map.put("code", code);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            default:
                finish();
                break;
        }
    }
    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof WeXinPresenter){
            ((WeXinPresenter) presenter).WeChatSuccess(code);
        }
    }
    @Override
    public void OnWeChatSuccess(WeChat data) {
        if (data instanceof WeChat) {
            WeChat bean = data;
            if (bean != null && bean.getStatus().equals("0000")) {
                Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                String sessionId = data.getResult().getSessionId();
                int userId = data.getResult().getUserId();
                SharedPreferences sp = getSharedPreferences("wx", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("userId", String.valueOf(userId));
                edit.putString("sessionId",sessionId);
                edit.commit();
                String headPic = data.getResult().getUserInfo().getHeadPic();
                String nickName = data.getResult().getUserInfo().getNickName();
                SharedPreferences into_iv = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor edit1 = into_iv.edit();
                edit1.putString("headPic",headPic);
                edit1.putString("nickName",nickName);
                edit1.commit();
                Intent intent = new Intent(WXEntryActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "微信登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
