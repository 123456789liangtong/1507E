package com.bawei.theweekendhappy.presenter;

/**
 * Created by la on 2017/11/5.
 */

public abstract class IMainPresenter<V> extends BasePresenter<V> {
    public abstract void getPage(String page);
}
