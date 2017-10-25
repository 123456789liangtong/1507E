package liangtong20170928.bawei.com.dshang.persenter;

import android.content.Context;

import liangtong20170928.bawei.com.dshang.model.LoginModelImpl;
import liangtong20170928.bawei.com.dshang.view.LoginView;

/**
 * Created by la on 2017/10/13.
 */

public class LoginPersenterImpl implements LoginPersenter,LoginFinishListenter{

    LoginView loginView;
    LoginModelImpl loginMode;

    public LoginPersenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginMode = new LoginModelImpl();
    }

    @Override
    public void onNameError() {
        if(loginView != null){
            loginView.setNameError();
        }
    }

    @Override
    public void onPwdError() {
        if(loginView != null){
            loginView.setPwdError();
        }
    }

    @Override
    public void onSuccess() {
        if(loginView != null){
            loginView.toHomeActivity();
        }
    }

    @Override
    public void LoginPersente(Context context, String name, String pwd) {
        loginMode.Login(context,name,pwd,this);
    }
}
