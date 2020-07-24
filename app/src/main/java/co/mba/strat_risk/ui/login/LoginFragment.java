package co.mba.strat_risk.ui.login;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseFragment;


public class LoginFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;

    private LoginFragmentViewModel viewModel;


    @Override
    protected int layoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, factory).get(LoginFragmentViewModel.class);


    }
}