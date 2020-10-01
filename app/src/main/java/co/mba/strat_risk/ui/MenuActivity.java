package co.mba.strat_risk.ui;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.widgets.DialogInformation;


public class MenuActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;

    MenuViewModel viewModel;
    ImageView imageMenuNews, imageMenuDetail;


    @Override
    protected int toolbarId() {
        return R.id.toolbar_menu;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_menu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(false, true);


        viewModel = ViewModelProviders.of(MenuActivity.this, factory).get(MenuViewModel.class);

        imageMenuNews = findViewById(R.id.menuNews);
        imageMenuDetail = findViewById(R.id.menuDetail);

        imageMenuNews.setOnClickListener(v -> viewModel.initMenu(MenuActivity.this, MainActivity.class));

        imageMenuDetail.setOnClickListener(v -> {
            DialogInformation.showDialog(MenuActivity.this, "Muy Pronto", 0, null);
        });
    }


}