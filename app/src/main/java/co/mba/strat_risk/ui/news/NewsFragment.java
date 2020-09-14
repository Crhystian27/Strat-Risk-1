package co.mba.strat_risk.ui.news;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;

import androidx.lifecycle.ViewModelProvider.Factory;

import java.util.Objects;


public class NewsFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;
    private RecyclerView recyclerView;
    private LinearLayout empty;
    private SwipeRefreshLayout refreshLayout;
    private RelativeLayout layout;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        refreshLayout = view.findViewById(R.id.swipe_news);
        recyclerView = view.findViewById(R.id.recycler_news);
        empty = view.findViewById(R.id.empty_relative);
        layout = view.findViewById(R.id.relative_news);

        ImageView imageView = view.findViewById(R.id.empty_icon);
        TextView textView = view.findViewById(R.id.empty_text);
        imageView.setImageResource(R.drawable.ic_news);
        textView.setText(getString(R.string.empty_news));

        NewsFragmentViewModel newsViewModel = ViewModelProviders.of(getBaseActivity(), factory).get(NewsFragmentViewModel.class);

        refreshLayout.setColorSchemeResources(R.color.colorPrimaryLight, R.color.colorNavigation);
        refreshLayout.setOnRefreshListener(() -> refreshSync(newsViewModel, this, factory));

        ((BaseActivity) getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.custom_elevation16dp));
        if (!InternetConnection.getAirPlaneMode(getBaseActivity()) || !InternetConnection.getConnection(getBaseActivity())) {
            unitUI(newsViewModel, this, factory);
        } else {
            refreshSync(newsViewModel, this, factory);
        }
    }

    private void refreshSync(NewsFragmentViewModel newsViewModel, Fragment fragment, Factory factory) {
        unitUI(newsViewModel, fragment, factory);
        new Handler().postDelayed(() -> refreshLayout.setRefreshing(false), 1400);
    }

    private void unitUI(NewsFragmentViewModel newsViewModel, Fragment fragment, Factory factory) {
        newsViewModel.fetchNewsInternet();
        newsViewModel.fetchNewsDB(Constants.LOCAL_STATUS);
        newsViewModel.getNewsDB().observe(getViewLifecycleOwner(), news -> {
            Log.e(getClass().getSimpleName(), "Status Local " + Constants.LOCAL_STATUS);
            Utilities.setRecyclerView(getContext(), getActivity(), empty, news, recyclerView, Constants.LOCAL_STATUS, fragment, factory, layout);
            String count = String.valueOf(news.size());
            if (!count.isEmpty()) {
                ((BaseActivity) Objects.requireNonNull(getActivity())).getToolbar().setTitle(getResources().getString(R.string.app_name) + " { " + count + " }");
            } else {
                ((BaseActivity) Objects.requireNonNull(getActivity())).getToolbar().setTitle(getResources().getString(R.string.app_name) + " { " + "0" + " }");
            }

        });
    }
}