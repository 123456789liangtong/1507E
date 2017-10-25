package bawei.com.zonghe;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by la on 2017/8/18.
 */

public class MyApplication extends Application{
    public static MyApplication mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();

        ImageLoader.getInstance().init(config);


    }
    public static MyApplication getInstance() {
        return mInstance;
    }

    public static DisplayImageOptions options (){

        DisplayImageOptions options = new DisplayImageOptions.Builder().build();

        return options;
    }
}
