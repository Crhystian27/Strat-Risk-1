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
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;


public class NewsFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;
    private RecyclerView recyclerView;
    private RelativeLayout empty;


    @Override
    protected int layoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_news);
        empty = view.findViewById(R.id.empty_relative);
        unitUI();
    }


    private void unitUI() {
        ((BaseActivity) getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.custom_elevation16dp));

        NewsFragmentViewModel viewModel = ViewModelProviders.of(getBaseActivity(), factory).get(NewsFragmentViewModel.class);

        if (InternetConnection.getAirPlaneMode(getBaseActivity()) || InternetConnection.getConnection(getBaseActivity())) {
            viewModel.fetchNewsDB(Constants.LOCAL_STATUS);
            viewModel.getNewsDB().observe(getViewLifecycleOwner(), news -> {
                Log.e(getClass().getSimpleName(), "Status Local " + Constants.LOCAL_STATUS);
                Utilities.setRecyclerView(getContext(), empty, news, recyclerView, Constants.LOCAL_STATUS);
                ((BaseActivity) getBaseActivity()).getToolbar().setTitle(news.size() + getResources().getString(R.string.app_name));
            });

        } else {
            viewModel.fetchNewsInternet(getActivity()).observe(getViewLifecycleOwner(), newsDTO -> {
                Log.e(getClass().getSimpleName(), "Status Internet");
                List<News> list = newsDTO.getArticles();
                ((BaseActivity) getBaseActivity()).getToolbar().setTitle("(" + list.size() + ")" + getResources().getString(R.string.app_name));
                Utilities.setRecyclerView(getContext(), empty, list, recyclerView, Constants.LOCAL_STATUS);
            });
        }
    }
}