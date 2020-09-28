package com.bw.movie.base;

import java.lang.ref.WeakReference;

/**
 * @author WangYi
 * @description:
 * @date :2020/5/19 19:07
 */
public  abstract class BasePresenter<V extends IBaseView> {

    private  WeakReference<V> weakReference;

    public BasePresenter(V v) {
        weakReference = new WeakReference<>(v);
        initModel();
    }
    public V getView(){
        if(weakReference!=null){
            return weakReference.get();
        }
        return null;
    }
    public void deatchView(){
        if(weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }

    protected abstract void initModel();
}
