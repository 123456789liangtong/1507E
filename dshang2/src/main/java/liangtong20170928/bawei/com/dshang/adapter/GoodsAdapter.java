package liangtong20170928.bawei.com.dshang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.app.MyApp;
import liangtong20170928.bawei.com.dshang.bean.GoodsBean;

/**
 * Created by la on 2017/10/18.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder>{

    private List<GoodsBean.DatasBean.GoodsListBean> goodsListBeen;
    private Context context;

    public GoodsAdapter(List<GoodsBean.DatasBean.GoodsListBean> goodsListBeen, Context context) {
        this.goodsListBeen = goodsListBeen;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.goods_item, null);
        final ViewHolder holder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                listener.onRecyclerViewItemClick(position);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(GoodsAdapter.ViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(goodsListBeen.get(position).getGoods_image_url(),holder.iv_goods, MyApp.options());
        holder.tv_goods1.setText(goodsListBeen.get(position).getGoods_name());
        holder.tv_goods2.setText(goodsListBeen.get(position).getGoods_price());
    }

    private Fenlei_1_Adapter.OnRrecyclerViewItemClickListener listener;

    //定义接口 和抽象方法
    public interface OnRrecyclerViewItemClickListener {
        void onRecyclerViewItemClick(int position);
    }

    //提供设置监听的方法
    public void setOnRrecyclerViewItemClickListener(Fenlei_1_Adapter.OnRrecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return goodsListBeen.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_goods;
        private final TextView tv_goods1;
        private final TextView tv_goods2;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_goods = itemView.findViewById(R.id.iv_goods);
            tv_goods1 = itemView.findViewById(R.id.tv_goods1);
            tv_goods2 = itemView.findViewById(R.id.tv_goods2);
        }
    }
}
