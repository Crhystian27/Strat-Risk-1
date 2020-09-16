package co.mba.strat_risk.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    private TextView textViewTitle, textViewBody;
    private ImageView imgDetail, bottomRisk, bottomInteresting, bottomOpportunity;

    @Override
    protected int toolbarId() {
        return R.id.toolbar_detail;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_news_detail_custom;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Utilities.loadFragment(NewsDetailActivity.this, new NewsDetailFragment(), R.id.news_detail_fragment, Constants.TAG_NEWS_DETAIL);

        textViewTitle = findViewById(R.id.title_detail);
        textViewBody = findViewById(R.id.body_detail);
        imgDetail = findViewById(R.id.imageDetail);
        bottomInteresting = findViewById(R.id.detailInteresting);
        bottomRisk = findViewById(R.id.detailRisk);
        bottomOpportunity = findViewById(R.id.detailOpportunity);


        viewModel = ViewModelProviders.of(NewsDetailActivity.this, factory).get(NewsDetailViewModel.class);

        layout = findViewById(R.id.detail_Remove);
        buttonRemove = findViewById(R.id.fab_remove);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dto_extra = extras.getString(Constants.EXTRA_NEWS);
        }
        dto = new Gson().fromJson(dto_extra, News.class);

        buttonRemove.setOnClickListener(view -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.DELETE_STATUS, layout, getString(R.string.snackBar_remove)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}