package liangtong20170928.bawei.com.dshang.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by la on 2017/9/29.
 */

public class MessageGroupFragmentAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> list;
    public MessageGroupFragmentAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list = list;
    }
    public MessageGroupFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
