package co.mba.strat_risk.ui;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;

import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;

@SuppressLint("SetJavaScriptEnabled")
public class NewsDetailActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;
    NewsDetailViewModel viewModel;
    RelativeLayout layout;
    RelativeLayout relativeLayout;

    News dto;
    String dto_extra;
    WebView webView;

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

        ImageView imgDetail = findViewById(R.id.imageDetail);
        imgDetail.setImageResource(R.drawable.ic_mba_gris);
        LinearLayout bottomInteresting = findViewById(R.id.detailInteresting);
        LinearLayout bottomRisk = findViewById(R.id.detailRisk);
        LinearLayout bottomOpportunity = findViewById(R.id.detailOpportunity);

        FloatingActionButton buttonO = findViewById(R.id.selectionOpportunity);
        FloatingActionButton buttonI = findViewById(R.id.selectionInteresting);
        FloatingActionButton buttonR = findViewById(R.id.selectionRisk);
        FloatingActionButton remove = findViewById(R.id.fab_remove);

        viewModel = ViewModelProviders.of(NewsDetailActivity.this, factory).get(NewsDetailViewModel.class);

        layout = findViewById(R.id.detail_Remove);
        relativeLayout = findViewById(R.id.containerDetail);
        webView = findViewById(R.id.webView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dto_extra = extras.getString(Constants.EXTRA_NEWS);
        }
        dto = new Gson().fromJson(dto_extra, News.class);

        webView.setWebViewClient(new Browser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(dto.getLink());

        /*layoutNews.setOnClickListener(v -> {
            SnackBarInformation.showSnackBar(this, layout, "Link", "fonts/montserrat_regular_.ttf");
        });*/

        switch (dto.getStatus()) {
            case 0:

                getToolbar().setTitle(getString(R.string.app_name));
                bottomOpportunity.setVisibility(View.VISIBLE);
                bottomInteresting.setVisibility(View.VISIBLE);
                bottomRisk.setVisibility(View.VISIBLE);


                buttonO.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.OPPORTUNITY_STATUS, layout, getString(R.string.snackBar_opportunity)));
                buttonI.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.INTERESTING_STATUS, layout, getString(R.string.snackBar_interesting)));
                buttonR.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.RISK_STATUS, layout, getString(R.string.snackBar_risk)));
                remove.setOnClickListener(v -> viewModel.removeNews(NewsDetailActivity.this, dto, Constants.DELETE_STATUS));

                break;
            case 1:

                getToolbar().setTitle(getString(R.string.app_name));
                bottomOpportunity.setVisibility(View.VISIBLE);
                bottomInteresting.setVisibility(View.VISIBLE);
                bottomRisk.setVisibility(View.VISIBLE);

                buttonI.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.INTERESTING_STATUS, layout, getString(R.string.snackBar_interesting)));
                buttonR.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.RISK_STATUS, layout, getString(R.string.snackBar_risk)));
                remove.setOnClickListener(v -> viewModel.removeNews(NewsDetailActivity.this, dto, Constants.DELETE_STATUS));

                break;
            case 2:

                getToolbar().setTitle(getString(R.string.app_name));
                bottomOpportunity.setVisibility(View.VISIBLE);
                bottomInteresting.setVisibility(View.VISIBLE);
                bottomRisk.setVisibility(View.VISIBLE);

                buttonO.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.OPPORTUNITY_STATUS, layout, getString(R.string.snackBar_opportunity)));
                buttonI.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.INTERESTING_STATUS, layout, getString(R.string.snackBar_interesting)));
                remove.setOnClickListener(v -> viewModel.removeNews(NewsDetailActivity.this, dto, Constants.DELETE_STATUS));


                break;
            case 3:

                getToolbar().setTitle(getString(R.string.app_name));
                bottomOpportunity.setVisibility(View.VISIBLE);
                bottomInteresting.setVisibility(View.VISIBLE);
                bottomRisk.setVisibility(View.VISIBLE);
                bottomInteresting.setEnabled(false);


                buttonO.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.OPPORTUNITY_STATUS, layout, getString(R.string.snackBar_opportunity)));
                buttonR.setOnClickListener(v -> viewModel.addNewsDB(NewsDetailActivity.this, dto, Constants.RISK_STATUS, layout, getString(R.string.snackBar_risk)));
                remove.setOnClickListener(v -> viewModel.removeNews(NewsDetailActivity.this, dto, Constants.DELETE_STATUS));

                break;
        }

    }

    private static class Browser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            view.clearCache(true);
            return true;
        }
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