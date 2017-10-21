package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.persenter.LoginPersenterImpl;
import liangtong20170928.bawei.com.dshang.view.LoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginView{

    private ImageView iv_return2;
    private TextView zhuce;
    private EditText et_name_login;
    private EditText et_pwd_login;
    private LoginPersenterImpl persenter;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iv_return2 = (ImageView) findViewById(R.id.iv_return2);
        zhuce = (TextView) findViewById(R.id.tv_zhuce);
        et_name_login = (EditText) findViewById(R.id.et_name_login);
        et_pwd_login = (EditText) findViewById(R.id.et_pwd_login);
        bt_login = (Button) findViewById(R.id.bt_login);

        iv_return2.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        et_name_login.setOnClickListener(this);
        et_pwd_login.setOnClickListener(this);
        bt_login.setOnClickListener(this);

        persenter = new LoginPersenterImpl(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_return2:
                    finish();
                break;
            case R.id.tv_zhuce:
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.bt_login:
                persenter.LoginPersente(this,et_name_login.getText().toString(),et_pwd_login.getText().toString());
                break;
        }
    }

    @Override
    public void setNameError() {
        et_name_login.setError("name cannot be empty");
    }

    @Override
    public void setPwdError() {
        et_pwd_login.setError("pwd cannot be empty");
    }

    @Override
    public void toHomeActivity() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //intent.putExtra("username",);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
