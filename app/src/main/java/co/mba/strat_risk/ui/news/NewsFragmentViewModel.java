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
import co.mba.strat_risk.util.AppPreferences;

public class NewsFragmentViewModel extends ViewModel {

    private LiveData<List<News>> newsLiveData;

    private static final String TAG = "News_FV";
    private Repository repository;

    @Inject
    public NewsFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void fetchNewsInternet() {
        String key = AppPreferences.getInstance().getUser().getKey();
        String cx = AppPreferences.getInstance().getUser().getSource();
        String search = AppPreferences.getInstance().getUser().getSearch();
        //TODO Verificar con bd
        repository.getNewsInternet("lang_es", "CO", "1", key, cx, search);
    }

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