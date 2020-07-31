package co.mba.strat_risk.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.ui.interesting.InterestingFragment;
import co.mba.strat_risk.ui.news.NewsFragment;
import co.mba.strat_risk.ui.opportunity.OpportunityFragment;
import co.mba.strat_risk.ui.risk.RiskFragment;
import co.mba.strat_risk.util.Utilities;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    ViewModelProvider.Factory factory;
    MainViewModel viewModel;
    BottomNavigationView bottomNavigationView;


    @Override
    protected int toolbarId() {
        return R.id.main_toolbar;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main_navigationview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(MainActivity.this, factory).get(MainViewModel.class);

        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_news, R.id.navigation_opportunity, R.id.navigation_interesting, R.id.navigation_risk)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);*/

        initUI();
        setSupportActionBar(true, true);
    }

    private void initUI() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // Passing each menu ID as a set of Ids because each


        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        Utilities.loadFragment(MainActivity.this, new NewsFragment(), R.id.nav_host_fragment);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_news:
                Utilities.loadFragment(MainActivity.this, new NewsFragment(), R.id.nav_host_fragment);
                break;
            case R.id.navigation_opportunity:
                //counterNews(this, Constants.OPPORTUNITY_STATUS);
                Utilities.loadFragment(MainActivity.this, new OpportunityFragment(), R.id.nav_host_fragment);
                break;
            case R.id.navigation_interesting:
                //counterNews(this, Constants.INTERESTING_STATUS);
                Utilities.loadFragment(MainActivity.this, new InterestingFragment(), R.id.nav_host_fragment);
                break;
            case R.id.navigation_risk:
                //counterNews(this, Constants.RISK_STATUS);
                Utilities.loadFragment(MainActivity.this, new RiskFragment(), R.id.nav_host_fragment);
                break;
        }
        return true;
    };

    private void counterNews(Context context, Integer idStatus) {
        /*viewModel.getNews(idStatus).observe(this, news -> {
            String a = String.valueOf(news.size());

        });*/

        //viewModel.getNewsDTO(context).observe(this, newsDTOS -> {

        //});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    //TODO change menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case R.id.item_account:
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            case R.id.item_log_in_out:
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            default:
                return true;
        }
    }
}