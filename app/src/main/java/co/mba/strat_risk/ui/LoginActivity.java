package co.mba.strat_risk.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.ui.login.LoginFragment;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;
import co.mba.strat_risk.widgets.SnackBarInformation;

public class LoginActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;
    LoginViewModel viewModel;
    RelativeLayout layout;

    private boolean recentlyBackPressed = false;
    private Runnable exitRunnable = () -> recentlyBackPressed = false;
    private Handler exitHandler = new Handler();

    @Override
    protected int layoutRes() {
        return R.layout.login_activity;
    }

    @Override
    protected int toolbarId() {
        return R.id.toolbar_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = findViewById(R.id.login_relative);
        viewModel = ViewModelProviders.of(LoginActivity.this, factory).get(LoginViewModel.class);

        if (viewModel.initLogin(LoginActivity.this, MainActivity.class)) {
            initUI();
            Log.e(getClass().getSimpleName(), "No user");
        }
    }

    private void initUI() {
        setSupportActionBar(true, true);
        Utilities.loadFragment(LoginActivity.this, new LoginFragment(), R.id.login_fragment, Constants.TAG_LOGIN);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            System.out.println("FRAGMENT_TAG -> " + Utilities.getTagFragment(LoginActivity.this));

            if (Constants.TAG_LOGIN.equals(Utilities.getTagFragment(LoginActivity.this))) {
                if (recentlyBackPressed) {
                    exitHandler.removeCallbacks(exitRunnable);
                    recentlyBackPressed = false;
                    moveTaskToBack(true);
                    finish();
                } else {
                    recentlyBackPressed = true;

                    SnackBarInformation.showSnackBar(LoginActivity.this, layout, getString(R.string.press_again_to_exit), "fonts/montserrat_regular_.ttf");

                    exitHandler.postDelayed(exitRunnable, 2000L);
                }
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}