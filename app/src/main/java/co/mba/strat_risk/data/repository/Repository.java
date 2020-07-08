package co.mba.strat_risk.data.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.network.ApiService;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.network.RequestInterceptor;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class Repository {

    private static final String TAG = "repo";


    private ApiService apiService;
    private Executor executor;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private NewsDao newsDao;
    //Internet Connection
    private InternetConnection connection;
    private RequestInterceptor interceptor;

    @Inject
    public Repository(ApiService apiService, Executor executor, CompositeDisposable compositeDisposable, NewsDao newsDao, InternetConnection connection, RequestInterceptor interceptor) {
        this.apiService = apiService;
        this.executor = executor;
        this.compositeDisposable = compositeDisposable;
        this.newsDao = newsDao;
        this.connection = connection;
        this.interceptor = interceptor;
    }




    public LiveData<List<News>> getDBListNews(Integer idStatus) {
        return newsDao.loadNewsStatus(idStatus);
    }



    //Load news list
    public MutableLiveData<List<NewsDTO>> getCurrentNews(Context context, MutableLiveData<List<NewsDTO>> ls) {
        compositeDisposable.add(apiService.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {

                    //Show News in message Build Monitor
                    for (int i = 0; i < news.size(); i++) {
                        Log.e(TAG, "message" + news.get(i));
                    }

                    ls.setValue(news);

                }, throwable -> {
                    String message = throwable.getMessage();
                    Log.e(TAG, message);
                    if("HTTP 400 ".equals(throwable.getMessage())){
                        Toast.makeText(context ,"Not News", Toast.LENGTH_LONG).show();
                    }
                }));
        return ls;
    }


    //TODO DELETES
    public void deleteNew(Integer id) {
        executor.execute(() -> newsDao.deleteNews(id));
    }

}
