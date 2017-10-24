package com.example.newsinfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newsinfo.R;
import com.example.newsinfo.utils.Hint;

import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends Hint {

    private Button login_denglu;
    private Button login_zhuce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_denglu = (Button) findViewById(R.id.login_denglu);
        login_zhuce = (Button) findViewById(R.id.login_zhuce);

        //点击按钮返回上一层界面
        findViewById(R.id.login_fanhui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        login_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RegisterPage
                RegisterPage registerPage = new RegisterPage();
                registerPage.show(LoginActivity.this);
            }
        });
        login_zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RegisterPage
                RegisterPage registerPage = new RegisterPage();
                registerPage.show(LoginActivity.this);
            }
        });
    }
}
