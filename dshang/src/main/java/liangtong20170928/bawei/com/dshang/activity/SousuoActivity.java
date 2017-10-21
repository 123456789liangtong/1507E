package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.adapter.Fenlei_1_Adapter;
import liangtong20170928.bawei.com.dshang.adapter.GoodsAdapter;
import liangtong20170928.bawei.com.dshang.bean.GoodsBean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

public class SousuoActivity extends AppCompatActivity {

    private ImageView iv_return;
    private EditText et_sousuo2;
    private ImageView iv_sousuo;
    private RecyclerView recycler_goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);

        iv_return = (ImageView) findViewById(R.id.iv_return);   //返回上一层页面
        et_sousuo2 = (EditText) findViewById(R.id.et_sousuo2);  //搜索框搜索
        iv_sousuo = (ImageView) findViewById(R.id.iv_sousuo);   //确认搜索
        recycler_goods = (RecyclerView) findViewById(R.id.recycler_goods);

        //返回上一层界面
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iv_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OkHttp3Utils.doGet(Urlpath_Utils.SOUSUO, new GsonObjectCallback<GoodsBean>() {
                    @Override
                    public void onUi(GoodsBean goodsBean) {
                        final List<GoodsBean.DatasBean.GoodsListBean> goods_list = goodsBean.getDatas().getGoods_list();
                        LinearLayoutManager manager = new LinearLayoutManager(SousuoActivity.this);
                        GoodsAdapter adapter = new GoodsAdapter(goods_list,SousuoActivity.this);
                        recycler_goods.setAdapter(adapter);
                        recycler_goods.setLayoutManager(manager);

                        //点击跳转到商品详细
                        adapter.setOnRrecyclerViewItemClickListener(new Fenlei_1_Adapter.OnRrecyclerViewItemClickListener() {
                            @Override
                            public void onRecyclerViewItemClick(final int position) {

                                Intent intent = new Intent(SousuoActivity.this,Goods_detailed_Activity.class);

                                intent.putExtra("id", goods_list.get(position).getGoods_id());

                                startActivity(intent);

                            }
                        });
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });
            }
        });
    }
}
