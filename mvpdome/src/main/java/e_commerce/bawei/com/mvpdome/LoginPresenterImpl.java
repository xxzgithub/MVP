package e_commerce.bawei.com.mvpdome;

/**
 * autohor:谢兴张(asus)
 * date:2017/5/16
 * effect:
 */

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {
    private LoginView loginView;
    private LoginMode1 loginMode1;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginMode1 = new LoginModeImp1();
    }


    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showprogress();
        }
        loginMode1.login(username, password, this);
    }

    @Override
    public void onDestrou() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
