package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.WeChat;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/15 11:28
 */
public interface IWexinContract {
    interface IView extends IBaseView {
        void OnWeChatSuccess(WeChat weChat);
    }
    interface IPreantert{
        void WeChatSuccess(String code);
    }
    interface IModel{
        void WeChatSuccess(String code,WeChatCoallack mWeChatCoallack);
        interface WeChatCoallack{
            void OnWeChatSuccess(WeChat weChat);
        }
    }
}
