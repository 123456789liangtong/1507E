package liangtong20170928.bawei.com.dshang.model;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.bean.Add_addressBean;
import liangtong20170928.bawei.com.dshang.persenter.Add_addressFinishListenter;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by la on 2017/10/12.
 */

public class Add_addressModelImpl implements Add_addressModel{

    @Override
    public void Register(final Context context, String name, String pwd, String email, String pwd2, String email22, String pwd22, final Add_addressFinishListenter listener) {

        if(TextUtils.isEmpty(name)){
            listener.onNameError();
            Toast.makeText(context, "收货人不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(pwd)){
            listener.onPwdError();
            Toast.makeText(context, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(pwd)){
            listener.onPwdError2();
            Toast.makeText(context, "城市不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(email)){
            listener.onEmailError();
            Toast.makeText(context, "省份不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(pwd)){
            listener.onPwdError22();
            Toast.makeText(context, "地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(email)){
            listener.onEmailError();
            Toast.makeText(context, "描述信息不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else{

            Map<String,String> map = new HashMap<String, String>();
            String key = Urlpath_Utils.getShard(context).getString("key", "");
            map.put("true_name",name);
            map.put("mob_phone",pwd);
            map.put("city_id",pwd2);
            map.put("area_id",email);
            map.put("address",email22);
            map.put("area_info",pwd22);
            map.put("key",key);

            OkHttp3Utils.doPost(Urlpath_Utils.ADDADDRESS, map, new GsonObjectCallback<Add_addressBean>() {
                @Override
                public void onUi(Add_addressBean add_addressBean) {
                    listener.onSuccess();
                    Toast.makeText(context, "添加新地址成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        }
    }







}
