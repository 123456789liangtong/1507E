package liangtong20170928.bawei.com.dshang.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by la on 2017/10/11.
 */

public class MyPagerAdapter extends PagerAdapter{

    private List<String> list;
    private Context context;
    private DisplayImageOptions options;


    public MyPagerAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        options = new DisplayImageOptions.Builder().build();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //创建一个ImageView
        ImageView iv = new ImageView(context);

        ImageLoader.getInstance().displayImage(list.get(position%list.size()),iv,options);

        //添加视图
        container.addView(iv);

        return iv;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
