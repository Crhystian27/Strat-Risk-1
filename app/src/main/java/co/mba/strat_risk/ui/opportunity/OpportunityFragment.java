package co.mba.strat_risk.ui.opportunity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseFragment;

public class OpportunityFragment extends BaseFragment {

    private OpportunityViewModel mViewModel;


    @Override
    protected int layoutRes() {
        return R.layout.opportunity_fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(OpportunityViewModel.class);
        View view = inflater.inflate(R.layout.opportunity_fragment, container, false);
        return view;
    }
}