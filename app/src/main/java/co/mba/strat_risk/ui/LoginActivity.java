package co.mba.strat_risk.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.ui.login.ForgotFragment;
import co.mba.strat_risk.ui.login.LoginFragment;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;

public class LoginActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;
    LoginViewModel viewModel;
    TextView textView;

    private boolean recentlyBackPressed = false;
    private Runnable exitRunnable = () -> recentlyBackPressed = false;
    private Handler exitHandler = new Handler();

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
        textView = findViewById(R.id.forgot_password);
        textView.setVisibility(View.VISIBLE);
        Utilities.loadFragment(LoginActivity.this, new LoginFragment(), R.id.login_fragment, Constants.TAG_LOGIN);

        textView.setOnClickListener(view -> {
            textView.setVisibility(View.GONE);
            Utilities.loadFragment(LoginActivity.this, new ForgotFragment(), R.id.login_fragment, Constants.TAG_FORGOT);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    //TODO: back forgot fragment to login fragment

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
                    Toast.makeText(this, getResources().getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
                    exitHandler.postDelayed(exitRunnable, 2000L);
                }
            } else {
                textView.setVisibility(View.VISIBLE);
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}