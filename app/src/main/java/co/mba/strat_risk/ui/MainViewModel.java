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


    //Internet
    /*public LiveData<List<NewsDTO>> getNewsDTO(Context context) {
        if (this.newsDTOLiveData != null) {
            Log.e(TAG, newsDTOLiveData.toString());
            return null;
        } else {
            newsDTOLiveData = new MutableLiveData<>();
            //newsDTOLiveData = repository.getCurrentNews(context, newsDTOLiveData);
            return this.newsDTOLiveData;
        }
    }

    //Local
    public LiveData<List<News>> getNews(Integer idStatus) {
        if (this.newsLiveData != null) {
            Log.e(TAG, newsLiveData.toString());
            return null;
        } else {
            //newsLiveData = repository.getDBListNews(idStatus);
            return this.newsLiveData;
        }
    }*/
}
