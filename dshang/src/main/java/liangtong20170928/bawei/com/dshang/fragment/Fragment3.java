package liangtong20170928.bawei.com.dshang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.app.MyApp;
import liangtong20170928.bawei.com.dshang.bean.ShoppingBean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by la on 2017/9/29.
 */

public class Fragment3 extends Fragment{

    private ExpandableListView expand;
    private CheckBox all2;
    private List<List<ShoppingBean.DatasBean.CartListBean.GoodsBean>> list2;
    private List<ShoppingBean.DatasBean.CartListBean> list;
    private Fragment3Adapter adapter;
    private TextView tv_price_num;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        expand = (ExpandableListView)view.findViewById(R.id.expand);
        all2 = (CheckBox) view.findViewById(R.id.rb_all);
        tv_price_num = view.findViewById(R.id.tv_num_price);

        String key = Urlpath_Utils.getShard(getActivity()).getString("key", "");
        Map<String,String> map = new HashMap<>();
        map.put("key",key);

        OkHttp3Utils.doPost(Urlpath_Utils.SHOPPING_SHOW,map, new GsonObjectCallback<ShoppingBean>() {
            @Override
            public void onUi(ShoppingBean shoppingBean) {

                list = shoppingBean.getDatas().getCart_list();
                list2 = new ArrayList<List<ShoppingBean.DatasBean.CartListBean.GoodsBean>>();

                System.out.println("=================="+list2+"+===================");

                for (int i = 0; i < list.size(); i++){
                    ShoppingBean.DatasBean.CartListBean cartListBean = list.get(i);
                    List<ShoppingBean.DatasBean.CartListBean.GoodsBean> goods = cartListBean.getGoods();
                    list2.add(goods);
                }

                //添加适配器
                adapter = new Fragment3Adapter(list,list2,getActivity());
                expand.setAdapter(adapter);

                int count = expand.getCount();
                for (int a = 0; a<count; a++){
                    expand.expandGroup(a);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

        //设置全选
        all2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i2 = 0; i2<list.size();i2++){
                    list.get(i2).setCheck(all2.isChecked());
                    for (int b = 0; b<list2.get(i2).size();b++){
                        list2.get(i2).get(b).setCheck2(all2.isChecked());
                    }
                }
                adapter.notifyDataSetChanged();
            }

        });
        return view;
    }

    class Fragment3Adapter extends BaseExpandableListAdapter {

        private List<ShoppingBean.DatasBean.CartListBean> list;
        List<List<ShoppingBean.DatasBean.CartListBean.GoodsBean>> list2;
        private Context context;

        private ImageView shop_img;
        private TextView shop_name1;
        private TextView shop_name2;
        private TextView shop_price;
        private TextView shop_num;

        public Fragment3Adapter(List<ShoppingBean.DatasBean.CartListBean> list, List<List<ShoppingBean.DatasBean.CartListBean.GoodsBean>> list2, Context context) {
            this.list = list;
            this.list2 = list2;
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return list.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return list2.get(i).size();
        }

        @Override
        public Object getGroup(int i) {
            return i;
        }

        @Override
        public Object getChild(int i, int i1) {
            return list2.get(i).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

        @Override
        public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
            GroupViewHolder gholder = null;
            if(view == null){
                gholder = new GroupViewHolder();
                view = View.inflate(context, R.layout.grouplist, null);
                gholder.shop_checkbox1 = view.findViewById(R.id.shop_rb1);
                gholder.shop_name = view.findViewById(R.id.shop_tv_name);
                view.setTag(gholder);
            }else{
                gholder = (GroupViewHolder)view.getTag();
            }
            ShoppingBean.DatasBean.CartListBean bean = list.get(i);
            gholder.shop_name.setText(bean.getStore_name());
            gholder.shop_checkbox1.setChecked(bean.isCheck());
            gholder.shop_checkbox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list.get(i).isCheck()){
                        list.get(i).setCheck(false);
                        all2.setChecked(false);
                        for (int i2 = 0; i2 < list2.get(i).size(); i2++){
                            list2.get(i).get(i2).setCheck2(false);
                        }
                    }else{
                        int num = 0;
                        list.get(i).setCheck(true);
                        for (int i2 = 0; i2<list2.get(i).size(); i2++){
                            list2.get(i).get(i2).setCheck2(true);
                        }
                        for (int j = 0; j<list.size(); j++){
                            if(list.get(j).isCheck()){
                                num++;
                            }
                        }
                        if(num == list.size()){
                            all2.setChecked(true);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
            return view;
        }

        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
            ChildViewHolder cholder = null;
            if(view == null){
                cholder = new ChildViewHolder();
                view = View.inflate(context, R.layout.childlist, null);
                cholder.shop_checkbox2 = view.findViewById(R.id.shop_rb2);
                cholder.shop_name2 =view.findViewById(R.id.shop_tv_name2);
                cholder.shop_price = view.findViewById(R.id.shop_tv_price);
                cholder.shop_img = view.findViewById(R.id.shop_img);
                cholder.shop_num = view.findViewById(R.id.shop_num);
                view.setTag(cholder);
            }else{
                cholder = (ChildViewHolder) view.getTag();
            }
            ShoppingBean.DatasBean.CartListBean.GoodsBean goodsBean = list.get(i).getGoods().get(i1);
            cholder.shop_name2.setText(goodsBean.getGoods_name());
            cholder.shop_price.setText(goodsBean.getGoods_price());
            cholder.shop_num.setText(goodsBean.getGoods_num());
            ImageLoader.getInstance().displayImage(goodsBean.getGoods_image_url(),cholder.shop_img, MyApp.options());
            cholder.shop_checkbox2.setChecked(goodsBean.isCheck2());
            cholder.shop_checkbox2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list2.get(i).get(i1).isCheck2()){
                        list2.get(i).get(i1).setCheck2(false);
                        //设置全选
                        all2.setChecked(false);
                        list.get(i).setCheck(false);
                    }else{
                        int num = 0;
                        int num1 = 0;
                        list2.get(i).get(i1).setCheck2(true);
                        for (int i3 = 0; i3<list2.get(i).size();i3++){
                            if(list2.get(i).get(i3).isCheck2()){
                                num++;
                            }
                        }
                        if(list2.get(i).size() == num){
                            list.get(i).setCheck(true);
                        }
                        for(int j = 0; j<list.size();j++){
                            if(list.get(j).isCheck()){
                                num1++;
                            }
                        }
                        if(num1 == list.size()){
                            //设置全局
                            all2.setChecked(true);
                        }
                    }
                    notifyDataSetChanged();
                }
            });
            return view;
        }
        public class GroupViewHolder{
            CheckBox shop_checkbox1;
            TextView shop_name;
        }

        public class ChildViewHolder{
            CheckBox shop_checkbox2;
            ImageView shop_img;
            TextView shop_name2;
            TextView shop_price;
            TextView shop_num;
        }

    }

}
