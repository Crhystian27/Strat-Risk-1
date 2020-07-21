package co.mba.strat_risk.data.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.dto.ArticlesDTO;
import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.network.ApiService;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.network.RequestInterceptor;
import co.mba.strat_risk.util.Constants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


@Singleton
public class Repository {

    private static final String TAG = "repository";


    private ApiService apiService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Executor executor;
    private NewsDao newsDao;


    //Internet Connection
    private InternetConnection connection;
    private RequestInterceptor interceptor;

    @Inject
    public Repository(ApiService apiService, Executor executor, NewsDao newsDao, InternetConnection connection, RequestInterceptor interceptor) {
        this.apiService = apiService;
        this.executor = executor;
        this.newsDao = newsDao;
        this.connection = connection;
        this.interceptor = interceptor;
    }

    //Load opportunity, interesting and risk
    /*public LiveData<List<News>> getDBListNews(Integer idStatus) {
        return newsDao.loadNewsStatus(idStatus);
    }*/


    //Load news list
    public MutableLiveData<NewsDTO> getCurrentNews(Context context, MutableLiveData<NewsDTO> ls) {
        executor.execute(() -> compositeDisposable.add(apiService.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsDTO -> {
                    ls.setValue(newsDTO);
                    addItems(newsDTO);
                }, throwable -> {
                    Log.e(TAG, "getCurrentsNews" + throwable.getMessage());
                })));

        return ls;
    }

    private void addItems(NewsDTO news) {
        List<ArticlesDTO> articlesDTO = news.getArticles();
        for (int i = 0; i < articlesDTO.size(); i++) {
            newsDao.insertNews(new News(articlesDTO.get(i).getTitle(),
                    articlesDTO.get(i).getDescription(), articlesDTO.get(i).getAuthor(),
                    articlesDTO.get(i).getUrl(), articlesDTO.get(i).getUrlToImage(),
                    articlesDTO.get(i).getPublishedAt(), Constants.LOCAL_STATUS));
            Log.e(TAG, articlesDTO.get(i).toString());
        }
    }


    //TODO DELETES
    //public void deleteNew(Integer id) {
    //  executor.execute(() -> newsDao.deleteNews(id));
    //}

}
