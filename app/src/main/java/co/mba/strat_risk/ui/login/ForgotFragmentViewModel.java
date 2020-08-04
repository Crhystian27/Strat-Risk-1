package co.mba.strat_risk.ui.login;

import android.app.Activity;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.data.repository.Repository;

public class ForgotFragmentViewModel extends ViewModel {

    private Repository repository;

    @Inject
    public ForgotFragmentViewModel(Repository repository) {
        this.repository = repository;
    }

    public void sendEmail(Activity activity, String email, ContentLoadingProgressBar progress) {
        repository.sendEmailRequest(activity, email, progress);
    }
}