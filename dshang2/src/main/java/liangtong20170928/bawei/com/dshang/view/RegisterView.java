package liangtong20170928.bawei.com.dshang.view;

/**
 * Created by la on 2017/10/12.
 */

public interface RegisterView {
    //用户名输入不准确
    void setNameError();

    //密码输入错误
    void setPwdError();

    //密码重新输入错误
    void setPwdError2();

    //邮箱输入错误
    void setEmailError();

    //注册成功
    void toOKActivity();
}
