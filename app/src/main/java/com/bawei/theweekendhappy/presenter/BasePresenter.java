package com.bawei.theweekendhappy.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by la on 2017/11/5.
 */

public class BasePresenter<V> {

    protected WeakReference<V> weakReference;

    //p层与v层绑定
    public void attach(V view){
        weakReference=new WeakReference<V>(view);
    }
    //p层与v层取消绑定
    public void detach(){
        if(isAttach()){
            weakReference.clear();
            weakReference=null;
        }
    }
    //判断是否绑定
    public Boolean isAttach(){
        return weakReference!=null&&weakReference.get()!=null;
    }
    //得到view接口
    public V getAttachView(){
        return isAttach()?weakReference.get():null;
    }


}
