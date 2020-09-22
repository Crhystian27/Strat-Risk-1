package co.mba.strat_risk.ui;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class MenuViewModel extends ViewModel {

    private static final String TAG = "Menu_VA";

    @Inject
    public MenuViewModel() {
    }

    public void initMenu(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        activity.startActivity(intent);
        Log.e(TAG, aClass.getSimpleName());
        Log.e(TAG, activity.getClass().getSimpleName() + " ->" + aClass.getSimpleName());
        activity.finish();
    }

}
