package liangtong20170928.bawei.com.dshang.persenter;

import android.content.Context;

import liangtong20170928.bawei.com.dshang.model.Add_addressModelImpl;
import liangtong20170928.bawei.com.dshang.view.Add_AddressView;

/**
 * Created by la on 2017/10/12.
 */

public class Add_addressPersenterImpl implements Add_addressPersenter,Add_addressFinishListenter{

    Add_AddressView registerView;

    private Add_addressModelImpl registerModel;


    public Add_addressPersenterImpl(Add_AddressView registerView) {
        this.registerView = registerView;
        registerModel = new Add_addressModelImpl();
    }

    @Override
    public void addressPersente(Context context, String name, String pwd, String pwd2, String pwd22, String email22, String email) {
        registerModel.Register(context,name,pwd,pwd2,pwd22,email22,email,this);
    }

    @Override
    public void onNameError() {
        if(registerView != null){
            registerView.setNameError();
        }
    }

    @Override
    public void onPwdError() {
        if(registerView != null){
            registerView.setPwdError();
        }
    }

    @Override
    public void onPwdError2() {
        if(registerView != null){
            registerView.setPwdError2();
        }
    }

    @Override
    public void onEmailError() {
        if(registerView != null){
            registerView.setEmailError();
        }
    }

    @Override
    public void onPwdError22() {
        if(registerView != null){
            registerView.setPwdError22();
        }
    }

    @Override
    public void onEmailError22() {
        if(registerView != null){
            registerView.setEmailError22();
        }
    }

    @Override
    public void onSuccess() {
        if(registerView != null){
            registerView.toOKActivity();
        }
    }


}
