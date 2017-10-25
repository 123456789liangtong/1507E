package bawei.com.zonghe;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import bawei.com.zonghe.adapter.MyFragmentAdapter;
import bawei.com.zonghe.fragment.Fragment01;
import bawei.com.zonghe.fragment.Fragment02;
import bawei.com.zonghe.fragment.Fragment03;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String[] title = new String[]{"最新日报","专栏","热门","主题日报"};
    private TabLayout tabLayout;
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setOnClickListener(this);
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOnClickListener(this);

        initData();
    }

    @Override
    public void onClick(View view) {

    }

    private void initData(){
        for (String str : title){
            tabLayout.addTab(tabLayout.newTab().setText(str));
        }
        //初始化ViewPager
        List<Fragment> fragments=new ArrayList<>();
        //创建Fragment装入集合
        fragments.add(new Fragment01());
        fragments.add(new Fragment02());
        fragments.add(new Fragment03());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),title,fragments);
        //把集合放入适配器
        adapter.setFragments(fragments);
        //给ViewPager设置适配器
        vp.setAdapter(adapter);
        for (int i = 0; i <fragments.size();i++){
            tabLayout.addTab(tabLayout.newTab());
        }
        //使tablayout和viewPager关联
        tabLayout.setupWithViewPager(vp);


    }
}
