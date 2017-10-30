package com.bwie.shixun1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bwie.shixun1.adapter.RecyclerAdapter;
import com.bwie.shixun1.bean.MusicBean;
import com.bwie.shixun1.presenter.ShowPresenterImpl;
import com.bwie.shixun1.view.ShowView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ShowView{

    private RecyclerView recycler;
    private ArrayList<MusicBean.SongListBean> song_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取组件id
        recycler = (RecyclerView) findViewById(R.id.recycler);

        //设置布局管理器
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ShowPresenterImpl presenter = new ShowPresenterImpl(this);
        presenter.showpresenter(this);

    }

    @Override
    public void showview(ArrayList<MusicBean> musicBeen) {

        for (int i = 0; i<musicBeen.size();i++){
            song_list = musicBeen.get(i).getSong_list();
        }

        RecyclerAdapter adapter = new RecyclerAdapter(song_list,MainActivity.this);
        recycler.setAdapter(adapter);
    }
}
