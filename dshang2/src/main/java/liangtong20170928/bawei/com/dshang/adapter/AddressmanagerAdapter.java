package liangtong20170928.bawei.com.dshang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.bean.AddressBean;

/**
 * Created by la on 2017/10/22.
 */

public class AddressmanagerAdapter extends BaseAdapter{

    private List<AddressBean.DatasBean.AddressListBean> list;
    private Context context;

    public AddressmanagerAdapter(List<AddressBean.DatasBean.AddressListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = View.inflate(context, R.layout.address_manager_item, null);
        }

        TextView true_name_ = view.findViewById(R.id.true_name_);
        TextView mob_phone_ = view.findViewById(R.id.mob_phone_);
        TextView city_id_ = view.findViewById(R.id.city_id_);
        TextView area_id_ = view.findViewById(R.id.area_id_);
        TextView address_ = view.findViewById(R.id.address_);
        TextView area_info_ = view.findViewById(R.id.area_info_);

        AddressBean.DatasBean.AddressListBean bean = list.get(i);
        true_name_.setText(bean.getTrue_name());
        mob_phone_.setText(bean.getMob_phone());
        city_id_.setText(bean.getCity_id());
        area_id_.setText(bean.getArea_id());
        address_.setText(bean.getAddress());
        area_info_.setText(bean.getArea_info());

        return view;
    }
}
