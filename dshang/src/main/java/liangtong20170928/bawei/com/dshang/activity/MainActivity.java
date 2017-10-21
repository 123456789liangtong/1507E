package liangtong20170928.bawei.com.dshang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.fragment.Fragment1;
import liangtong20170928.bawei.com.dshang.fragment.Fragment2;
import liangtong20170928.bawei.com.dshang.fragment.Fragment3;
import liangtong20170928.bawei.com.dshang.fragment.Fragment4;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.frame,new Fragment1()).commit();//默认加载第一个页面
        findViewById(R.id.tv_main).setOnClickListener(this);
        findViewById(R.id.tv_dynamic).setOnClickListener(this);
        findViewById(R.id.tv_message).setOnClickListener(this);
        findViewById(R.id.tv_person).setOnClickListener(this);
    }

    //底部fragment+ViewPager切换
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_main:
                manager.beginTransaction().replace(R.id.frame,new Fragment1()).commit();
                break;
            case R.id.tv_dynamic:
                manager.beginTransaction().replace(R.id.frame,new Fragment2()).commit();
                break;
            case R.id.tv_message:
                manager.beginTransaction().replace(R.id.frame,new Fragment3()).commit();
                break;
            case R.id.tv_person:
                SharedPreferences shard = Urlpath_Utils.getShard(MainActivity.this);
                boolean flag = shard.getBoolean("flag", false);
                if(flag == false){
                    startActivity(new Intent(MainActivity.this,Reg_Login_Activity.class));
                }
                manager.beginTransaction().replace(R.id.frame,new Fragment4()).commit();
                break;
        }
    }
}
