package co.mba.strat_risk.ui.news;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import co.mba.strat_risk.R;
import co.mba.strat_risk.adapter.NewsAdapter;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.data.dto.NewsDTO;

public class NewsFragment extends BaseFragment {

    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private NewsViewModel viewModel;
    private LinearLayout empty;


    @Override
    protected int layoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
    }


    private void unitUI(){
        ((BaseActivity)getBaseActivity()).getToolbar().setTitle(getResources().getString(R.string.title_news));
        ((BaseActivity)getBaseActivity()).getToolbar().setElevation(getResources().getDimension(R.dimen.activity_default_elevation));


    }

    private void setRecyclerView(List<NewsDTO> ls){
        Collections.sort(ls , (o1,o2) -> o1.getDate().compareTo(o2.getDate()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsAdapter(getBaseActivity(),ls,empty);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
    }
}