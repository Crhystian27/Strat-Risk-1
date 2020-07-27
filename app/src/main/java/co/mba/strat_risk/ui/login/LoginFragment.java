package co.mba.strat_risk.ui.login;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

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

    private void initUi(View view) {

        MaterialCheckBox materialCheckBox = view.findViewById(R.id.login_checkbox);
        CardView cardView = view.findViewById(R.id.login_button);
        TextInputEditText username = view.findViewById(R.id.login_username);
        TextInputEditText password = view.findViewById(R.id.login_password);

        cardView.setOnClickListener(v -> {

            if (materialCheckBox.isChecked()) {

                if (username.toString().isEmpty() || password.toString().isEmpty()) {
                    Toast.makeText(getBaseActivity(), "show dialog", Toast.LENGTH_LONG).show();

                } else {
                    //TODO CALL VIEWMODEL AND SEDN DATA

                }


            } else {
                Toast.makeText(getBaseActivity(), "show dialog", Toast.LENGTH_LONG).show();
            }

        });


    }
}