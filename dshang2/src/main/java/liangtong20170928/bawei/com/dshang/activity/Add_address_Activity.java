package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.persenter.Add_addressPersenterImpl;
import liangtong20170928.bawei.com.dshang.view.Add_AddressView;

public class Add_address_Activity extends AppCompatActivity implements Add_AddressView{

    private Add_addressPersenterImpl add_addressPersenter;
    private TextView et_save;
    private EditText et_true_name;
    private EditText et_area_id;
    private EditText et_mob_phone;
    private EditText et_city_id;
    private EditText et_address;
    private EditText et_area_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address_);

        add_addressPersenter = new Add_addressPersenterImpl(this);

        et_true_name = (EditText) findViewById(R.id.et_true_name);
        et_area_id = (EditText) findViewById(R.id.et_area_id);
        et_mob_phone = (EditText) findViewById(R.id.et_mob_phone);
        et_city_id = (EditText) findViewById(R.id.et_city_id);
        et_address = (EditText) findViewById(R.id.et_address);
        et_area_info = (EditText) findViewById(R.id.area_info);

        et_save = (TextView) findViewById(R.id.et_save);

        et_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //context, String name, String pwd, String pwd2, String pwd22, String email22, String email
                add_addressPersenter.addressPersente(Add_address_Activity.this,et_true_name.getText().toString(),et_area_id.getText().toString(),
                        et_mob_phone.getText().toString(),et_city_id.getText().toString(),et_address.getText().toString(),et_area_info.getText().toString());
            }
        });

    }

    @Override
    public void setNameError() {
        et_true_name.setError("name cannot be empty");
    }

    @Override
    public void setPwdError() {
        et_mob_phone.setError("phone cannot be empty");
    }

    @Override
    public void setPwdError2() {
        et_city_id.setError("city cannot be empty");
    }

    @Override
    public void setEmailError() {
        et_area_id.setError("area_id cannot be empty");
    }

    @Override
    public void setPwdError22() {
        et_address.setError("address cannot be empty");
    }

    @Override
    public void setEmailError22() {
        et_area_info.setError("area_info cannot be empty");
    }

    @Override
    public void toOKActivity() {
        Toast.makeText(this, "添加新地址成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Add_address_Activity.this,AddressManager_Activity.class));
    }


}
