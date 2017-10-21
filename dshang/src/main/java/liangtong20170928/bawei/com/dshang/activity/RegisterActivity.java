package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.persenter.RegisterPersenterImpl;
import liangtong20170928.bawei.com.dshang.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,RegisterView{

    private ImageView iv_return_reg;
    private EditText et_name_reg;
    private EditText et_pwd_reg;
    private EditText et_pwd2_reg;
    private EditText et_email_reg;
    private Button bt_reg;
    private RegisterPersenterImpl redisterPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iv_return_reg = (ImageView) findViewById(R.id.iv_return_reg);
        et_name_reg = (EditText) findViewById(R.id.et_name_reg);
        et_pwd_reg = (EditText) findViewById(R.id.et_pwd_reg);
        et_pwd2_reg = (EditText) findViewById(R.id.et_pwd2_reg);
        et_email_reg = (EditText) findViewById(R.id.et_email_reg);
        bt_reg = (Button) findViewById(R.id.bt_reg);

        bt_reg.setOnClickListener(this);
        iv_return_reg.setOnClickListener(this);

        redisterPersenter = new RegisterPersenterImpl(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.iv_return_reg:
                finish();
                break;
            case R.id.bt_reg:
                redisterPersenter.RegisterPersente(this,et_name_reg.getText().toString(),et_pwd_reg.getText().toString(),et_pwd2_reg.getText().toString(),et_email_reg.getText().toString());
                break;
        }

    }

    @Override
    public void setNameError() {
        et_name_reg.setError("name cannot be empty");
    }

    @Override
    public void setPwdError() {
        et_pwd_reg.setError("pwd cannot be empty");
    }

    @Override
    public void setPwdError2() {
        et_pwd2_reg.setError("pwd2 cannot be empty");
    }

    @Override
    public void setEmailError() {
        et_email_reg.setError("email cannot be empty");
    }

    @Override
    public void toOKActivity() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}
