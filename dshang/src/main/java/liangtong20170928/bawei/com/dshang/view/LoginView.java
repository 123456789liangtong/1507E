package liangtong20170928.bawei.com.dshang.view;

/**
 * Created by la on 2017/10/13.
 */

public interface LoginView {

    //用户名输入有误
    void setNameError();

    //密码输入错误
    void setPwdError();

    //登录成功
    void toHomeActivity();

}
