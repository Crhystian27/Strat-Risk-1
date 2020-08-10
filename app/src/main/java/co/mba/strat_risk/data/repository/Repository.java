package co.mba.strat_risk.data.repository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.mba.strat_risk.R;
import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.dao.UserDao;
import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.entity.User;
import co.mba.strat_risk.data.model.Session;
import co.mba.strat_risk.network.ApiService;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.ui.MainActivity;
import co.mba.strat_risk.util.AppPreferences;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;
import co.mba.strat_risk.widgets.DialogInformation;
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

    @Inject
    public Repository(ApiService apiService, Executor executor, NewsDao newsDao, UserDao userDao) {
        this.apiService = apiService;
        this.executor = executor;
        this.newsDao = newsDao;
        this.userDao = userDao;
    }

    public LiveData<User> getCurrentUser(Integer id) {
        return userDao.loadUser(id);
    }

    public void getAccessToken(Activity activity, Session session, ContentLoadingProgressBar progressBar) {
        if (InternetConnection.getAirPlaneMode(activity)) {
            progressBar.setVisibility(View.GONE);
        } else if (InternetConnection.getConnection(activity)) {
            progressBar.setVisibility(View.GONE);
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

                        //DialogInformation.showDialog(activity, throwable.getMessage(), 0, null);
                        Log.e(TAG, throwable.getMessage());
                        progressBar.setVisibility(View.GONE);
                    })));
        }
    }

    public LiveData<User> getDBUser(Integer id) {
        return userDao.loadUser(id);
    }

    public void sendEmailRequest(Activity activity, String email, ContentLoadingProgressBar progress) {
        progress.setVisibility(View.GONE);
        Utilities.OpenSendEmail(activity, email);

    }

    public void getCurrentUser(Activity activity, String key, ContentLoadingProgressBar progressBar) {
        executor.execute(() -> compositeDisposable.add(apiService.getUser(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {

                    AppPreferences.getInstance().setUser(user);
                    userDao.insertUser(user);

                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    Utilities.removeThisActivityFromRunningActivities(activity.getClass());

                    Log.e(TAG, user.toString());
                    progressBar.setVisibility(View.GONE);
                    Utilities.removeThisActivityFromRunningActivities(activity.getClass());

                }, throwable -> {
                    DialogInformation.showDialog(activity, throwable.getMessage(), 0, null);
                    Log.e(TAG, throwable.getMessage());
                    progressBar.setVisibility(View.GONE);
                })));
    }


    //Load news list
    public MutableLiveData<NewsDTO> getNewsInternet(Context context, MutableLiveData<NewsDTO> ls) {
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

    //STATUS LOCAL-(0), OPPORTUNITY-(1), INTERMEDIATE-(2), RISK-(3)
    public LiveData<List<News>> getNewsDB(Integer status) {
        return newsDao.loadNewsStatus(status);
    }

    private void addItems(NewsDTO news) {
        List<News> newsDTO = news.getArticles();
        for (int i = 0; i < newsDTO.size(); i++) {
            News data = new News(newsDTO.get(i).getTitle(),
                    newsDTO.get(i).getDescription(), newsDTO.get(i).getAuthor(),
                    newsDTO.get(i).getUrl(), newsDTO.get(i).getUrlToImage(),
                    newsDTO.get(i).getPublishedAt(), newsDTO.get(i).getContent(), Constants.LOCAL_STATUS);
            newsDao.insertNews(data);
            Log.e(TAG, data.toString());
        }
    }


   /* private boolean getAirPlaneMode(Activity activity) {
        if (InternetConnection.isAirplaneMode(activity) && !DialogInformation.isShowing) {
            Log.e(getClass().getSimpleName(), activity.getString(R.string.dialog_airplane_mode));
            DialogInformation.showDialog(activity, activity.getString(R.string.dialog_airplane_mode), 0, null);
        }
        return false;
    }

    private boolean getConnection(Activity activity) {
        if (InternetConnection.isConnected(activity) == 0 && !DialogInformation.isShowing) {
            Log.e(getClass().getSimpleName(), activity.getString(R.string.dialog_no_internet_connection));
            DialogInformation.showDialog(activity, activity.getString(R.string.dialog_no_internet_connection), 0, null);
        }
        return false;
    }*/


    //TODO DELETES
    //public void deleteNew(Integer id) {
    //  executor.execute(() -> newsDao.deleteNews(id));
    //}

}
