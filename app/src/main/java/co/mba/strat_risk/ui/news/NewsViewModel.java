package co.mba.strat_risk.ui.news;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.data.dto.NewsDTO;
import co.mba.strat_risk.data.repository.Repository;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<NewsDTO>> newsLiveData;
    private static final String TAG = "News_FV";
    private Repository repository;

    @Inject
    public NewsViewModel(Repository repository) {
        this.repository = repository;
    }

    /*public LiveData<List<NewsDTO>> getNews() {
        return this.newsLiveData;
    }*/

    /*public void fetchNews() {
        disposable.add(repository.getCurrentNews().subscribeWith(new DisposableSingleObserver<List<NewsDTO>>() {
            @Override
            public void onSuccess(List<NewsDTO> newsDTOS) {
                newsLiveData.setValue(newsDTOS);
                Gson gson = new Gson();
                String stringJson = gson.toJson(newsDTOS);
                System.out.println("Json -> " + stringJson);
            }

            @Override
            public void onError(Throwable e) {
                newsLiveData.setValue(null);
                System.out.println("Error" + e.getMessage());
            }
        }));
    }*/

    public LiveData<List<NewsDTO>> fetchNewsDTO(Context context) {
        if (this.newsLiveData != null) {
            Log.e(TAG, newsLiveData.toString());
        } else {
            newsLiveData = new MutableLiveData<>();
            newsLiveData = repository.getCurrentNews(context, newsLiveData);
        }
        return newsLiveData;
    }
}