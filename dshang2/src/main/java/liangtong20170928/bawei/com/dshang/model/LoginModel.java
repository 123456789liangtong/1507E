package liangtong20170928.bawei.com.dshang.model;

import android.content.Context;

import liangtong20170928.bawei.com.dshang.persenter.LoginFinishListenter;

/**
 * Created by la on 2017/10/13.
 */

public interface LoginModel {
    void Login(Context context, String name, String pwd, LoginFinishListenter listenter);
}
