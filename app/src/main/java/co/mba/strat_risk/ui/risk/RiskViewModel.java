package co.mba.strat_risk.ui.risk;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.repository.Repository;

public class RiskViewModel extends ViewModel {

    private LiveData<List<News>> riskLiveData;
    private static final String TAG = "Risk_FV";
    private Repository repository;

    public RiskViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<News>> initRisk(Integer idStatus){
        if (this.riskLiveData != null) {
            Log.e(TAG, riskLiveData.toString());
            return null;
        } else {
            riskLiveData = repository.getDBListNews(idStatus);
            return this.riskLiveData;
        }
    }
}