package e_commerce.bawei.com.mvpdome;

/**
 * autohor:谢兴张(asus)
 * date:2017/5/16
 * effect:
 */

public interface LoginView {
    void hideProgress();
    void showprogress();
    void setUsernameError();
    void setPasswordError();
    void navigateToHome();
}
