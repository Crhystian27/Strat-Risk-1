package co.mba.strat_risk.ui.login;

import android.app.Activity;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.data.model.Session;
import co.mba.strat_risk.data.repository.Repository;

public class LoginFragmentViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public LoginFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void sendSession(Activity activity, Session session, ContentLoadingProgressBar progressBar){
        repository.getAccessToken(activity, session, progressBar);
    }
}