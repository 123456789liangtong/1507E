package com.bawei.theweekendhappy.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.theweekendhappy.R;
import com.bawei.theweekendhappy.bean.MyBean;

import java.util.List;

/**
 * Created by la on 2017/11/6.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<MyBean.DataBean> data;
    private Context context;

    public RecyclerViewAdapter(List<MyBean.DataBean> data, Context context) {
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
        holder.title.setText(data.get(position).getTitle());
        Uri uri = Uri.parse(data.get(position).getImg());
        holder.img.setImageURI(uri);
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
        private TextView title;
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv);
            img = itemView.findViewById(R.id.iv);
        }
    }
}