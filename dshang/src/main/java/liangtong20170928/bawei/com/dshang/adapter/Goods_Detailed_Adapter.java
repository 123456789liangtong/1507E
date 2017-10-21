package liangtong20170928.bawei.com.dshang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.bean.Goods_Detailed_Bean;

/**
 * Created by la on 2017/10/18.
 */

public class Goods_Detailed_Adapter extends RecyclerView.Adapter<Goods_Detailed_Adapter.ViewHolder> {

    private List<Goods_Detailed_Bean.DatasBean.GoodsInfoBean> goodsInfoBeen;
    private Context context;
    private OnRrecyclerViewItemClickListener listener;

    public Goods_Detailed_Adapter(List<Goods_Detailed_Bean.DatasBean.GoodsInfoBean> goodsInfoBeen, Context context) {
        this.goodsInfoBeen = goodsInfoBeen;
        this.context = context;
    }

    public void setOnRrecyclerViewItemClickListener(OnRrecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    //定义接口和抽象方法
    public interface OnRrecyclerViewItemClickListener{
        void onRecyclerViewItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.activity_goods_detailed_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return goodsInfoBeen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView name;
        private final TextView price;
        private final TextView location;
        private final TextView num;
        private final TextView youfei;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.goods_detailed_img);
            name = itemView.findViewById(R.id.goods_detailed_name);
            price = itemView.findViewById(R.id.goods_detailed_price);
            location = itemView.findViewById(R.id.goods_detailed_location);
            num = itemView.findViewById(R.id.goods_detailed_num);
            youfei = itemView.findViewById(R.id.goods_detailed_youfei);
        }
    }
}