package liangtong20170928.bawei.com.dshang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.bean.Fenlei_3Bean;

/**
 * Created by la on 2017/10/17.
 */

public class Fenlei_3_Adapter extends RecyclerView.Adapter<Fenlei_3_Adapter.ViewHolder>{

    private List<Fenlei_3Bean.DatasBean.ClassListBean> beanList;
    private Context context;

    public Fenlei_3_Adapter(List<Fenlei_3Bean.DatasBean.ClassListBean> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_2_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.title.setText(beanList.get(position).getGc_name());
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_fenlei_2);
        }

    }

}
