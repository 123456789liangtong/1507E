package liangtong20170928.bawei.com.dshang.model;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.persenter.ZhuceFinishListenter;
import liangtong20170928.bawei.com.dshang.bean.ResPonseBean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by la on 2017/10/12.
 */

public class RegisterModelImpl implements RegisterModel{

    @Override
    public void Register(final Context context, String name, String pwd, String email, String pwd2, final ZhuceFinishListenter listener) {
        if(TextUtils.isEmpty(name)){
            listener.onNameError();
            Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(pwd)){
            listener.onPwdError();
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(pwd)){
            listener.onPwdError2();
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(email)){
            listener.onEmailError();
            Toast.makeText(context, "邮箱不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Map<String,String> map = new HashMap<>();
            map.put("username",name);
            map.put("password",pwd);
            map.put("password_confirm",pwd2);
            map.put("email",email);
            map.put("client", Urlpath_Utils.CLIENT);

            OkHttp3Utils.doPost(Urlpath_Utils.ZHUCE, map, new GsonObjectCallback<ResPonseBean>() {
                @Override
                public void onUi(ResPonseBean resPonseBean) {
                    if(resPonseBean.getCode() == 200){
                        listener.onSuccess();
                        Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {
                    Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
