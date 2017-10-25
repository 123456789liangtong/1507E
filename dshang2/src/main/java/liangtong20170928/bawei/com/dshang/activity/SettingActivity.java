package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.bean.ExitBean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

public class SettingActivity extends AppCompatActivity {

    private Button bt_tui;
    private LinearLayout linear_address;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        bt_tui = (Button) findViewById(R.id.bt_tui);
        linear_address = (LinearLayout) findViewById(R.id.linear_address);
        back = (ImageView) findViewById(R.id.setting_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        linear_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this,AddressManager_Activity.class));
            }
        });

        bt_tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences shard = Urlpath_Utils.getShard(SettingActivity.this);
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
                            Toast.makeText(SettingActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SettingActivity.this,Reg_Login_Activity.class));
                        }
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {
                        Toast.makeText(SettingActivity.this, "退出失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
