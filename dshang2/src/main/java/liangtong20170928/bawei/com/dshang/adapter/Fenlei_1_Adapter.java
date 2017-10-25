package liangtong20170928.bawei.com.dshang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.app.MyApp;
import liangtong20170928.bawei.com.dshang.bean.Fenlei_1Bean;

/**
 * Created by la on 2017/10/17.
 */

public class Fenlei_1_Adapter extends RecyclerView.Adapter<Fenlei_1_Adapter.ViewHolder>{

    private List<Fenlei_1Bean.DatasBean.ClassListBean> data;
    private Context context;
    private OnRrecyclerViewItemClickListener listener;

    public Fenlei_1_Adapter(List<Fenlei_1Bean.DatasBean.ClassListBean> data, Context context) {
        this.data = data;
        this.context = context;

    }
    //提供设置监听的方法
    public void setOnRrecyclerViewItemClickListener(OnRrecyclerViewItemClickListener listener) {
        this.listener = listener;
    }


    //定义接口 和抽象方法
    public interface OnRrecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_1_item, parent, false);
        final ViewHolder viewHolder=new ViewHolder(view);
        //使用view的条目点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //自己获取position
                int position = viewHolder.getLayoutPosition();

                listener.onRecyclerViewItemClick(position);

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getGc_name());
        ImageLoader.getInstance().displayImage(data.get(position).getImage(),holder.img, MyApp.options());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_fenlei_1);
            img = itemView.findViewById(R.id.iv_fenlei_1);
        }

    }
}
