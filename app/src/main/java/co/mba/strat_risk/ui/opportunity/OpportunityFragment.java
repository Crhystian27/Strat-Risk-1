package co.mba.strat_risk.ui.opportunity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.adapter.NewsAdapter;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.util.Constants;

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
        ((BaseActivity)getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.title_opportunity));
        ((BaseActivity)getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.custom_elevation16dp));

        OpportunityFragmentViewModel viewModel = ViewModelProviders.of(this, factory).get(OpportunityFragmentViewModel.class);

        viewModel.fetchOpportunityDB(Constants.OPPORTUNITY_STATUS);
        viewModel.getOpportunityDB().observe(getViewLifecycleOwner(), this::setRecyclerView);
    }

    private void setRecyclerView(List<News> list) {
        //TODO VALIDAR SI EL RECYCLER NO ES EMPTY
        //if(recyclerView != null){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NewsAdapter adapter = new NewsAdapter(getContext(), list, empty);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //}

    }
}