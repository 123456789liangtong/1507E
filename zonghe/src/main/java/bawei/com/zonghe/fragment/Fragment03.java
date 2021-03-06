package bawei.com.zonghe.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwie.mycartutils.bean.ChildBean;
import com.bwie.mycartutils.bean.GroupBean;
import com.bwie.mycartutils.utils.CartUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bawei.com.zonghe.R;
import bawei.com.zonghe.bean.ShopBean;
import bawei.com.zonghe.utils.OkHttp3Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by la on 2017/10/25.
 */

public class Fragment03 extends Fragment{
    private ExpandableListView elv;
    private CheckBox all;
    private TextView all_count,all_money;
    private List<GroupBean> filst;
    private List<List<ChildBean>> clist;
    private String url="http://120.27.23.105/product/searchProducts?keywords=%E7%AC%94%E8%AE%B0%E6%9C%AC&page=1";
    private List<ShopBean> list;

    private Handler handler=new Handler(){
        private List<ShopBean.DataBean> data;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json=(String) msg.obj;
            Gson gson=new Gson();
            ShopBean bean=gson.fromJson(json,ShopBean.class);
            List<ShopBean.DataBean> data = bean.getData();
            for (int i=0;i<data.size();i++){
                filst.add(new GroupBean(data.get(i).getTitle(),false));
                List<ChildBean> slist=new ArrayList<>();
                for (int j=0;j<filst.size();j++){
                    slist.add(new ChildBean(data.get(j).getTitle(),data.get(j).getPrice()+"",data.get(j).getImages(),false,1));
                }
                clist.add(slist);
            }
            CartUtils.setCartData(getActivity(),filst,clist,elv,all,all_count,all_money);

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment03, container, false);

        elv= (ExpandableListView) view.findViewById(R.id.elv);
        all= (CheckBox) view.findViewById(R.id.all);
        all_count= (TextView) view.findViewById(R.id.all_count);
        all_money= (TextView) view.findViewById(R.id.all_money);

        setData();
        return view;
    }

    private void setData() {
        filst=new ArrayList<>();
        clist=new ArrayList<>();
        OkHttp3Utils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                Message message=new Message();
                message.obj=s;
                handler.sendMessage(message);
            }
        });
    }

}
