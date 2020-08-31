package co.mba.strat_risk.ui.interesting;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.ui.news.NewsFragmentViewModel;
import co.mba.strat_risk.ui.opportunity.OpportunityFragment;
import co.mba.strat_risk.ui.opportunity.OpportunityFragmentViewModel;
import co.mba.strat_risk.ui.risk.RiskFragmentViewModel;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;

public class InterestingFragment extends BaseFragment {


    @Inject
    ViewModelProvider.Factory factory;

    private RecyclerView recyclerView;
    private RelativeLayout empty;
    private RelativeLayout layout;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_interesting;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_interesting);
        empty = view.findViewById(R.id.empty_relative);
        layout = view.findViewById(R.id.relative_intermediate);
        unitUI();

    }

    private void unitUI() {
        NewsFragmentViewModel newsViewModel = ViewModelProviders.of(this, factory).get(NewsFragmentViewModel.class);
        OpportunityFragmentViewModel opportunityViewModel = ViewModelProviders.of(this, factory).get(OpportunityFragmentViewModel.class);
        InterestingFragmentViewModel interestingViewModel = ViewModelProviders.of(this, factory).get(InterestingFragmentViewModel.class);
        RiskFragmentViewModel riskViewModel = ViewModelProviders.of(this, factory).get(RiskFragmentViewModel.class);


        interestingViewModel.fetchInterestingDB(Constants.INTERESTING_STATUS);
        interestingViewModel.getInterestingDB().observe(getViewLifecycleOwner(), news -> {
            Utilities.setRecyclerView(getContext(), getActivity(), empty, news, recyclerView, Constants.INTERESTING_STATUS, newsViewModel, opportunityViewModel, interestingViewModel, riskViewModel, layout);
            ((BaseActivity) getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.app_name) + news.size());
        });
    }
}