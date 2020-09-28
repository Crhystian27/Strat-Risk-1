package co.mba.strat_risk.ui;


import android.app.Activity;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;
import co.mba.strat_risk.data.repository.Repository;

public class MainViewModel extends ViewModel {

    private static final String TAG = "Main_AV";

    private Repository repository;

    @Inject
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }


    public void setLogInOut(Activity activity, Class aClass) {
        repository.logOut(activity, aClass);
    }
}
