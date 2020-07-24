package co.mba.strat_risk.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.ui.login.LoginFragment;
import co.mba.strat_risk.util.Utilities;

public class LoginActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;
    LoginViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.login_activity;
    }

    @Override
    protected int toolbarId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(LoginActivity.this, factory).get(LoginViewModel.class);

        initUI();
        setSupportActionBar(false, false);
    }

    private void initUI() {
        Utilities.loadFragment(LoginActivity.this, new LoginFragment(), R.id.login_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}