package liangtong20170928.bawei.com.dshang.model;

import android.content.Context;

import liangtong20170928.bawei.com.dshang.persenter.ZhuceFinishListenter;

/**
 * Created by la on 2017/10/12.
 */

public interface RegisterModel {
    void Register(Context context, String name, String pwd, String email,String pwd2,ZhuceFinishListenter listener);

}
