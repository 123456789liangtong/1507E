package liangtong20170928.bawei.com.dshang;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by la on 2017/10/12.
 */

public class Urlpath_Utils {
    //统一的接口
    public static final String url="http://169.254.110.146/mobile/index.php?";

    //保存登录状态
    public static SharedPreferences preferences = null;
    public static final String Key="user";

    //0947980e0aa3a07c0aad456cba605f32
    //50df2f8588c8f7deb29013915fc1cb4d

    public static SharedPreferences getShard(Context context){
        if(preferences == null){
            preferences = context.getSharedPreferences(Key,Context.MODE_PRIVATE);
        }
        return preferences;
    }

    //client 内部填写；终端属性  固定值：android
    public static final String CLIENT="android";

    //登录
    public static final String LOGIN = url+"act=login";

    //注册
    public static final String ZHUCE = url+"act=login&op=register";

    //注销
    public static final String ZHUXIAO = url+"act=logout";

    //搜索
    public static final String SOUSUO = url+"act=goods&op=goods_list&page=100";

    //分类 GET 一级
    public static final String FENLEI_1 = url+"act=goods_class";

    //分类 GET 二级 三级
    public static final String FENLEI_23 = url+"act=goods_class&gc_id=";

    //商品明细GET
    public static final String XIANGXI = url+"act=goods&op=goods_detail";

    //WebView展示商品详细
    public static final String XIANGXI2 = url+"act=goods&op=goods_body&goods_id=100009";

    //购物车添加 POST
    public static final String ADD_SHOPPING = url+"act=member_cart&op=cart_add";

    //购物车列表展示
    public static final String SHOPPING_SHOW = url+"act=member_cart&op=cart_list";
}
