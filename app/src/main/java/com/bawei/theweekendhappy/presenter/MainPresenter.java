package com.bawei.theweekendhappy.presenter;

import com.bawei.theweekendhappy.model.IModelMain;
import com.bawei.theweekendhappy.model.ModelMain;
import com.bawei.theweekendhappy.view.IMainView;

/**
 * Created by la on 2017/11/5.
 */

public class MainPresenter extends IMainPresenter<IMainView>{
    IModelMain iModelMain;
    IMainView iMainView;
    public MainPresenter() {
        iModelMain=new ModelMain();
    }


    @Override
    public void getPage(String page) {
        iModelMain.getData(page);
    }
}
