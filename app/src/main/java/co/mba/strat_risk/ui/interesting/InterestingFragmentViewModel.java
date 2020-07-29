package co.mba.strat_risk.ui.interesting;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.repository.Repository;

public class InterestingFragmentViewModel extends ViewModel {

    private LiveData<List<News>> interestingLiveData;
    private static final String TAG = "Interesting_FV";
    private Repository repository;

    @Inject
    public InterestingFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<News>> initInteresting(Integer idStatus) {
        if (this.interestingLiveData != null) {
            Log.e(TAG, interestingLiveData.toString());
            return null;
        } else {
            //interestingLiveData = repository.getDBListNews(idStatus);
            return this.interestingLiveData;
        }
    }
}