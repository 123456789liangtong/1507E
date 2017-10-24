package com.example.newsinfo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.city_picker.CityListActivity;
import com.example.newsinfo.activity.LoginActivity;
import com.example.newsinfo.activity.SettingActivity;
import com.example.newsinfo.adapter.MyFragmentAdapter;
import com.example.newsinfo.bean.QQBean;
import com.example.newsinfo.fragment.Fragment1;
import com.example.newsinfo.fragment.Fragment2;
import com.example.newsinfo.fragment.Fragment3;
import com.example.newsinfo.fragment.Fragment4;
import com.example.newsinfo.fragment.Fragment5;
import com.example.newsinfo.fragment.Fragment6;
import com.example.newsinfo.fragment.Fragment7;
import com.example.newsinfo.fragment.Fragment8;
import com.example.newsinfo.fragment.Fragment9;
import com.example.newsinfo.utils.Hint;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.smssdk.gui.RegisterPage;

public class MainActivity extends Hint {

    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private DrawerLayout drawerLayout;
    private ImageView iv;
    private TabLayout tablayout;
    private LinearLayout ll;
    private ImageView iv_qq;
    private ImageView iv_phone;
    private TextView more;
    private RadioGroup light;
    private boolean isDay = true;//默认是日间模式
    private ViewPager vp;
    private TextView iv_pop;
    private TextView moshi_zi;
    private List<ChannelBean> list;
    private String jsonStr;
    private ImageView iv_sousuo;
    private RadioGroup setting;
    private ImageView moshi_tu;
    private ImageView qq_head;
    private LinearLayout ll_more;
    private TextView qq_name;
    private TextView qq_city;
    private String[] title = new String[]{"社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    private int theme = R.style.AppTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
       theme = savedInstanceState.getInt("theme");
      setTheme(theme);
  }
        setContentView(R.layout.activity_main);

        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,MainActivity.this.getApplicationContext());
        //网络判断

        initView();
        if(isOnline()){
            initData();
        }else{
            showDialog();
        }

    }

    /**
     * 提示用户的一个联网对话框
     */
    private void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否打开网络!");
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("开网", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //设置跳转网络界面
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        });

        builder.create().show();

    }
    //设置开网
    private void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

    }

    //判断网络是否连接
    private boolean isOnline(){

        //得到网络管理者
        ConnectivityManager manager  = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //通过网络管理者获取网络信息
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info!=null&&info.isConnected());
    }

    //获取组件id
    private void initView(){
        drawerLayout  = (DrawerLayout) findViewById(R.id.drawerlayout);
        iv = (ImageView) findViewById(R.id.iv);//左上方点击左侧拉
        tablayout = (TabLayout) findViewById(R.id.tablayout);//导航栏
        ll = (LinearLayout) findViewById(R.id.ll);//左侧布局
        iv_qq = (ImageView) findViewById(R.id.iv_qq);//qq
        iv_phone = (ImageView) findViewById(R.id.iv_phone);//短信登录
        more = (TextView) findViewById(R.id.more);//更多登录方式
        light = (RadioGroup) findViewById(R.id.light);//日夜间模式
        vp = (ViewPager) findViewById(R.id.vp);//viewPager
        iv_pop = (TextView) findViewById(R.id.iv_pop);//频道
        iv_sousuo = (ImageView) findViewById(R.id.iv_sousuo);//搜索
        setting = (RadioGroup) findViewById(R.id.setting);//设置
        moshi_tu = (ImageView) findViewById(R.id.moshi_tu);
        moshi_zi = (TextView) findViewById(R.id.moshi_zi);
        ll_more = (LinearLayout) findViewById(R.id.ll_more);//更多登录方式
        qq_head = (ImageView) findViewById(R.id.qq_head);//qq头像
        qq_name = (TextView) findViewById(R.id.qq_name);//获取的qq信息中的昵称
        qq_city = (TextView) findViewById(R.id.qq_city);//获取的qq信息中的城市

    }

    private void initData(){

        //添加tablayout内容
        for (String str : title){
            tablayout.addTab(tablayout.newTab().setText(str));
        }

        //初始化ViewPager
        List<Fragment> fragments=new ArrayList<>();
        //创建Fragment装入集合
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        fragments.add(new Fragment6());
        fragments.add(new Fragment7());
        fragments.add(new Fragment8());
        fragments.add(new Fragment9());

        //创建适配器
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),title,fragments);
        //把集合放入适配器
        fragmentAdapter.setFragments(fragments);
        //给ViewPager设置适配器
        vp.setAdapter(fragmentAdapter);
        vp.setOffscreenPageLimit(9);
        for (int i = 0; i <fragments.size();i++){
            tablayout.addTab(tablayout.newTab());
        }
        //使tablayout和viewPager关联
        tablayout.setupWithViewPager(vp);


        //点击左上角图片是实现左侧拉
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerOpen(ll)){
                    drawerLayout.closeDrawer(ll);
                }else{
                    drawerLayout.openDrawer(ll);
                }
            }
        });

        //实现手机短信验证登录
        iv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RegisterPage
                RegisterPage registerPage = new RegisterPage();
                registerPage.show(MainActivity.this);

            }
        });

        //实现更多登录方式
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //点击设置跳转页面
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        //实现频道管理
        iv_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list==null){//判断集合中是否已有数据，没有则创建
                    list=new ArrayList<>();
                    //第一个是显示的条目，第二个参数是否显示
                    list.add(new ChannelBean("热点",true));
                    list.add(new ChannelBean("军事",true));
                    list.add(new ChannelBean("八卦",true));
                    list.add(new ChannelBean("游戏",true));
                    list.add(new ChannelBean("宠物",true));
                    list.add(new ChannelBean("汽车",false));
                    list.add(new ChannelBean("热卖",false));
                    list.add(new ChannelBean("外卖",false));
                    list.add(new ChannelBean("健康",false));
                    list.add(new ChannelBean("时尚",false));
                    list.add(new ChannelBean("段子",false));
                    list.add(new ChannelBean("文化",false));
                    list.add(new ChannelBean("时尚",false));
                    list.add(new ChannelBean("历史",false));
                    list.add(new ChannelBean("育儿",false));
                    list.add(new ChannelBean("搞笑",false));
                    list.add(new ChannelBean("数码",false));
                    list.add(new ChannelBean("美食",false));
                    list.add(new ChannelBean("养生",false));
                    list.add(new ChannelBean("电影",false));
                    list.add(new ChannelBean("手机",false));
                    list.add(new ChannelBean("旅游",false));
                    list.add(new ChannelBean("宠物",false));
                    list.add(new ChannelBean("情感",false));
                    list.add(new ChannelBean("家居",false));
                    list.add(new ChannelBean("教育",false));
                    list.add(new ChannelBean("三农",false));
                    list.add(new ChannelBean("收藏",false));
                    list.add(new ChannelBean("股票",false));
                    list.add(new ChannelBean("科学",false));
                    list.add(new ChannelBean("动漫",false));
                    list.add(new ChannelBean("故事",false));
                    list.add(new ChannelBean("精选",false));
                    list.add(new ChannelBean("星座",false));

                    ChannelActivity.startChannelActivity(MainActivity.this,list);

                }else if (jsonStr!=null){//当判断保存的字符串不为空的时候，直接加载已经有了的字符串
                    ChannelActivity.startChannelActivity(MainActivity.this,jsonStr);
                }
            }
        });

        //点击搜索实现城市列表
        iv_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityListActivity.startCityActivityForResult(MainActivity.this);
            }
        });

        //点击切换日夜间模式
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
             MainActivity.this.recreate();

            }
        });

        //点击实现第三方登录
        iv_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(MainActivity.this,"all", mIUiListener);

            }
        });

    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();

            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());

                        findViewById(R.id.ll_more).setVisibility(View.GONE);//隐藏布局
                        findViewById(R.id.ll_more2).setVisibility(View.VISIBLE);//显示布局
                        Gson gson = new Gson();
                        QQBean qqBean = gson.fromJson(response.toString(), QQBean.class);
                        DisplayImageOptions options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(360)).build();

                        //将主页面左上角替换成登录自后的头像
                        ImageLoader.getInstance().displayImage(qqBean.getFigureurl(),iv,options);
                        //将左侧拉布局里的更多登录方式中替换为自己的登录后的模式
                        ImageLoader.getInstance().displayImage(qqBean.getFigureurl(),qq_head,options);
                        qq_city.setText("城市:"+qqBean.getCity());
                        qq_name.setText("昵称:"+qqBean.getNickname());

                        findViewById(R.id.ll_more2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog builder = new AlertDialog.Builder(MainActivity.this).setMessage("退出登录")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                findViewById(R.id.ll_more).setVisibility(View.VISIBLE);
                                                findViewById(R.id.ll_more2).setVisibility(View.GONE);
                                            }
                                        }).setNegativeButton("返回", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Log.i("alertdialog"," 请保存数据！");
                                            }
                                        }).show();

                            }
                        });
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }

    }

    @Override
   protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
   outState.putInt("theme", theme);
 }

         @Override
   protected void onRestoreInstanceState(Bundle savedInstanceState) {
   super.onRestoreInstanceState(savedInstanceState);
    theme = savedInstanceState.getInt("theme");
  }
}
