package com.bwie.shixun1.presenter;

import android.content.Context;

import com.bwie.shixun1.bean.MusicBean;
import com.bwie.shixun1.model.ShowModel;
import com.bwie.shixun1.model.ShowModelImpl;
import com.bwie.shixun1.view.ShowView;

import java.util.ArrayList;

/**
 * Created by la on 2017/10/30.
 */

public class ShowPresenterImpl implements ShowPresenter,Finish{
    private ShowModel showModel;
    private ShowView showView;

    public ShowPresenterImpl( ShowView showView) {
        this.showView = showView;
        showModel = new ShowModelImpl();
    }

    @Override
    public void showpresenter(Context context) {
        showModel.showmodel(context,this);
    }

    @Override
    public void finish(ArrayList<MusicBean> musicBeen) {
        showView.showview(musicBeen);
    }
}
