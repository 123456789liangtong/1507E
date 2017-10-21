package liangtong20170928.bawei.com.dshang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.activity.SearchActicity;
import liangtong20170928.bawei.com.dshang.app.GlideImageLoader;

/**
 * Created by la on 2017/9/29.
 */

public class Fragment1 extends Fragment{

    private RecyclerView recyclerView;
    private EditText et_sousuo;
    private List<String> mListImage;
    Banner banner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        //获取组件id
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        et_sousuo = (EditText) view.findViewById(R.id.et_sousuo1);
        banner = view.findViewById(R.id.banner);

        //找到控件
        banner= (Banner) view.findViewById(R.id.banner);

        //加载图片的方法
        banner.setImageLoader(new GlideImageLoader());
        mListImage= new ArrayList<>();

        //想图片集合中添加网网址
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508339104220&di=edf1a1582db43bf3ab96ef196edc6be4&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F29%2F29%2F69y58PICCQ3_1024.jpg");
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508339104216&di=3fdec533f17eca412dc8e0489f7b3a04&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F18%2F04%2F37%2F55c61be8b2fc0_1024.jpg");
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508342080216&di=32df6cdd5e749effeafd09a2fbdc1cdb&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F160916%2F105-16091610443Y43.jpg");
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508342080215&di=825fb78c2d2c8b38f82aa299f3a52256&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01adbf554b40ed000001bf722da621.jpg");
        mListImage.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508342080210&di=a4fc6891c1066e74fd4b140e61f04c45&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01ea78558b662f0000001fa3e0542e.jpg%40900w_1l_2o_100sh.jpg");

        //设置Banner图片集合
        banner.setImages(mListImage);
        //切换的时间
        banner.setDelayTime(2000);
        //启动banner
        banner.start();

        //点击搜索框跳转
        et_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchActicity.class);
                startActivity(intent);
            }
        });

        return view;
    }



}
