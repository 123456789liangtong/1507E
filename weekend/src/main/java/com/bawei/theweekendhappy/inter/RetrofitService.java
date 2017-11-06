package com.bawei.theweekendhappy.inter;

import com.bawei.theweekendhappy.bean.MyBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by la on 2017/11/5.
 */

public interface RetrofitService {
    @GET("page_{page}.json")
    Flowable<List<MyBean>> getData(@Path("page")String page);
}
