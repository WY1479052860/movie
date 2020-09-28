package com.bw.movie.model.search;

import com.bw.movie.bean.ByKeywordBean;
import com.bw.movie.contract.search.ISearchContract;
import com.bw.movie.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author WangYi
 * @description:
 * @date :2020/6/3 22:32
 */
public class SearchModel implements ISearchContract.IModel {
    @Override
    public void GetSearch(String keyword, int page, int count, ICallBack callBack) {
        NetUtils.getInstance().getApis().getSearch(keyword,page,count)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ByKeywordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ByKeywordBean byKeywordBean) {
                        if(callBack!=null){
                            callBack.GetSearchSuccess(byKeywordBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBack!=null){
                            callBack.GetSearchFailure(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
