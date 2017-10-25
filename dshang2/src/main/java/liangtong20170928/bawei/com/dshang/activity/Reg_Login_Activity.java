package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import liangtong20170928.bawei.com.dshang.R;

public class Reg_Login_Activity extends AppCompatActivity {

    private Button bt_reg2;
    private Button bt_login2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg__login_);

        bt_reg2 = (Button) findViewById(R.id.bt_reg2);
        bt_login2 = (Button) findViewById(R.id.bt_login2);

        bt_reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Reg_Login_Activity.this,RegisterActivity.class));
            }
        });

        bt_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Reg_Login_Activity.this,LoginActivity.class));
            }
        });

    }
}
