package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.searchview_library.SearchALG;
import com.example.searchview_library.SearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.adapter.Fenlei_1_Adapter;
import liangtong20170928.bawei.com.dshang.adapter.GoodsAdapter;
import liangtong20170928.bawei.com.dshang.bean.GoodsBean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

public class SearchActicity extends AppCompatActivity {

    private SearchView searchView;
    private SearchALG searchALG;
    private RecyclerView recycler_goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        searchView = (SearchView) findViewById(R.id.searchView);
        recycler_goods = (RecyclerView) findViewById(R.id.recycler_goods);

        initData();

        searchView.setOnSearchListener(new MyOnSearchListener());
    }

    private List<String> changedHintDatas;

    /**
     * 设置searview的监听
     */
    class MyOnSearchListener implements SearchView.OnSearchListener {

        /**
         * 搜索回调
         * @param searchText 进行搜索的文本
         */
        @Override
        public void onSearch(String searchText) {
            if (!TextUtils.isEmpty(searchText)) {

                OkHttp3Utils.doGet(Urlpath_Utils.SOUSUO, new GsonObjectCallback<GoodsBean>(){

                    @Override
                    public void onUi(GoodsBean goodsBean) {
                        final List<GoodsBean.DatasBean.GoodsListBean> goods_list = goodsBean.getDatas().getGoods_list();
                        LinearLayoutManager manager = new LinearLayoutManager(SearchActicity.this);
                        GoodsAdapter adapter = new GoodsAdapter(goods_list,SearchActicity.this);
                        recycler_goods.setAdapter(adapter);
                        recycler_goods.setLayoutManager(manager);

                        adapter.setOnRrecyclerViewItemClickListener(new Fenlei_1_Adapter.OnRrecyclerViewItemClickListener() {
                            @Override
                            public void onRecyclerViewItemClick(final int position) {

                                Intent intent = new Intent(SearchActicity.this,Goods_detailed_Activity.class);

                                intent.putExtra("id", goods_list.get(position).getGoods_id());

                                startActivity(intent);

                            }
                        });
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });

                //Toast.makeText(SearchActicity.this, "完成搜索" + searchText, Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(SearchActicity.this, "搜索内容不能为空！", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 刷新提示列表
         * @param changedText 改变后的文本
         */
        @Override
        public void onRefreshHintList(String changedText) {
            if (changedHintDatas == null) {
                changedHintDatas = new ArrayList<>();
            } else {
                changedHintDatas.clear();
            }
            if (TextUtils.isEmpty(changedText)) {
                return;
            }
            for (int i = 0; i < hint_datas.size(); i++) {
                String hint_data = hint_datas.get(i);
                boolean isAdd = searchALG.isAddToHintList(hint_data, changedText);
                if (isAdd) {
                    changedHintDatas.add(hint_datas.get(i));
                }
            }

            /**
             * 根据搜索框文本的变化，动态的改变提示的listView
             */
            searchView.updateHintList(changedHintDatas);

        }
    }

    //热搜数据
    private List<String> hot_datas;
    //提示列表数据
    private List<String> hint_datas;

    private void initData() {
        hot_datas = new ArrayList<>();
        hint_datas = new ArrayList<>();

        searchALG = new SearchALG(this);

        for (int i = 0; i < 10; i++) {
            hot_datas.add("Android Hot " + i);
        }

        //设置热搜数据显示的列数
        searchView.setHotNumColumns(2);
        //设置热搜数据
        searchView.setHotSearchDatas(hot_datas);

        /**
         * 设置提示数据的集合
         */
        for (int i = 0; i < 10; i++) {
            hint_datas.add("ts"+"安卓学习" + "Android Hint " + i + " 你好");
        }

        /**
         * 设置自动保存搜索记录
         */
        searchView.keepSearchHistory(true);

        //设置提示列表的最大显示列数
        searchView.setMaxHintLines(8);
        //设置保存搜索记录的个数
        searchView.setMaxHistoryRecordCount(6);

    }
}
