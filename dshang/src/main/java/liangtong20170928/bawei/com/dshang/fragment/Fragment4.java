package liangtong20170928.bawei.com.dshang.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.activity.LoginActivity;
import liangtong20170928.bawei.com.dshang.activity.Reg_Login_Activity;
import liangtong20170928.bawei.com.dshang.bean.ExitBean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by la on 2017/9/29.
 */

public class Fragment4 extends Fragment{
    private ImageView iv_login;
    private Button tuichu1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment4, container, false);
        //获取组件id
        iv_login = (ImageView) view.findViewById(R.id.iv_login);
        tuichu1 = (Button) view.findViewById(R.id.bt_tui);

        iv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        tuichu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences shard = Urlpath_Utils.getShard(getActivity());
                String name = shard.getString("name", "");
                String key = shard.getString("key", "");

                Map<String,String> map = new HashMap<String, String>();
                map.put("key",key);
                map.put("client",Urlpath_Utils.CLIENT);
                map.put("username",name);

                OkHttp3Utils.doPost(Urlpath_Utils.ZHUXIAO, map, new GsonObjectCallback<ExitBean>() {
                    @Override
                    public void onUi(ExitBean loginPonseBean) {
                        if(loginPonseBean.getCode() == 200&loginPonseBean.getDatas() == 1){
                            Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(),Reg_Login_Activity.class));

                        }
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {
                        Toast.makeText(getActivity(), "退出失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        return view;
    }
}
