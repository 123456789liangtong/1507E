package bawei.com.zonghe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bawei.com.zonghe.MyApplication;
import bawei.com.zonghe.R;
import bawei.com.zonghe.bean.ZuixinBean;

/**
 * Created by la on 2017/10/22.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ZuixinBean.StoriesBean> data;
    private Context context;

    public RecyclerViewAdapter(List<ZuixinBean.StoriesBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        ImageLoader.getInstance().displayImage(data.get(position).getImages().get(position),holder.img, MyApplication.options());
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
            title = itemView.findViewById(R.id.title_data);
            img = itemView.findViewById(R.id.img_data);
        }
    }
}