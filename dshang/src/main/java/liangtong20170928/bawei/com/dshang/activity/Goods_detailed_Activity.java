package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.app.MyApp;
import liangtong20170928.bawei.com.dshang.bean.ExitBean;
import liangtong20170928.bawei.com.dshang.bean.Goods_Detailed_Bean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

public class Goods_detailed_Activity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private ImageView img;
    private TextView name;
    private TextView price;
    private TextView location;
    private TextView num;
    private TextView youfei;
    private WebView webView;
    private Button gouwuche;
    private Button buy;
    private PopupWindow popupWindow;
    private View contentView;
    private Button pop_bt;
    private Button bt_jia;
    private int  pop_num2= 1;
    private Button bt_jian;
    private ImageView pop_img;
    private TextView pop_price;
    private TextView num_price;
    private TextView pop_num1;
    private String price2;
    private String img2;
    private Double pp;
    private String name2;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detailed_item);

        //获取组件id
        initData();

        //方法
        Data();

    }
    //获取组件id
    private void initData(){
        //子布局id
        back = (ImageView) findViewById(R.id.goods_detailed_back);
        img = (ImageView) findViewById(R.id.goods_detailed_img);
        name = (TextView) findViewById(R.id.goods_detailed_name);
        price = (TextView) findViewById(R.id.goods_detailed_price);
        location = (TextView) findViewById(R.id.goods_detailed_location);
        num = (TextView) findViewById(R.id.goods_detailed_num);
        youfei = (TextView) findViewById(R.id.goods_detailed_youfei);
        webView = (WebView) findViewById(R.id.webView);
        gouwuche = (Button) findViewById(R.id.bt_gouwuche);
        gouwuche.setOnClickListener(this);
        buy = (Button) findViewById(R.id.bt_buy);
    }

    //popWindow监听
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bt_gouwuche:
                showPopWindow();

                popupWindow.showAtLocation(contentView, Gravity.BOTTOM,0,0);

                //popWindow_id
                pop_bt = (Button) contentView.findViewById(R.id.pop_bt);
                bt_jia = (Button) contentView.findViewById(R.id.pop_bt_jia);
                bt_jian = (Button) contentView.findViewById(R.id.pop_bt_jian);
                pop_img = (ImageView) contentView.findViewById(R.id.pop_img);
                pop_price = (TextView) contentView.findViewById(R.id.pop_price);
                num_price = (TextView) contentView.findViewById(R.id.pop_num_price);
                pop_num1 = (TextView) contentView.findViewById(R.id.pop_et_num);

                //设置popWindow的值
                pop_num1.setText(pop_num2+"");
                bt_jian.setEnabled(false);
                pop_price.setText(price2);
                ImageLoader.getInstance().displayImage(img2,pop_img, MyApp.options());
                pp = Double.parseDouble(price2);
                num_price.setText("¥"+pp);

                //确定添加购物车按钮
                pop_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,String> map = new HashMap<String, String>();
                        String key = Urlpath_Utils.getShard(Goods_detailed_Activity.this).getString("key", "");
                        map.put("goods_id",id);
                        map.put("quantity","1");
                        map.put("key",key);

                        OkHttp3Utils.doPost(Urlpath_Utils.ADD_SHOPPING,map, new GsonObjectCallback<ExitBean>() {
                            @Override
                            public void onUi(ExitBean exitBean) {
                               if(exitBean.getCode() == 200){
                                   Toast.makeText(Goods_detailed_Activity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
                               }

                            }

                            @Override
                            public void onFailed(Call call, IOException e) {
                                Toast.makeText(Goods_detailed_Activity.this, "添加购物车失败", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                });

                //增加商品数量
                bt_jia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop_num2++;
                        pop_num1.setText(pop_num2+"");
                        num_price.setText("¥"+pop_num2*pp);
                        bt_jian.setEnabled(true);

                    }
                });

                //减少商品数量
                bt_jian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop_num2--;
                        pop_num1.setText(pop_num2+"");
                        num_price.setText("¥"+pop_num2*pp);
                        if(pop_num2 <2){
                            bt_jian.setEnabled(false);
                        }
                    }
                });
                break;
        }
    }

    //当前页面方法
    private void Data(){

        //加载商品介绍详情界面
        webView.loadUrl(Urlpath_Utils.XIANGXI2);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        //返回上层页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        OkHttp3Utils.doGet(Urlpath_Utils.XIANGXI +"&goods_id="+ id, new GsonObjectCallback<Goods_Detailed_Bean>() {
            @Override
            public void onUi(Goods_Detailed_Bean goods_detailed_bean) {
                Goods_Detailed_Bean.DatasBean datas = goods_detailed_bean.getDatas();
                if(goods_detailed_bean.getCode() == 200){
                    img2 = datas.getGoods_image();

                    ImageLoader.getInstance().displayImage(img2,img, MyApp.options());
                    name2 = datas.getGoods_info().getGoods_name();
                    name.setText(name2);
                    price2 = datas.getGoods_info().getGoods_price();
                    price.setText("¥"+ price2);
                    location.setText(datas.getGoods_hair_info().getArea_name());
                    youfei.setText("快递"+datas.getGoods_hair_info().getContent());
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    //显示POPWindow
    private void showPopWindow(){
        //加载弹出框的布局
        contentView = LayoutInflater.from(Goods_detailed_Activity.this).inflate(R.layout.shopping_pop,null);

        //设置弹出框的宽度和高度
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

    }


}
