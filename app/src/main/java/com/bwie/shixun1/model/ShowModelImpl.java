package com.bwie.shixun1.model;

import android.content.Context;
import android.util.Log;

import com.bwei.okhttp3ps.utils.OkHttp3Utils;
import com.bwie.shixun1.bean.MusicBean;
import com.bwie.shixun1.presenter.Finish;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by la on 2017/10/30.
 */

public class ShowModelImpl implements ShowModel{
    @Override
    public void showmodel(Context context, final Finish finish) {
        String path = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0";
        OkHttp3Utils.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("SSSS",string);
                Gson gson = new Gson();
                MusicBean bean = gson.fromJson(string, MusicBean.class);

                ArrayList<MusicBean> list = new ArrayList<MusicBean>();
                list.add(bean);

                finish.finish(list);

            }
        });
    }
}
