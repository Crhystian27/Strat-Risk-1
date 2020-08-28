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
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;

public class InterestingFragment extends BaseFragment {


    @Inject
    ViewModelProvider.Factory factory;

    private RecyclerView recyclerView;
    private RelativeLayout empty;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_interesting;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_interesting);
        empty = view.findViewById(R.id.empty_relative);
        unitUI();

    }

    private void unitUI() {
        InterestingFragmentViewModel viewModel = ViewModelProviders.of(this, factory).get(InterestingFragmentViewModel.class);
        viewModel.fetchInterestingDB(Constants.INTERESTING_STATUS);
        viewModel.getInterestingDB().observe(getViewLifecycleOwner(), news -> {
            Utilities.setRecyclerView(getContext(), empty, news, recyclerView, Constants.INTERESTING_STATUS);
            ((BaseActivity) getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.app_name) + news.size());
        });
    }
}