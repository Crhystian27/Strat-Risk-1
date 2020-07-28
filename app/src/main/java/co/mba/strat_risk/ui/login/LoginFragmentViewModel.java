package co.mba.strat_risk.ui.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import co.mba.strat_risk.data.model.Session;
import co.mba.strat_risk.data.repository.Repository;

public class LoginFragmentViewModel extends ViewModel {

    private static final String TAG = "Login_FV";
    private Repository repository;

    @Inject
    public LoginFragmentViewModel(Repository repository) {
        this.repository = repository;
    }


    public void sendSession(Context context, Session session){
        repository.getAccessToken(context, session);
    }
}