package co.mba.strat_risk.ui;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.data.repository.Repository;
import co.mba.strat_risk.util.AppPreferences;

public class SliderViewModel extends ViewModel {


    Repository repository;

    private static final String TAG = "Slider_VA";

    @Inject
    public SliderViewModel(Repository repository) {
        this.repository = repository;
    }

    public void initSlider(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        activity.startActivity(intent);
        Log.e(TAG, aClass.getSimpleName());
        Log.e(TAG, activity.getClass().getSimpleName() + " ->" + aClass.getSimpleName());
        activity.finish();
    }

    public boolean isExistData() {
        if (repository.existUserDB()) {

            Log.e(TAG, "Exist User");
            return true;
        } else {
            Log.e(TAG, "No Exist User");
            return false;
        }
    }

}
