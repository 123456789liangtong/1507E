package bawei.com.zonghe.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bawei.com.zonghe.R;
import bawei.com.zonghe.adapter.MyAdapter;
import bawei.com.zonghe.adapter.RecyclerViewAdapter;
import bawei.com.zonghe.bean.NewBean;
import bawei.com.zonghe.utils.OkHttp3Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by la on 2017/10/25.
 */

public class Fragment01 extends Fragment {

    private PullLoadMoreRecyclerView recycler;
    private int page=1;
    private List<NewBean.StoriesBean> list2;
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String obj = (String) msg.obj;
                    Gson gson = new Gson();
                    NewBean bean = gson.fromJson(obj, NewBean.class);
                    List<NewBean.StoriesBean> data = bean.getStories();
                    if(page == 1){
                        list2 = new ArrayList<>();
                    }
                    for(int i = 0; i<data.size();i++){
                        list2.add(data.get(i));
                        //list2.add(data.get(i));
                    }
                    //绑定数据
                    dataAdapter(list2);
                    break;
            }
        }
    };

    private int a = 0;
    private RadioGroup rg;
    private ViewPager viewPager;
    private List<String> list = new ArrayList<String>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            viewPager.setCurrentItem(what);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, container, false);

        //获取组件id
        rg = (RadioGroup) view.findViewById(R.id.rg);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        recycler = view.findViewById(R.id.recycler);

        recycler =view.findViewById(R.id.recycler);
        recycler.setLinearLayout();
        recycler.setPullRefreshEnable(true);
        recycler.setPushRefreshEnable(true);

        initData();
        recycler.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page =1;
                        recycler.setPullLoadMoreCompleted();
                    }
                },2000);
            }


            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        initData();
                        recycler.setPullLoadMoreCompleted();
                    }
                },2000);
            }
        });

        list.add("http://p4.gexing.com/G1/M00/32/DB/rBACE1PCoGbg2oWkAATJwjqkeDU726.jpg");
        list.add("http://p4.gexing.com/G1/M00/32/DB/rBACE1PCoGbg2oWkAATJwjqkeDU726.jpg");
        list.add("http://p4.gexing.com/G1/M00/32/DB/rBACE1PCoGbg2oWkAATJwjqkeDU726.jpg");

        MyAdapter adapter = new MyAdapter(list, getActivity());
        viewPager.setAdapter(adapter);
        getRadioPager();

        //开启线程，自动轮播
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                        a++;
                        handler.sendEmptyMessage(a);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        return view;



    }

    private void dataAdapter(List<NewBean.StoriesBean> list2){
        RecyclerViewAdapter adapter = null;
        if(adapter == null){
            adapter = new RecyclerViewAdapter(list2,getActivity());
            recycler.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    //关联按钮和页面的方法
    private void getRadioPager() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position % list.size()) {
                    case 0:
                        rg.check(R.id.rb1);
                        break;
                    case 1:
                        rg.check(R.id.rb2);
                        break;
                    case 2:
                        rg.check(R.id.rb3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i % list.size()) {
                    case R.id.rb1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        viewPager.setCurrentItem(2);
                        break;

                }
            }
        });
    }

    private void initData(){
        String path = "http://news-at.zhihu.com/api/4/news/latest";
        OkHttp3Utils.doGet(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=json;
                handler2.sendMessage(message);
            }
        });
    }
}

