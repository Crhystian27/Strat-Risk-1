package co.mba.strat_risk.ui.news;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.ui.interesting.InterestingFragmentViewModel;
import co.mba.strat_risk.ui.opportunity.OpportunityFragmentViewModel;
import co.mba.strat_risk.ui.risk.RiskFragmentViewModel;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;


public class NewsFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;
    private RecyclerView recyclerView;
    private RelativeLayout empty;
    private RelativeLayout layout;


    @Override
    protected int layoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_news);
        empty = view.findViewById(R.id.empty_relative);
        layout = view.findViewById(R.id.relative_news);
        unitUI();
    }

    private void unitUI() {
        ((BaseActivity) getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.custom_elevation16dp));

        NewsFragmentViewModel newsViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(NewsFragmentViewModel.class);
        OpportunityFragmentViewModel opportunityViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(OpportunityFragmentViewModel.class);
        InterestingFragmentViewModel interestingViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(InterestingFragmentViewModel.class);
        RiskFragmentViewModel riskViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(RiskFragmentViewModel.class);


        //TODO SIEMPRE CARGAR DE BASE DE DATOS, NO DIRECTAMENTE DE INTERNET
        if (!InternetConnection.getAirPlaneMode(getBaseActivity()) || !InternetConnection.getConnection(getBaseActivity())) {
            newsViewModel.fetchNewsDB(Constants.LOCAL_STATUS);
            newsViewModel.getNewsDB().observe(getViewLifecycleOwner(), news -> {
                Log.e(getClass().getSimpleName(), "Status Local " + Constants.LOCAL_STATUS);
                Utilities.setRecyclerView(getContext(), getActivity(), empty, news, recyclerView, Constants.LOCAL_STATUS, newsViewModel, opportunityViewModel, interestingViewModel, riskViewModel, layout);
            });

        } else {
            newsViewModel.fetchNewsInternet(getActivity()).observe(getViewLifecycleOwner(), newsDTO -> {
                Log.e(getClass().getSimpleName(), "Status Internet");
                List<News> list = newsDTO.getArticles();
                //((BaseActivity) getBaseActivity()).getToolbar().setTitle("(" + list.size() + ")" + getResources().getString(R.string.app_name));
                Utilities.setRecyclerView(getContext(), getActivity(), empty, list, recyclerView, Constants.LOCAL_STATUS, newsViewModel, opportunityViewModel, interestingViewModel, riskViewModel, layout);
            });
        }
    }
}