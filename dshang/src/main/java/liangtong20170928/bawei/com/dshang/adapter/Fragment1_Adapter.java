package liangtong20170928.bawei.com.dshang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import liangtong20170928.bawei.com.dshang.R;

/**
 * Created by la on 2017/10/22.
 */

public class Fragment1_Adapter extends RecyclerView.Adapter<Fragment1_Adapter.MyViewHolder>{

    private Context context;
    private JSONArray data;

    public Fragment1_Adapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
    }

        @Override
    public void onBindViewHolder(Fragment1_Adapter.MyViewHolder holder, int position) {

        try {
            JSONObject jsonObject = data.getJSONObject(position);
            String image_url = jsonObject.optString("image");
            String title = jsonObject.getString("title");
            holder.tv_fragm2.setText(title);
            Picasso.with(context).load(image_url).placeholder(R.mipmap.ic_launcher).into(holder.iv_fragm1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    @Override
    public Fragment1_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment1_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_fragm1;
        private TextView tv_fragm2;


        public MyViewHolder(View itemView) {
            super(itemView);

            iv_fragm1 = (ImageView) itemView.findViewById(R.id.iv_fragm1);
            tv_fragm2 = (TextView) itemView.findViewById(R.id.tv_fragm2);

        }
    }
}
