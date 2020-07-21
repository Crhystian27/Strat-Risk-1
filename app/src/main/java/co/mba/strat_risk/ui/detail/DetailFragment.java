package co.mba.strat_risk.ui.detail;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseFragment;

public class DetailFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;
    private DetailViewModel viewModel;
    private TextView textView;



    @Override
    protected int layoutRes() {
        return R.layout.detail_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textView = view.findViewById(R.id.prueba);



        //viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel.class);

        super.onViewCreated(view, savedInstanceState);
    }
}