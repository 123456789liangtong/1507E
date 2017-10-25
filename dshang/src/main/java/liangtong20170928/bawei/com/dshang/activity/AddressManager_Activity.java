package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.adapter.AddressmanagerAdapter;
import liangtong20170928.bawei.com.dshang.bean.AddressBean;
import liangtong20170928.bawei.com.dshang.utils.GsonObjectCallback;
import liangtong20170928.bawei.com.dshang.utils.OkHttp3Utils;
import okhttp3.Call;

public class AddressManager_Activity extends AppCompatActivity {

    private ListView address_lv;
    private Button bt_add_address;
    private ImageView address_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager_);

        //获取组件id
        address_lv = (ListView) findViewById(R.id.address_lv);
        bt_add_address = (Button) findViewById(R.id.bt_add_address);
        address_back = (ImageView) findViewById(R.id.address_back);

        address_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bt_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddressManager_Activity.this,Add_address_Activity.class));
            }
        });

        Map<String,String> map = new HashMap<String, String>();
        String key = Urlpath_Utils.getShard(AddressManager_Activity.this).getString("key", "");
        map.put("key",key);

        OkHttp3Utils.doPost(Urlpath_Utils.ADDRESS, map, new GsonObjectCallback<AddressBean>() {
            @Override
            public void onUi(AddressBean addressBean) {
                List<AddressBean.DatasBean.AddressListBean> list = addressBean.getDatas().getAddress_list();
                AddressmanagerAdapter adapter = new AddressmanagerAdapter(list,AddressManager_Activity.this);
                address_lv.setAdapter(adapter);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
