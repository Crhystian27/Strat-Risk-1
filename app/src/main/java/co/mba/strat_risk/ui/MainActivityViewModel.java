package co.mba.strat_risk.ui;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.repository.Repository;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "Main_AV";

    private LiveData<List<News>> newsLiveData;
    private MutableLiveData<List<NewsDTO>> newsDTOLiveData;
    private Repository repository;

    @Inject
    public MainActivityViewModel(Repository repository) {
        this.repository = repository;
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
