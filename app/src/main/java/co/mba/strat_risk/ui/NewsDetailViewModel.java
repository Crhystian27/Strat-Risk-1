package co.mba.strat_risk.ui;

import android.app.Activity;
import android.widget.RelativeLayout;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.repository.Repository;

public class NewsDetailViewModel extends ViewModel {

    private static final String TAG = "NewsDetail_AV";
    private Repository repository;


    @Inject
    public NewsDetailViewModel(Repository repository) {
        this.repository = repository;
    }


    public void addNewsDB(Activity activity, News news, Integer newsStatus, RelativeLayout layout, String message) {
        repository.addNews(activity, news, newsStatus, layout, message);
    }

    public void removeNews(Activity activity, News news, Integer newsStatus){
        repository.removeNews(activity, news, newsStatus);
    }
}
