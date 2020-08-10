package co.mba.strat_risk.ui;

import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.util.AppPreferences;

public class LoginViewModel extends ViewModel {

    private static final String TAG = "Login_VA";

    @Inject
    public LoginViewModel() {
    }

    public Boolean initLogin(Activity activity, Class aClass) {
        if (AppPreferences.getInstance().getUser() != null) {
            Intent intent = new Intent(activity, aClass);
            activity.startActivity(intent);
        }
        return true;
    }
}
