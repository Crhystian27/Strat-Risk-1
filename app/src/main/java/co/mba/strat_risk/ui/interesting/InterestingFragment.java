package co.mba.strat_risk.ui.interesting;

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

import java.util.Objects;

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
    private LinearLayout empty;
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

        ImageView imageView = view.findViewById(R.id.empty_icon);
        TextView textView = view.findViewById(R.id.empty_text);
        imageView.setImageResource(R.drawable.ic_neutral);
        textView.setText(getString(R.string.empty_intermediate));

        unitUI();

    }

    private void unitUI() {
        InterestingFragmentViewModel interestingViewModel = ViewModelProviders.of(this, factory).get(InterestingFragmentViewModel.class);
        interestingViewModel.fetchInterestingDB(Constants.INTERESTING_STATUS);
        interestingViewModel.getInterestingDB().observe(getViewLifecycleOwner(), news -> {
            Utilities.setRecyclerView(getActivity(), getContext(), empty, news, recyclerView, Constants.INTERESTING_STATUS, this, factory, layout);
            /*String count = String.valueOf(news.size());
            if (!count.isEmpty()) {
                ((BaseActivity) Objects.requireNonNull(getActivity())).getToolbar().setTitle(getResources().getString(R.string.app_name) + " { " + count + " }");
            } else {
                ((BaseActivity) Objects.requireNonNull(getActivity())).getToolbar().setTitle(getResources().getString(R.string.app_name) + " { " + "0" + " }");
            }*/
        });
    }
}