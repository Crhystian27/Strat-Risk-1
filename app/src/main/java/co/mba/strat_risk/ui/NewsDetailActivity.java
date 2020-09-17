package co.mba.strat_risk.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;

import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;
import co.mba.strat_risk.widgets.SnackBarInformation;

public class NewsDetailActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;
    NewsDetailViewModel viewModel;
    RelativeLayout layout;
    LinearLayout layoutNews;
    BottomAppBar bottomAppBar;


    News dto;
    String dto_extra;

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

        setSupportActionBar(true, true);

        TextView textViewTitle = findViewById(R.id.title_detail);
        TextView textViewBody = findViewById(R.id.body_detail);
        FloatingActionButton buttonRemove = findViewById(R.id.fab_remove);

        //TODO SHOW IMAGE WITH GLIDE
        ImageView imgDetail = findViewById(R.id.imageDetail);
        LinearLayout bottomInteresting = findViewById(R.id.detailInteresting);
        LinearLayout bottomRisk = findViewById(R.id.detailRisk);
        LinearLayout bottomOpportunity = findViewById(R.id.detailOpportunity);

        FloatingActionButton buttonO = findViewById(R.id.selectionOpportunity);
        FloatingActionButton buttonI = findViewById(R.id.selectionInteresting);
        FloatingActionButton buttonR = findViewById(R.id.selectionRisk);


        viewModel = ViewModelProviders.of(NewsDetailActivity.this, factory).get(NewsDetailViewModel.class);

        layout = findViewById(R.id.detail_Remove);
        layoutNews = findViewById(R.id.newsLink);
        bottomAppBar = findViewById(R.id.bottom_app_bar_detail);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dto_extra = extras.getString(Constants.EXTRA_NEWS);
        }
        dto = new Gson().fromJson(dto_extra, News.class);

        layoutNews.setOnClickListener(v -> {
            SnackBarInformation.showSnackBar(this, layout, "Link", "fonts/montserrat_regular_.ttf");
        });

        switch (dto.getStatus()) {

            case 0:

                getToolbar().setTitle(getString(R.string.app_name));
                bottomOpportunity.setVisibility(View.VISIBLE);
                bottomInteresting.setVisibility(View.VISIBLE);
                bottomRisk.setVisibility(View.VISIBLE);

                bottomAppBar.setBackgroundTint(ColorStateList.valueOf(getColor(R.color.colorPrimaryLight)));

                buttonO.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.OPPORTUNITY_STATUS, layout, getString(R.string.snackBar_opportunity)));
                buttonI.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.INTERESTING_STATUS, layout, getString(R.string.snackBar_interesting)));
                buttonR.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.RISK_STATUS, layout, getString(R.string.snackBar_risk)));
                buttonRemove.setOnClickListener(v -> {
                    viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.DELETE_STATUS, layout, getString(R.string.snackBar_remove));
                    finish();
                });
                break;
            case 1:

                getToolbar().setTitle(getString(R.string.app_name));
                bottomOpportunity.setVisibility(View.GONE);
                bottomInteresting.setVisibility(View.VISIBLE);
                bottomRisk.setVisibility(View.VISIBLE);
                bottomAppBar.setBackgroundTint(ColorStateList.valueOf(getColor(R.color.colorOpportunity)));

                buttonI.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.INTERESTING_STATUS, layout, getString(R.string.snackBar_interesting)));
                buttonR.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.RISK_STATUS, layout, getString(R.string.snackBar_risk)));
                buttonRemove.setOnClickListener(v -> {
                    viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.DELETE_STATUS, layout, getString(R.string.snackBar_remove));
                    finish();
                });
                break;
            case 2:

                getToolbar().setTitle(getString(R.string.app_name));
                bottomOpportunity.setVisibility(View.VISIBLE);
                bottomInteresting.setVisibility(View.GONE);
                bottomRisk.setVisibility(View.VISIBLE);

                bottomAppBar.setBackgroundTint(ColorStateList.valueOf(getColor(R.color.colorInteresting)));

                buttonO.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.OPPORTUNITY_STATUS, layout, getString(R.string.snackBar_opportunity)));
                buttonR.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.RISK_STATUS, layout, getString(R.string.snackBar_risk)));
                buttonRemove.setOnClickListener(v -> {
                    viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.DELETE_STATUS, layout, getString(R.string.snackBar_remove));
                    finish();
                });
                break;
            case 3:

                getToolbar().setTitle(getString(R.string.app_name));
                bottomOpportunity.setVisibility(View.VISIBLE);
                bottomInteresting.setVisibility(View.VISIBLE);
                bottomRisk.setVisibility(View.GONE);
                bottomAppBar.setBackgroundTint(ColorStateList.valueOf(getColor(R.color.colorRisk)));

                buttonO.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.OPPORTUNITY_STATUS, layout, getString(R.string.snackBar_opportunity)));
                buttonI.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.INTERESTING_STATUS, layout, getString(R.string.snackBar_interesting)));
                buttonRemove.setOnClickListener(v -> {
                    viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.DELETE_STATUS, layout, getString(R.string.snackBar_remove));
                    finish();
                });
                break;
        }

        textViewTitle.setText(dto.getTitle());
        textViewBody.setText(dto.getSnippet());
        textViewBody.setTextSize(20);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        Utilities.removeThisActivityFromRunningActivities(NewsDetailActivity.this.getClass());
        finish();
        super.onBackPressed();
    }
}