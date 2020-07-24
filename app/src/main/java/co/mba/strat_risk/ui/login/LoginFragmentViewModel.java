package co.mba.strat_risk.ui.login;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.data.repository.Repository;

public class LoginFragmentViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public LoginFragmentViewModel(Repository repository) {
        this.repository = repository;
    }
}