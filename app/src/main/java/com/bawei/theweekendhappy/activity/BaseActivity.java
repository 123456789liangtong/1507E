package com.bawei.theweekendhappy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bawei.theweekendhappy.presenter.BasePresenter;

/**
 * Created by la on 2017/11/5.
 */

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends Activity {

    protected P mpresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局
        setContentView(initView());
        mpresenter=getPresenter();
        mpresenter.attach((V) this);
        initData();


    }
    //初始化布局
    public abstract int initView();
    //初始化
    public abstract void initData();
    //获得p层
    public abstract P getPresenter();
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mpresenter.detach();
    }
}
