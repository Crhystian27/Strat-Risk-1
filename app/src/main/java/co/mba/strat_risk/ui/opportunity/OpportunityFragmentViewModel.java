package co.mba.strat_risk.ui.opportunity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.data.repository.Repository;

public class OpportunityFragmentViewModel extends ViewModel {

    private LiveData<List<News>> opportunityLiveData;
    private static final String TAG = "Opportunity_FV";
    private Repository repository;

    @Inject
    public OpportunityFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void fetchOpportunityDB(Integer status) {
        if (this.opportunityLiveData != null) {
            Log.e(TAG, opportunityLiveData.toString());
            return;
        }
        opportunityLiveData = repository.getNewsDB(status);
    }

    public LiveData<List<News>> getOpportunityDB() {
        return this.opportunityLiveData;
    }
}