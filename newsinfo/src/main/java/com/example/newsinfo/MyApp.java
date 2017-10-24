package com.example.newsinfo;

import com.mob.MobApplication;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by la on 2017/9/5.
 */

public class MyApp extends MobApplication{

    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        x.Ext.init(this);

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();

        ImageLoader.getInstance().init(configuration);

    }
}
