package co.mba.strat_risk.ui.news;

import android.os.Bundle;
import android.util.Log;
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
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.util.Constants;


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
        //((BaseActivity) getActivity()).getToolbar().setTitle(getResources().getString(R.string.title_news));
        //((BaseActivity) getActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.custom_elevation16dp));

        NewsFragmentViewModel viewModel = ViewModelProviders.of(this, factory).get(NewsFragmentViewModel.class);

        if (InternetConnection.getAirPlaneMode(getActivity()) || InternetConnection.getConnection(getActivity())) {
            viewModel.fetchNewsDB(Constants.LOCAL_STATUS);
            viewModel.getNewsDB().observe(getViewLifecycleOwner(), this::setRecyclerView);
            Log.e(getClass().getSimpleName(), "Status Local " + Constants.LOCAL_STATUS);
        } else {
            viewModel.fetchNewsInternet(getActivity()).observe(getViewLifecycleOwner(), newsDTO -> {
                List<News> list = newsDTO.getArticles();
                Log.e(getClass().getSimpleName(), "Status Internet");
                setRecyclerView(list);
            });
        }
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