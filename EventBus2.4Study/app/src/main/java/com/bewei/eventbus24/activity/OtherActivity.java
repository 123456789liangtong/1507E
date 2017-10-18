package com.bewei.eventbus24.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bewei.eventbus24.R;
import com.bewei.eventbus24.bean.MessageEvent;
import com.ypy.eventbus.EventBus;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/10/10 15:59
 */


public class OtherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Button bt_send = (Button) findViewById(R.id.bt_send);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发布消息

                EventBus.getDefault().post(new MessageEvent("这是我使用EventBus来发送消息"));
                finish();

            }
        });
    }
}
