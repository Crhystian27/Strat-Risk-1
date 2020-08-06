package co.mba.strat_risk.ui.login;

import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.data.model.Session;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;
import co.mba.strat_risk.widgets.DialogInformation;


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
        initUi(view);
    }

    private void initUi(View view) {

        ((BaseActivity) getBaseActivity()).getToolbar().setVisibility(View.GONE);

        TextView textView = view.findViewById(R.id.forgot_password);
        textView.setText(getString(R.string.string_forgot_password));

        textView.setOnClickListener(v ->
                Utilities.loadFragment(Objects.requireNonNull(getActivity()), new ForgotFragment(), R.id.login_fragment, Constants.TAG_FORGOT));

        MaterialCheckBox materialCheckBox = view.findViewById(R.id.login_checkbox);
        LinearLayout linearLayout = view.findViewById(R.id.login_button);
        TextInputEditText username = view.findViewById(R.id.login_username);
        TextInputEditText password = view.findViewById(R.id.login_password);
        ContentLoadingProgressBar progress = view.findViewById(R.id.login_progress);

        linearLayout.setOnClickListener(v -> {
            if (materialCheckBox.isChecked()) {
                if (TextUtils.isEmpty(username.getText()) && TextUtils.isEmpty(password.getText())) {
                    Log.e(getClass().getSimpleName(), getString(R.string.dialog_empty_camps));
                    DialogInformation.showDialog(getActivity(), getString(R.string.dialog_empty_camps), 0, null);

                } else {
                    progress.setVisibility(View.VISIBLE);
                    String string_username = String.valueOf(username.getText());
                    String string_password = String.valueOf(password.getText());
                    Session session = new Session(Constants.GRANT_TYPE, Constants.CLIENT_ID, Constants.CLIENT_SECRET, string_username, string_password);
                    viewModel.sendSession(getActivity(), session, progress);
                }
            } else {
                Log.e(getClass().getSimpleName(), getString(R.string.dialog_terms_conditions));
                DialogInformation.showDialog(getActivity(), getString(R.string.dialog_terms_conditions), 0, null);
            }
        });

        materialCheckBox.setOnClickListener(v -> DialogInformation.showDialog(getActivity(), getString(R.string.dialog_accept_terms_conditions), 1, materialCheckBox));
    }
}