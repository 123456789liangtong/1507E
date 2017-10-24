package com.example.newsinfo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsinfo.R;
import com.example.newsinfo.bean.Bean;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by la on 2017/9/8.
 */

public class MyBaseAdapter extends BaseAdapter{
    private List<Bean.ResultBean.DataBean> list = new ArrayList<Bean.ResultBean.DataBean>();
    private Context context;
    private DisplayImageOptions options;

    public MyBaseAdapter(List<Bean.ResultBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        options = new DisplayImageOptions.Builder().build();
    }

    //加载更多
    public void loadMore(List<Bean.ResultBean.DataBean> dataBean,boolean flag){
        for (Bean.ResultBean.DataBean dataBean1 : dataBean){
            if(flag){
                list.add(dataBean1);
            }else{
                list.add(0,dataBean1);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        switch (type){
            case 0:
                    if(view == null){
                        view = View.inflate(context, R.layout.item,null);
                    }
                TextView tv1 = view.findViewById(R.id.tv1);
                ImageView iv1 = view.findViewById(R.id.iv1_1);
                ImageView iv2 = view.findViewById(R.id.iv1_2);
                ImageView iv3 = view.findViewById(R.id.iv1_3);

                Bean.ResultBean.DataBean bean = list.get(i);
                tv1.setText(bean.getTitle());
                ImageLoader.getInstance().displayImage(bean.getThumbnail_pic_s(),iv1);
                ImageLoader.getInstance().displayImage(bean.getThumbnail_pic_s02(),iv2);
                ImageLoader.getInstance().displayImage(bean.getThumbnail_pic_s03(),iv3);

                break;
            case 1:
                    if(view == null){
                        view = View.inflate(context, R.layout.item2, null);
                    }
                    ImageView iv2_1 = view.findViewById(R.id.iv2_1);
                    TextView tv2_1 = view.findViewById(R.id.tv2_1);

                Bean.ResultBean.DataBean bean1 = list.get(i);
                tv2_1.setText(bean1.getTitle());
                ImageLoader.getInstance().displayImage(bean1.getThumbnail_pic_s(),iv2_1);
                break;
        }
        return view;
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0){
            return 0;
        }else{
            return 1;
        }
    }



    private void setImage(String path,ImageView img){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//让图片进行内存缓存
                .cacheOnDisk(true)//让图片进行sdcard缓存
                .showImageOnFail(R.mipmap.ic_launcher_round)//当图片加载出现错误的时候显示的图片
                .showImageOnLoading(R.mipmap.ic_launcher)//图片正在加载的时候显示的图片
                .build();
        ImageLoader.getInstance().displayImage(path, img, options);
    }
}
