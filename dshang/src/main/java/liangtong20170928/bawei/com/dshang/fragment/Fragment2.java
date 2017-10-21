package liangtong20170928.bawei.com.dshang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.adapter.Fenlei_1_Adapter;
import liangtong20170928.bawei.com.dshang.adapter.Fenlei_2_Adapter;
import liangtong20170928.bawei.com.dshang.bean.Fenlei_1Bean;
import liangtong20170928.bawei.com.dshang.bean.Fenlei_2Bean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by la on 2017/9/29.
 */

public class Fragment2 extends Fragment{

    private RecyclerView f_recycler1;
    private RecyclerView f_recycler2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);

        f_recycler1 = view.findViewById(R.id.f_recycler1);
        f_recycler2 = view.findViewById(R.id.f_recycler2);

        //一级分类
        OkHttp3Utils.doGet(Urlpath_Utils.FENLEI_1, new GsonObjectCallback<Fenlei_1Bean>() {
            @Override
            public void onUi(Fenlei_1Bean fenlei_1Bean) {
                final List<Fenlei_1Bean.DatasBean.ClassListBean> class_list = fenlei_1Bean.getDatas().getClass_list();

                f_recycler1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

                final Fenlei_1_Adapter adapter = new Fenlei_1_Adapter(class_list,getActivity());
                f_recycler1.setAdapter(adapter);

                //点击展示二级列表
                adapter.setOnRrecyclerViewItemClickListener(new Fenlei_1_Adapter.OnRrecyclerViewItemClickListener() {
                    @Override
                    public void onRecyclerViewItemClick(final int position) {
                        int i = Integer.parseInt(class_list.get(position).getGc_id());

                        OkHttp3Utils.doGet(Urlpath_Utils.FENLEI_23+i, new GsonObjectCallback<Fenlei_2Bean>() {
                            @Override
                                public void onUi(Fenlei_2Bean fenlei_2Bean) {

                                List<Fenlei_2Bean.DatasBean.ClassListBean> class_list1 = fenlei_2Bean.getDatas().getClass_list();
                                f_recycler2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                                Fenlei_2_Adapter adapter2 = new Fenlei_2_Adapter(class_list1,getActivity());
                                f_recycler2.setAdapter(adapter2);
                            }

                            @Override
                            public void onFailed(Call call, IOException e) {

                            }
                        });
                    }
                });

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


        return view;
    }


}
