package co.mba.strat_risk.ui;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import javax.inject.Inject;

import co.mba.strat_risk.data.entity.User;
import co.mba.strat_risk.data.repository.Repository;
import co.mba.strat_risk.util.AppPreferences;

public class MainViewModel extends ViewModel {

    private static final String TAG = "Main_AV";

    private LiveData<User> userLiveData;
    private Repository repository;


    @Inject
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getCurrentUser() {
        if (this.userLiveData != null) {
            Log.e(TAG, userLiveData.toString());
            return;
        }
        Integer id = AppPreferences.getInstance().getUser().getId();
        userLiveData = repository.getDBUser(id);
    }

    public LiveData<User> getUser() {
        return this.userLiveData;
    }


}
