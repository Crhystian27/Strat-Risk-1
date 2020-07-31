package co.mba.strat_risk.data.repository;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.dao.UserDao;
import co.mba.strat_risk.data.dto.AccessTokenDTO;
import co.mba.strat_risk.data.dto.ArticlesDTO;
import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.entity.User;
import co.mba.strat_risk.data.model.Session;
import co.mba.strat_risk.network.ApiService;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.network.RequestInterceptor;
import co.mba.strat_risk.util.AppPreferences;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.widgets.DialogInformation;
import io.reactivex.Scheduler;
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
    private UserDao userDao;


    //Internet Connection
    private InternetConnection connection;

    @Inject
    public Repository(ApiService apiService, Executor executor, NewsDao newsDao, UserDao userDao, InternetConnection connection) {
        this.apiService = apiService;
        this.executor = executor;
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.connection = connection;
    }

    //Load opportunity, interesting and risk
    /*public LiveData<List<News>> getDBListNews(Integer idStatus) {
        return newsDao.loadNewsStatus(idStatus);
    }*/

    private boolean verifyConnectionDialog(Activity activity) {

        return true;
    }


    public void getAccessToken(Activity activity, Session session, ContentLoadingProgressBar progressBar) {
        if (InternetConnection.isAirplaneMode(activity) && !DialogInformation.isShowing) {
            Log.e(getClass().getSimpleName(), activity.getString(R.string.dialog_airplane_mode));
            DialogInformation.showDialog(activity, activity.getString(R.string.dialog_airplane_mode), 0, null);
        } else if (InternetConnection.isConnected(activity) == 0 && !DialogInformation.isShowing) {
            Log.e(getClass().getSimpleName(), activity.getString(R.string.dialog_no_internet_connection));
            DialogInformation.showDialog(activity, activity.getString(R.string.dialog_no_internet_connection), 0, null);
        } else {
            executor.execute(() -> compositeDisposable.add(apiService.getToken(session)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(accessTokenDTO -> {

                        AppPreferences.getInstance().setAccessTokenDTO(accessTokenDTO);
                        Log.e(TAG, accessTokenDTO.toString());
                        Log.e(TAG, accessTokenDTO.getAccessToken());
                        progressBar.setVisibility(View.GONE);

                        getCurrentUser(activity, accessTokenDTO.getAccessToken().trim(), progressBar);

                    }, throwable -> {

                        DialogInformation.showDialog(activity, throwable.getMessage(), 0, null);
                        Log.e(TAG, throwable.getMessage());
                        progressBar.setVisibility(View.GONE);
                    })));
        }
    }

    public void getCurrentUser(Activity activity, String key, ContentLoadingProgressBar progressBar) {
        executor.execute(() -> compositeDisposable.add(apiService.getUser(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {

                    userDao.insertUser(user);
                    Log.e(TAG, user.toString());
                    progressBar.setVisibility(View.GONE);

                }, throwable -> {
                    DialogInformation.showDialog(activity, throwable.getMessage(), 0, null);
                    Log.e(TAG, throwable.getMessage());
                    progressBar.setVisibility(View.GONE);
                })));
    }


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
