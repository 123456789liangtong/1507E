package com.bawei.theweekendhappy.activity;

import android.net.Uri;

import com.bawei.theweekendhappy.R;
import com.bawei.theweekendhappy.presenter.MainPresenter;
import com.bawei.theweekendhappy.view.IMainView;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends BaseActivity<IMainView,MainPresenter> {

    private SimpleDraweeView image;
    private MainPresenter mainPresenter;

    @Override
    public int initView() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        //获取资源id
        image=(SimpleDraweeView) findViewById(R.id.image);
        Uri parse = Uri.parse("res://" + getPackageName() + "/" + R.drawable.ic_launcher);
        image.setImageURI(parse);
        //网络请求
        getHttpData();
    }

    @Override
    public MainPresenter getPresenter() {
        mainPresenter = new MainPresenter();
        return mainPresenter;
    }

    public void getHttpData() {
        mainPresenter.getPage("1");
    }
}
