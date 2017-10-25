package liangtong20170928.bawei.com.dshang.model;

import android.content.Context;

import liangtong20170928.bawei.com.dshang.persenter.Add_addressFinishListenter;

/**
 * Created by la on 2017/10/12.
 */

public interface Add_addressModel {
    void Register(Context context, String name, String pwd, String email, String pwd2, String email22, String pwd22, Add_addressFinishListenter listenter);

}
