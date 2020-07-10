package co.mba.strat_risk.ui.risk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.adapter.InterestingAdapter;
import co.mba.strat_risk.adapter.RiskAdapter;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.util.Constants;

public class RiskFragment extends BaseFragment {


    RiskViewModel viewModel;

    private RecyclerView recyclerView;
    private RelativeLayout empty;


    @Override
    protected int layoutRes() {
        return R.layout.fragment_risk;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        unitUI();

        recyclerView = view.findViewById(R.id.recycler_risk);
        empty = view.findViewById(R.id.empty_relative);
    }

    private void unitUI() {
        ((BaseActivity) getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.title_risk));
        ((BaseActivity) getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.activity_default_elevation));

        viewModel = new ViewModelProvider(this).get(RiskViewModel.class);
        viewModel.initRisk(Constants.RISK_STATUS).observe(getBaseActivity(), this::setRecyclerView);
    }

    private void setRecyclerView(List<News> ls) {
        Collections.sort(ls, (o1, o2) -> o1.getPublishedAt().compareTo(o2.getPublishedAt()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RiskAdapter adapter = new RiskAdapter(getBaseActivity(), ls, empty);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
    }

}