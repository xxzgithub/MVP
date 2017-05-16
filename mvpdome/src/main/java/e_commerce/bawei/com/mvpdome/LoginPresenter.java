package e_commerce.bawei.com.mvpdome;

/**
 * autohor:谢兴张(asus)
 * date:2017/5/16
 * effect:
 */

public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestrou();
}
