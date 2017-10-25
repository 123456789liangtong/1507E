package liangtong20170928.bawei.com.dshang.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by la on 2017/10/12.
 */

public class MyApp extends Application {
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

                 ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                         .build();

                 ImageLoader.getInstance().init(config);


             }
    public static MyApp getInstance() {
        return mInstance;
    }

    public static DisplayImageOptions options (){

        DisplayImageOptions options = new DisplayImageOptions.Builder().build();

        return options;
    }


    }



