package co.mba.strat_risk.ui.opportunity;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.RelativeLayout;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseFragment;

public class OpportunityFragment extends BaseFragment {


    OpportunityFragmentViewModel viewModel;

    private RecyclerView recyclerView;
    private RelativeLayout empty;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_opportunity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        unitUI();

        //TODO IMPLEMENT Count
        recyclerView = view.findViewById(R.id.recycler_interesting);
        empty = view.findViewById(R.id.empty_relative);
    }

    private void unitUI() {
        //((BaseActivity) getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.title_opportunity));
        //((BaseActivity) getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.activity_default_elevation));

        //viewModel = new ViewModelProvider(this).get(OpportunityViewModel.class);
        //viewModel.initOpportunity(Constants.OPPORTUNITY_STATUS).observe(getBaseActivity(), this::setRecyclerView);
    }

    /*private void setRecyclerView(List<News> ls) {
        Collections.sort(ls, (o1, o2) -> o1.getPublishedAt().compareTo(o2.getPublishedAt()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        OpportunityAdapter adapter = new OpportunityAdapter(getBaseActivity(), ls, empty);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
    }*/
}