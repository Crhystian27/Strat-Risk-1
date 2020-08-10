package co.mba.strat_risk.ui.interesting;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.adapter.NewsAdapter;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.util.Constants;

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
        //((BaseActivity) getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.title_interesting));
        //((BaseActivity) getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.custom_elevation16dp));

        InterestingFragmentViewModel viewModel = ViewModelProviders.of(this, factory).get(InterestingFragmentViewModel.class);

        viewModel.fetchInterestingDB(Constants.INTERESTING_STATUS);
        viewModel.getInterestingDB().observe(getViewLifecycleOwner(), this::setRecyclerView);
    }

    private void setRecyclerView(List<News> list) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NewsAdapter adapter = new NewsAdapter(getContext(), list, empty);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}