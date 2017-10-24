package com.example.newsinfo.sqilte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.newsinfo.bean.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by la on 2017/9/21.
 */

public class MyManager {



    private Context context;
    private SQLiteDatabase db;

    public MyManager(Context context) {
        this.context = context;

        MyHelper helper = new MyHelper(context);
        db = helper.getWritableDatabase();
    }

    public void add(List<Bean.ResultBean.DataBean> list){

        for (int i = 0;i<list.size();i++){
            Bean.ResultBean.DataBean bean1 = list.get(i);
            ContentValues values = new ContentValues();
            values.put("title",bean1.getTitle());
            values.put("img1",bean1.getThumbnail_pic_s());
            values.put("img2",bean1.getThumbnail_pic_s02());
            values.put("img3",bean1.getThumbnail_pic_s03());

            //插入数据库
            db.insert("news",null,values);
        }


        }

    public List<Bean.ResultBean.DataBean> cha(){
        List<Bean.ResultBean.DataBean> list = new ArrayList<>();
        //查询数据库
        Cursor cursor = db.query("news", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String img1 = cursor.getString(cursor.getColumnIndex("img1"));
            String img2 = cursor.getString(cursor.getColumnIndex("img2"));
            String img3 = cursor.getString(cursor.getColumnIndex("img3"));

            Bean.ResultBean.DataBean dataBean = new Bean.ResultBean.DataBean();
            dataBean.setTitle(title);
            dataBean.setThumbnail_pic_s(img1);
            dataBean.setThumbnail_pic_s02(img2);
            dataBean.setThumbnail_pic_s03(img3);

            list.add(dataBean);
        }
        return list;
    }



}
