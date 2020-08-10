package co.mba.strat_risk.ui.login;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.widgets.DialogInformation;

public class ForgotFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;
    private ForgotFragmentViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.forgot_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, factory).get(ForgotFragmentViewModel.class);
        initUi(view);
    }

    private void initUi(View view) {
        ((BaseActivity)getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.string_forgot));
        ((BaseActivity) getBaseActivity()).getToolbar().setVisibility(View.VISIBLE);
        ((BaseActivity)getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.custom_elevation16dp));

        TextInputEditText email = view.findViewById(R.id.forgot_email);
        LinearLayout linearLayout = view.findViewById(R.id.forgot_button);
        ContentLoadingProgressBar progress = view.findViewById(R.id.forgot_progress);

        linearLayout.setOnClickListener(v -> {
            if (TextUtils.isEmpty(email.getText())) {
                Log.e(getClass().getSimpleName(), getString(R.string.dialog_empty_camps));
                DialogInformation.showDialog(getActivity(), getString(R.string.dialog_empty_camps), 0, null);

            } else {

                if (Patterns.EMAIL_ADDRESS.matcher(String.valueOf(email.getText())).matches()) {
                    String string_email = String.valueOf(email.getText());
                    progress.setVisibility(View.VISIBLE);
                    viewModel.sendEmail(getActivity(), string_email, progress);
                } else {
                    DialogInformation.showDialog(getActivity(), getString(R.string.dialog_matcher_email_address), 0, null);
                }
            }
        });
    }
}