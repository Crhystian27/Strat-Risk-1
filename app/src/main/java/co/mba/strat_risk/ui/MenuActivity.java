package co.mba.strat_risk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;

public class MenuActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;

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
        imageMenuNews = findViewById(R.id.menuNews);
        imageMenuDetail = findViewById(R.id.menuDetail);

        imageMenuNews.setOnClickListener(v -> {

            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
        });

        imageMenuDetail.setOnClickListener(v -> {
            //TODO crear undialogo.
        });

        //Load in database


    }


}