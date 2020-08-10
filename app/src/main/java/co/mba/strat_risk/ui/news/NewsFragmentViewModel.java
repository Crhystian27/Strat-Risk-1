package co.mba.strat_risk.ui.news;


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

public class NewsFragmentViewModel extends ViewModel {

    private MutableLiveData<NewsDTO> newsDTOLiveData;
    private LiveData<List<News>> newsLiveData;

    private static final String TAG = "News_FV";
    private Repository repository;

    @Inject
    public NewsFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<NewsDTO> fetchNewsInternet(Context context) {
        if (this.newsDTOLiveData != null) {
            Log.e(TAG, newsDTOLiveData.toString());
            return null;
        } else {
            newsDTOLiveData = new MutableLiveData<>();
            newsDTOLiveData = repository.getNewsInternet(context, newsDTOLiveData);
        }
        return newsDTOLiveData;
    }

    public void fetchNewsDB(Integer status) {
        if (this.newsLiveData != null) {
            Log.e(TAG, newsLiveData.toString());
            return;
        }
        newsLiveData = repository.getNewsDB(status);
    }

    public LiveData<List<News>> getNewsDB() {
        return this.newsLiveData;
    }
}