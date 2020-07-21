package co.mba.strat_risk.ui;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.data.repository.Repository;

public class NewsDetailViewModel extends ViewModel {

    private static final String TAG = "NewsDetail_AV";
    private Repository repository;


    @Inject
    public NewsDetailViewModel(Repository repository) {
        this.repository = repository;
    }
}
