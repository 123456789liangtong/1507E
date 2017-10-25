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
import android.widget.TextView;

import liangtong20170928.bawei.com.dshang.R;
import liangtong20170928.bawei.com.dshang.Urlpath_Utils;
import liangtong20170928.bawei.com.dshang.activity.LoginActivity;
import liangtong20170928.bawei.com.dshang.activity.SettingActivity;

/**
 * Created by la on 2017/9/29.
 */

public class Fragment4 extends Fragment{
    private ImageView iv_login;
    private Button tuichu1;
    private TextView tv_set;
    private TextView username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment4, container, false);


        //获取组件id
        iv_login = (ImageView) view.findViewById(R.id.iv_login);
        tuichu1 = (Button) view.findViewById(R.id.bt_tui);
        tv_set = view.findViewById(R.id.tv_set);
        username = view.findViewById(R.id.username);

        SharedPreferences shard = Urlpath_Utils.getShard(getActivity());
        String name = shard.getString("username", "");
        username.setText(name);


        tv_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        iv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
