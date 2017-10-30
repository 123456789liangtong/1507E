package com.bwie.shixun1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.okhttp3ps.app.MyApp;
import com.bwie.shixun1.R;
import com.bwie.shixun1.bean.MusicBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by la on 2017/10/30.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<MusicBean.SongListBean> data;
    private Context context;

    public RecyclerAdapter(ArrayList<MusicBean.SongListBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        //使用view的条目点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自己获取position
                int position = viewHolder.getLayoutPosition();
                //设置监听
                if (listener != null) {
                    listener.onRecyclerViewItemClick(position);
                }
            }
        });
        //使用view的长按事件
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //自己获取position
                int position = viewHolder.getLayoutPosition();
                //设置监听
                if (longListener != null) {
                    longListener.onRecyclerViewLongItemClick(position);
                }
                //true代表消费事件 不继续传递
                return true;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.album_title.setText(data.get(position).getAlbum_title());
        holder.title2.setText(data.get(position).getTitle());
        holder.author.setText(data.get(position).getAuthor());
        ImageLoader.getInstance().displayImage(data.get(position).getPic_small(),holder.img, MyApp.options());

    }

    private OnRrecyclerViewItemClickListener listener;

    //定义接口 和抽象方法
    public interface OnRrecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }

    //提供设置监听的方法
    public void setOnRrecyclerViewItemClickListener(OnRrecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 条目长按
     */
//声明接口
    private OnRecyclerViewLongItemClickListener longListener;

    //定义接口 和抽象方法
    public interface OnRecyclerViewLongItemClickListener {
        void onRecyclerViewLongItemClick(int position);
    }

    //提供设置监听的方法
    public void setOnRecyclerViewLongItemClickListener(OnRecyclerViewLongItemClickListener longListener) {
        this.longListener = longListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView album_title;
        private ImageView img;
        private TextView title2;
        private TextView author;

        public ViewHolder(View itemView) {
            super(itemView);
            album_title=itemView.findViewById(R.id.album_title);
            img=itemView.findViewById(R.id.img);
            title2 = itemView.findViewById(R.id.title2);
            author = itemView.findViewById(R.id.author);
        }
    }
}