package liangtong20170928.bawei.com.dshang.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.bean.LoginPonseBean;
import liangtong20170928.bawei.com.dshang.persenter.LoginFinishListenter;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by la on 2017/10/13.
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public void Login(final Context context, final String name, final String pwd, final LoginFinishListenter listenter) {
        if(TextUtils.isEmpty(name)){
            listenter.onNameError();
            Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            listenter.onPwdError();
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("username",name);
        map.put("password",pwd);
        map.put("client",Urlpath_Utils.CLIENT);

        OkHttp3Utils.doPost(Urlpath_Utils.LOGIN, map, new GsonObjectCallback<LoginPonseBean>() {
            @Override
            public void onUi(LoginPonseBean loginPonseBean) {
                if (loginPonseBean.getCode() == 200){
                    SharedPreferences shard = Urlpath_Utils.getShard(context);
                    SharedPreferences.Editor edit = shard.edit();
                    edit.putBoolean("flag",true);
                    edit.putString("name",name);
                    edit.putString("key",loginPonseBean.getDatas().getKey());

                    edit.commit();
                    listenter.onSuccess();
                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {
                Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
