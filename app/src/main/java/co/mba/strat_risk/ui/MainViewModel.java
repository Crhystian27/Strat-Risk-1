package co.mba.strat_risk.ui;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.entity.User;
import co.mba.strat_risk.data.repository.Repository;
import co.mba.strat_risk.util.AppPreferences;

public class MainViewModel extends ViewModel {

    private static final String TAG = "Main_AV";

    private LiveData<User> userLiveData;
    private Repository repository;
    private LiveData<List<News>> newsLiveData;

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

    public void initNews(Integer id) {
        if (this.newsLiveData != null) {
            Log.e(TAG, newsLiveData.toString());
            return;

        }
        newsLiveData = repository.getNewsDB(id);
    }

    public LiveData<List<News>> getLocalNews() {
        return this.newsLiveData;
    }


}
