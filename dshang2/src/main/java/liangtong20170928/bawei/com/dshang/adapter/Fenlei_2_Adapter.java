package liangtong20170928.bawei.com.dshang.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.bean.Fenlei_2Bean;
import liangtong20170928.bawei.com.dshang.bean.Fenlei_3Bean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by la on 2017/10/17.
 */

public class Fenlei_2_Adapter extends RecyclerView.Adapter<Fenlei_2_Adapter.ViewHolder>{

    private List<Fenlei_2Bean.DatasBean.ClassListBean> beanList;
    private Context context;

    public Fenlei_2_Adapter(List<Fenlei_2Bean.DatasBean.ClassListBean> beanList, Context context) {
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        System.out.println(beanList.get(position).getGc_name());
        holder.title.setText(beanList.get(position).getGc_name());
        holder.f_recycler3.setLayoutManager(new GridLayoutManager(context,3));

        int i = Integer.parseInt(beanList.get(position).getGc_id());

        OkHttp3Utils.doGet(Urlpath_Utils.FENLEI_23+i, new GsonObjectCallback<Fenlei_3Bean>() {
            @Override
            public void onUi(Fenlei_3Bean fenlei_3Bean) {
                List<Fenlei_3Bean.DatasBean.ClassListBean> class_list = fenlei_3Bean.getDatas().getClass_list();

                Fenlei_3_Adapter adapter = new Fenlei_3_Adapter(class_list,context);
                holder.f_recycler3.setAdapter(adapter);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView f_recycler3;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_fenlei_2);
            f_recycler3 = itemView.findViewById(R.id.f_recycler3);
        }

    }

}
