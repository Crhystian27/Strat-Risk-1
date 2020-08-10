package co.mba.strat_risk.ui.risk;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.repository.Repository;

public class RiskFragmentViewModel extends ViewModel {

    private LiveData<List<News>> riskLiveData;
    private static final String TAG = "Risk_FV";
    private Repository repository;

    @Inject
    public RiskFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void fetchRiskDB(Integer status) {
        if (this.riskLiveData != null) {
            Log.e(TAG, riskLiveData.toString());
            return;
        }
        riskLiveData = repository.getNewsDB(status);
    }

    public LiveData<List<News>> getRiskDB() {
        return this.riskLiveData;
    }
}