package co.mba.strat_risk.ui.news;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.data.repository.Repository;

public class NewsFragmentViewModel extends ViewModel {

    private MutableLiveData<NewsDTO> newsLiveData;
    private static final String TAG = "News_FV";
    private Repository repository;

    @Inject
    public NewsFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<NewsDTO> fetchNewsDTO(Context context) {
        if (this.newsLiveData != null) {
            Log.e(TAG, newsLiveData.toString());
            return null;
        } else {
            newsLiveData = new MutableLiveData<>();
            newsLiveData = repository.getCurrentNews(context, newsLiveData);
        }
        return newsLiveData;
    }
}