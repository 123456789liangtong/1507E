package com.bawei.theweekendhappy.model;

import android.util.Log;

import com.bawei.theweekendhappy.api.API;
import com.bawei.theweekendhappy.bean.MyBean;
import com.bawei.theweekendhappy.inter.RetrofitService;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by la on 2017/11/5.
 */

public class ModelMain implements IModelMain{

    @Override
    public void getData(String page) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API.path)
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Flowable<List<MyBean>> listFlowable = service.getData(page);
        listFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<MyBean>, Publisher<MyBean.DataBean>>() {
                    @Override
                    public Publisher<MyBean.DataBean> apply(@NonNull List<MyBean> myBeen) throws Exception {
                        List<MyBean.DataBean> list=myBeen.get(0).getData();
                        return Flowable.fromIterable(list);
                    }
                })
                .subscribeWith(new DisposableSubscriber<MyBean.DataBean>() {
                    @Override
                    public void onNext(MyBean.DataBean dataBean) {
                        Log.e("SSSS",dataBean.toString()+"请求数据");
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
