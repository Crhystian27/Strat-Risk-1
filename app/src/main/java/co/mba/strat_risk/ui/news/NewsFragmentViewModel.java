package co.mba.strat_risk.ui.news;


import android.app.Activity;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import javax.inject.Inject;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.repository.Repository;

public class NewsFragmentViewModel extends ViewModel {

    private LiveData<List<News>> newsLiveData;

    private static final String TAG = "News_FV";
    private Repository repository;

    @Inject
    public NewsFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void fetchNewsInternet() {
        repository.getNewsInternet();
    }


    //TODO load news in menu activity
    public void fetchNewsDB(Integer status) {
        if (this.newsLiveData != null) {
            Log.e(TAG, newsLiveData.toString());
            return;
        }
        newsLiveData = repository.getNewsDB(status);
    }

    public void addNewsDB(Activity activity, News news, Integer newsStatus, RelativeLayout layout, String message) {
        repository.addNews(activity, news, newsStatus, layout, message);
    }

    public LiveData<List<News>> getNewsDB() {
        return this.newsLiveData;
    }
}