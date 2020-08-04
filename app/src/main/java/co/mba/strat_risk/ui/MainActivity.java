package co.mba.strat_risk.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
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
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    ViewModelProvider.Factory factory;
    MainViewModel viewModel;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    View viewDrawer;


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

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        viewDrawer = navigationView.getHeaderView(0);

        ImageView nv_image = viewDrawer.findViewById(R.id.nv_image);
        TextView nv_name = viewDrawer.findViewById(R.id.nv_name);
        TextView nv_email = viewDrawer.findViewById(R.id.nv_email);
        navigationView.setNavigationItemSelectedListener(this);

        initUI();
        setSupportActionBar(true, true);
    }


    @Override
    public void onBackPressed() {
        drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initUI() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // Passing each menu ID as a set of Ids because each

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        Utilities.loadFragment(MainActivity.this, new NewsFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_news:
                Utilities.loadFragment(MainActivity.this, new NewsFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);
                break;
            case R.id.navigation_opportunity:
                //counterNews(this, Constants.OPPORTUNITY_STATUS);
                Utilities.loadFragment(MainActivity.this, new OpportunityFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);
                break;
            case R.id.navigation_interesting:
                //counterNews(this, Constants.INTERESTING_STATUS);
                Utilities.loadFragment(MainActivity.this, new InterestingFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);
                break;
            case R.id.navigation_risk:
                //counterNews(this, Constants.RISK_STATUS);
                Utilities.loadFragment(MainActivity.this, new RiskFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);
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
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case R.id.item_safe:

                Toast.makeText(this, "safe", Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;

            case R.id.item_question:

                Toast.makeText(this, "question", Toast.LENGTH_SHORT).show();


                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.item_log_in_out:

                Toast.makeText(this, "log in out", Toast.LENGTH_SHORT).show();

                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            default:
                return true;
        }
    }
}