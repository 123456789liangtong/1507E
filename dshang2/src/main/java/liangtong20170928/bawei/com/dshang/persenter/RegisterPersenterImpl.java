package liangtong20170928.bawei.com.dshang.persenter;

import android.content.Context;

import liangtong20170928.bawei.com.dshang.model.RegisterModelImpl;
import liangtong20170928.bawei.com.dshang.view.RegisterView;

/**
 * Created by la on 2017/10/12.
 */

public class RegisterPersenterImpl implements RegisterPersenter,ZhuceFinishListenter{

    RegisterView registerView;
    private final RegisterModelImpl registerModel;

    public RegisterPersenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    @Override
    public void RegisterPersente(Context context,String name, String pwd, String pwd2, String email) {
        registerModel.Register(context,name,pwd,email,pwd2,this);
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
    public void onSuccess() {
        if(registerView != null){
            registerView.toOKActivity();
        }
    }
}
