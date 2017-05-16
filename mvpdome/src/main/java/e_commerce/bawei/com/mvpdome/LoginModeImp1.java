package e_commerce.bawei.com.mvpdome;

import android.os.Handler;
import android.text.TextUtils;

/**
 * autohor:谢兴张(asus)
 * date:2017/5/16
 * effect:
 */

public class LoginModeImp1 implements LoginMode1 {
    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameError();
                    error = true;
                }
                if (TextUtils.isEmpty(password)) {
                    listener.onPasswordError();
                    error = true;
                }
                if (!error) {
                    listener.onSuccess();
                }
            }
        }, 2000);
    }
}
