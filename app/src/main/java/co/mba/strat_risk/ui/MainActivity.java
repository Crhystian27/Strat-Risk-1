package co.mba.strat_risk.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;


public class MainActivity extends BaseActivity {


    MainActivityViewModel viewModel;
    BottomNavigationView bottomNavigationView;


    @Override
    protected int toolbarId() {
        return R.id.main_toolbar;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        initUI();
        setSupportActionBar(true, true);
    }

    private void initUI() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_news, R.id.navigation_opportunity, R.id.navigation_interesting, R.id.navigation_risk)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_news:

                return true;
            case R.id.navigation_opportunity:
                counterNews(this, 1);
                return true;
            case R.id.navigation_interesting:
                counterNews(this, 2);
                return true;
            case R.id.navigation_risk:
                counterNews(this, 3);
                return true;
        }
        return false;
    };

    private void counterNews(Context context, Integer idStatus) {
        viewModel.getNews(idStatus).observe(this, news -> {

        });
        viewModel.getNewsDTO(context).observe(this, newsDTOS -> {

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}