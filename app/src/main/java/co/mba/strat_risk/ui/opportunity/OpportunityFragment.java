package co.mba.strat_risk.ui.opportunity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

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
    private LinearLayout empty;
    private RelativeLayout layout;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_opportunity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_opportunity);
        empty = view.findViewById(R.id.empty_relative);
        layout = view.findViewById(R.id.relative_opportunity);

        ImageView imageView = view.findViewById(R.id.empty_icon);
        TextView textView = view.findViewById(R.id.empty_text);
        imageView.setImageResource(R.drawable.ic_oportunidad);
        textView.setText(getString(R.string.empty_opportunity));

        unitUI();
    }

    private void unitUI() {
        OpportunityFragmentViewModel opportunityViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(OpportunityFragmentViewModel.class);
        opportunityViewModel.fetchOpportunityDB(Constants.OPPORTUNITY_STATUS);
        opportunityViewModel.getOpportunityDB().observe(getViewLifecycleOwner(), news -> {
            Utilities.setRecyclerView(getActivity(), getContext(), empty, news, recyclerView, Constants.OPPORTUNITY_STATUS, this, factory, layout);
            /*String count = String.valueOf(news.size());
            if (!count.isEmpty()) {
                ((BaseActivity) Objects.requireNonNull(getActivity())).getToolbar().setTitle(getResources().getString(R.string.app_name) + " { " + count + " }");
            } else {
                ((BaseActivity) Objects.requireNonNull(getActivity())).getToolbar().setTitle(getResources().getString(R.string.app_name) + " { " + "0" + " }");
            }*/
        });
    }
}