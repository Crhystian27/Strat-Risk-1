package co.mba.strat_risk.ui.news;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.adapter.NewsAdapter;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.data.dto.NewsDTO;

public class NewsFragment extends BaseFragment {

    NewsViewModel viewModel;

    private RecyclerView recyclerView;
    private RelativeLayout empty;


    @Override
    protected int layoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        unitUI();
        recyclerView = view.findViewById(R.id.recycler_news);
        empty = view.findViewById(R.id.empty_relative);
    }

    private void unitUI() {
        ((BaseActivity) getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.title_news));
        ((BaseActivity) getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.activity_default_elevation));

        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.initNews();
    }

    private void setRecyclerView(List<NewsDTO> ls) {
        Collections.sort(ls, (o1, o2) -> o1.getPublishedAt().compareTo(o2.getPublishedAt()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsAdapter adapter = new NewsAdapter(getBaseActivity(), ls, empty);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
    }
}