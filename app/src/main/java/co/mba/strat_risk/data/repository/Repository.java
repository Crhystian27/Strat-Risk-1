package co.mba.strat_risk.data.repository;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.LiveData;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.mba.strat_risk.R;
import co.mba.strat_risk.data.dao.NewsDao;
import co.mba.strat_risk.data.dao.UserDao;

import co.mba.strat_risk.data.dto.ItemsDTO;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.entity.User;
import co.mba.strat_risk.data.model.Session;
import co.mba.strat_risk.network.ApiService;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.ui.MenuActivity;
import co.mba.strat_risk.ui.TermsActivity;
import co.mba.strat_risk.util.AppPreferences;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;
import co.mba.strat_risk.widgets.DialogInformation;
import co.mba.strat_risk.widgets.SnackBarInformation;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


@Singleton
public class Repository {

    private static final String TAG = "repository";
    public static boolean isShowing = false;

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
                        newsDao.deleteAllNews();
                        progressBar.setVisibility(View.GONE);

                        getCurrentUser(activity, accessTokenDTO.getAccessToken().trim(), progressBar);

                    }, throwable -> {
                        if ("HTTP 400 Bad Request".equals(throwable.getMessage())) {
                            DialogInformation.showDialog(activity, activity.getResources().getString(R.string.string_login_error), 0, null);
                        }
                        if ("HTTP 404 Not Found".equals(throwable.getMessage())) {
                            DialogInformation.showDialog(activity, activity.getResources().getString(R.string.string_server_status), 0, null);
                        }
                        Log.e(TAG, "getAccessToken" + throwable.getMessage());
                        progressBar.setVisibility(View.GONE);
                    })));
        }
    }

    public LiveData<User> getDBUser(Integer id) {
        return userDao.loadUser(id);
    }

    public boolean existUserDB() {
        return userDao.existUser();
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

                    //userDao.deleteUser();
                    AppPreferences.getInstance().setUser(user);
                    userDao.insertUser(user);

                    Intent intent = new Intent(activity, MenuActivity.class);
                    activity.startActivity(intent);
                    Log.e(TAG, user.toString());
                    progressBar.setVisibility(View.GONE);
                    Utilities.removeThisActivityFromRunningActivities(activity.getClass());
                    activity.finish();

                }, throwable -> {
                    DialogInformation.showDialog(activity, throwable.getMessage(), 0, null);
                    Log.e(TAG, "THROWABLE-> getCurrentUser" + throwable.getMessage());
                    progressBar.setVisibility(View.GONE);
                })));
    }


    //Load news list
    public void getNewsInternet(String lr, String gl, String start, String key, String cx, String search) {
        executor.execute(() ->
                compositeDisposable.add(apiService.getNews(lr, gl, start, key, cx, search)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(dto -> {
                            Log.e(TAG, dto.toString());
                            List<ItemsDTO> items = dto.getItems();
                            saveNewsDB(items);
                        }, throwable ->
                                Log.e(TAG, "Server Error" + throwable.getMessage() + " " + Arrays.toString(throwable.getStackTrace())))));
    }


    private void saveNewsDB(List<ItemsDTO> items) {
        for (int i = 0; i < items.size(); i++) {

            News data = new News(items.get(i).getKind(),
                    items.get(i).getTitle(), items.get(i).getSnippet(), items.get(i).getLink(), items.get(i).getPagemap().cse_image != null ? items.get(i).getPagemap().getCse_image().toString() : null,
                    Constants.LOCAL_STATUS);

            if (!newsDao.compareTo(items.get(i).getLink())) {
                newsDao.insertNews(data);
            }
        }
    }

    //STATUS  --> LOCAL - (0), OPPORTUNITY - (1), INTERMEDIATE - (2), RISK - (3), REMOVE - (-1)
    public LiveData<List<News>> getNewsDB(Integer status) {
        return newsDao.loadNewsStatus(status);
    }


    public void addNews(Activity activity, News news, Integer newStatus, RelativeLayout layout, String message) {
        News newsData = new News(news.getKind(), news.getTitle(),
                news.getSnippet(), news.getLink(), news.getSrc(),
                newStatus);
        newsDao.insertNews(newsData);
        newsDao.deleteNews(news.getId());
        SnackBarInformation.showSnackBar(activity, layout, message, "fonts/montserrat_regular_.ttf");
    }


    public void removeNews(Activity activity, News news, Integer newStatus) {
        News newsData = new News(news.getKind(), news.getTitle(),
                news.getSnippet(), news.getLink(), news.getSrc(),
                newStatus);
        DialogRepository(newsData, news.getId(), activity, activity.getString(R.string.string_dialog_remove), 0);

    }

    public void logOut(Activity activity, Class aClass) {

        AppPreferences.getInstance().setUser(null);
        AppPreferences.getInstance().setAccessTokenDTO(null);

        Intent intent = new Intent(activity, aClass);
        intent.putExtra(Constants.TAG_EXTRA, activity.getClass().getSimpleName());
        activity.startActivity(intent);
        newsDao.deleteAllNews();
        userDao.deleteUser();
    }

    private void DialogRepository(News news, Integer id, Activity activity, String message, Integer status) {

        final Dialog dialog = new Dialog(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.item_dialog_information, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(false);

        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        //int height = metrics.heightPixels;

        TextView text_accept = dialog.findViewById(R.id.txt_dialog_accept);
        TextView text_more = dialog.findViewById(R.id.more);
        TextView text_cancel = dialog.findViewById(R.id.txt_dialog_cancel);
        TextView text_message = dialog.findViewById(R.id.txt_dialog_message_title);
        RadioGroup radioGroup = dialog.findViewById(R.id.dialog_radio_group);
        ImageView img = dialog.findViewById(R.id.dialog_img);
        Drawable drawable = activity.getDrawable(R.drawable.ic_gris);

        RadioButton radio1 = dialog.findViewById(R.id.dialog_radio_button1);

        Glide.with(activity.getApplicationContext())
                .applyDefaultRequestOptions(RequestOptions.placeholderOf(drawable).error(drawable).circleCrop())
                .load(drawable)
                .into(img);

        switch (status) {
            case 0:
                text_message.setVisibility(View.VISIBLE);
                text_message.setVerticalScrollBarEnabled(false);
                radioGroup.setVisibility(View.GONE);
                text_message.setLines(2);
                text_more.setVisibility(View.GONE);
                text_message.setText(message);
                text_cancel.setVisibility(View.VISIBLE);
                break;
        }

        text_cancel.setOnClickListener(v -> {
            isShowing = false;
            dialog.dismiss();
        });

        text_accept.setOnClickListener(v -> {
            isShowing = false;
            Log.e(TAG, news.toString());
            Log.e(TAG, "id ->" + id);
            newsDao.insertNews(news);
            newsDao.deleteNews(id);
            dialog.dismiss();
            activity.finish();
        });
        isShowing = true;

        if (status.equals(0)) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        dialog.show();
    }
}
