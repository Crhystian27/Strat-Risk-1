package co.mba.strat_risk.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;

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
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dto_extra = extras.getString(Constants.EXTRA_NEWS);
        }
        dto = new Gson().fromJson(dto_extra, News.class);
        initUI();
    }


    private void initUI() {
        Utilities.loadFragment(NewsDetailActivity.this, new NewsDetailFragment(), R.id.news_detail_fragment, Constants.TAG_NEWS_DETAIL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}