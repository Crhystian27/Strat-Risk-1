package co.mba.strat_risk.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;

import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.network.ApiService;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.network.RequestInterceptor;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class Repository {

    private static final String TAG = "repo";


    private ApiService apiService;
    private Executor executor;
    //private NewsDao newsDao;
    //Internet Connection
    private InternetConnection connection;
    private RequestInterceptor interceptor;
    private final Scheduler schedulerIO;
    private final Scheduler androidScheduler;

    @Inject
    public Repository(@Named("schedulerIO") Scheduler schedulerIO ,@Named("androidScheduler") Scheduler androidScheduler,
                      ApiService apiService, Executor executor /*NewsDao newsDao*/, InternetConnection connection, RequestInterceptor interceptor) {
        this.schedulerIO = schedulerIO;
        this.androidScheduler = androidScheduler;
        this.apiService = apiService;
        this.executor = executor;
        //this.newsDao = newsDao;
        this.connection = connection;
        this.interceptor = interceptor;
    }

    //Load opportunity, interesting and risk
    /*public LiveData<List<News>> getDBListNews(Integer idStatus) {
        return newsDao.loadNewsStatus(idStatus);
    }*/


    public Single<List<NewsDTO>> getCurrentNews2(){
        return apiService.getNews()
                .subscribeOn(schedulerIO)
                .observeOn(androidScheduler);
    }

    //Load news list
    /*public MutableLiveData<List<NewsDTO>> getCurrentNews(Context context, MutableLiveData<List<NewsDTO>> ls) {
       executor.execute(()->{
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
       });
        return ls;
    }*/


    //TODO DELETES
    //public void deleteNew(Integer id) {
      //  executor.execute(() -> newsDao.deleteNews(id));
    //}

}
