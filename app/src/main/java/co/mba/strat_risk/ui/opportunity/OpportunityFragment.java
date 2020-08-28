package co.mba.strat_risk.ui.opportunity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.RelativeLayout;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;

public class OpportunityFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;

    private RecyclerView recyclerView;
    private RelativeLayout empty;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_opportunity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_opportunity);
        empty = view.findViewById(R.id.empty_relative);
        unitUI();
    }

    private void unitUI() {
        OpportunityFragmentViewModel viewModel = ViewModelProviders.of(this, factory).get(OpportunityFragmentViewModel.class);
        viewModel.fetchOpportunityDB(Constants.OPPORTUNITY_STATUS);
        viewModel.getOpportunityDB().observe(getViewLifecycleOwner(), news -> {
            ((BaseActivity) getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.app_name) + news.size());
            Utilities.setRecyclerView(getContext(), empty, news, recyclerView, Constants.OPPORTUNITY_STATUS);
        });
    }
}