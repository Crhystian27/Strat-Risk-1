package co.mba.strat_risk.ui;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;

import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.ui.detail.NewsDetailFragment;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;

public class NewsDetailActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;
    NewsDetailViewModel viewModel;
    FloatingActionButton buttonRemove;
    RelativeLayout layout;

    News dto;
    String dto_extra;

    @Override
    protected int toolbarId() {
        return 0;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Utilities.loadFragment(NewsDetailActivity.this, new NewsDetailFragment(), R.id.news_detail_fragment, Constants.TAG_NEWS_DETAIL);
        }

        viewModel = ViewModelProviders.of(NewsDetailActivity.this, factory).get(NewsDetailViewModel.class);

        layout = findViewById(R.id.detail_Remove);
        buttonRemove = findViewById(R.id.fab_remove);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dto_extra = extras.getString(Constants.EXTRA_NEWS);
        }
        dto = new Gson().fromJson(dto_extra, News.class);

        buttonRemove.setOnClickListener(view -> {
            viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.DELETE_STATUS, layout, getString(R.string.snackBar_remove) );
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


}