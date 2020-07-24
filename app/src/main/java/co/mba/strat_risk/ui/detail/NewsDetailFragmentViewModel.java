package co.mba.strat_risk.ui.detail;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.data.repository.Repository;


public class NewsDetailFragmentViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public NewsDetailFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}