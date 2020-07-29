package co.mba.strat_risk.ui;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.data.repository.Repository;

public class LoginViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public LoginViewModel(Repository repository) {
        this.repository = repository;
    }
}