package com.example.newsinfo.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.newsinfo.R;
import com.example.newsinfo.activity.WebViewActivity;
import com.example.newsinfo.adapter.MyBaseAdapter;
import com.example.newsinfo.bean.Bean;
import com.google.gson.Gson;
import com.limxing.xlistview.view.XListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by la on 2017/9/7.
 */

public class Fragment8 extends Fragment implements XListView.IXListViewListener{
    private XListView xlv1;
    private int pageIndex = 1;
    private boolean flag;
    private MyBaseAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment8, container, false);

        //加载布局文件
        xlv1 = view.findViewById(R.id.xlv1);
        xlv1.setPullLoadEnable(true);
        xlv1.setXListViewListener(this);

        xlv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //获取当前的条目
                Bean.ResultBean.DataBean dataBean = (Bean.ResultBean.DataBean) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url",dataBean.getUrl());
                startActivity(intent);
            }
        });

        getConnect("http://v.juhe.cn/toutiao/index?type=caijing&key=54e3d5f4ee64f51bef570ce8505d37b5");

        return view;
    }

    private void getConnect(String path){
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s == null){
                    return;
                }
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                List<Bean.ResultBean.DataBean> list = bean.getResult().getData();

                if(adapter == null){
                    adapter = new MyBaseAdapter(list,getActivity());
                    xlv1.setAdapter(adapter);
                }else{
                    adapter.loadMore(list,flag);
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    String s = strings[0];
                    URL url = new URL(s);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    int code = connection.getResponseCode();
                    if(code == 200){
                        InputStream is = connection.getInputStream();

                        String json = getJson(is);
                        return json;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(path);
    }
    private String getJson(InputStream is){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = is.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            is.close();
            baos.close();
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onRefresh() {
        flag = false;
        ++pageIndex;
        getConnect("http://v.juhe.cn/toutiao/index?type=caijing&key=54e3d5f4ee64f51bef570ce8505d37b5");
        xlv1.stopRefresh(true);
    }

    @Override
    public void onLoadMore() {

        flag = true;
        ++pageIndex;
        getConnect("http://v.juhe.cn/toutiao/index?type=caijing&key=54e3d5f4ee64f51bef570ce8505d37b5");
        xlv1.stopLoadMore();
    }
}
