package co.mba.strat_risk.ui.risk;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class RiskFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;
    private RecyclerView recyclerView;
    private LinearLayout empty;
    private RelativeLayout layout;


    @Override
    protected int layoutRes() {
        return R.layout.fragment_risk;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_risk);
        empty = view.findViewById(R.id.empty_relative);
        layout = view.findViewById(R.id.relative_risk);

        ImageView imageView = view.findViewById(R.id.empty_icon);
        TextView textView = view.findViewById(R.id.empty_text);
        imageView.setImageResource(R.drawable.ic_amenaza);
        textView.setText(getString(R.string.empty_risk));

        unitUI();
    }

    private void unitUI() {
        RiskFragmentViewModel riskViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(RiskFragmentViewModel.class);
        riskViewModel.fetchRiskDB(Constants.RISK_STATUS);
        riskViewModel.getRiskDB().observe(getViewLifecycleOwner(), news -> {
            Utilities.setRecyclerView(getContext(), getActivity(), empty, news, recyclerView, Constants.RISK_STATUS, this, factory, layout);
            ((BaseActivity) getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.app_name) + news.size());
        });
    }
}