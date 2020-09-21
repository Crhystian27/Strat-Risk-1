package co.mba.strat_risk.ui;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

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
            Log.e(TAG, activity.getClass().getSimpleName() + " ->" + aClass.getSimpleName());
            activity.finish();

        }
        return true;
    }
}
