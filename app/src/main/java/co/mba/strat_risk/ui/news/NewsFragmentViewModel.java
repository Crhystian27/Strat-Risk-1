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
        String[] partKey = key.split("=");
        String partKey2 = partKey[1];
        String cx = AppPreferences.getInstance().getUser().getSource();
        String[] partCx = cx.split("=");
        String partCx2 = partCx[1];
        String search = AppPreferences.getInstance().getUser().getSearch();
        String[] partSearch = search.split("&");
        String q = partSearch[1];
        String[] partQ = q.split("=");
        String valueQ = partQ[1];
        String gl = partSearch[2];
        String[] partGL = gl.split("=");
        String valueGL = partGL[1];
        String lr = partSearch[3];
        String[] partLR = lr.split("=");
        String valueLR = partLR[1];

        Log.e(TAG, valueQ + " - " + valueGL + " - " + valueLR);
        Log.e(TAG,partCx2+" - "+partKey2);

        repository.getNewsInternet(valueLR.trim(), valueGL.trim(), "1", partKey2, partCx2, valueQ);
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