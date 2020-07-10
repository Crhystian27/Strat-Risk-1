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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class NewsViewModel extends ViewModel {

    private CompositeDisposable disposable;
    private MutableLiveData<List<NewsDTO>> newsLiveData = new MutableLiveData<>();
    private static final String TAG = "News_FV";
    private Repository repository;

    @Inject
    public NewsViewModel(Repository repository) {
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    public LiveData<List<NewsDTO>> getNews() {
        return this.newsLiveData;
    }

    public void initNews() {
        disposable.add(repository.getCurrentNews2().subscribeWith(new DisposableSingleObserver<List<NewsDTO>>() {
            @Override
            public void onSuccess(List<NewsDTO> newsDTOS) {
                newsLiveData.setValue(newsDTOS);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, " - " + e.getMessage());
                newsLiveData.setValue(null);
            }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}