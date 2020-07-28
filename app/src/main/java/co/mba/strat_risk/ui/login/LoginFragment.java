package co.mba.strat_risk.ui.login;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.data.model.Session;
import co.mba.strat_risk.util.Constants;


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

        MaterialCheckBox materialCheckBox = view.findViewById(R.id.login_checkbox);
        CardView cardView = view.findViewById(R.id.login_button);
        TextInputEditText username = view.findViewById(R.id.login_username);
        TextInputEditText password = view.findViewById(R.id.login_password);

        cardView.setOnClickListener(v -> {

            if (materialCheckBox.isChecked()) {

                //TODO Cambiar los TOas por Dialogs
                if (TextUtils.isEmpty(username.getText()) && TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(getBaseActivity(), "Campos Vacios", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getBaseActivity(), "Campos diligenciados", Toast.LENGTH_SHORT).show();
                    //TODO CALL VIEWMODEL AND SEDN DATA
                    String string_username = String.valueOf(username.getText());
                    String string_password = String.valueOf(password.getText());
                    Session session = new Session(Constants.GRANT_TYPE, Integer.parseInt(Constants.CLIENT_ID), Constants.CLIENT_SECRET, string_username, string_password);
                    viewModel.sendSession(getBaseActivity(), session);
                }

            } else {
                Toast.makeText(getBaseActivity(), "Terminos no Aceptados dialog", Toast.LENGTH_SHORT).show();
            }
        });

        materialCheckBox.setOnClickListener(view1 -> {

            //TODO show Dialog

        });

    }
}