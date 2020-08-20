package co.mba.strat_risk.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
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
import co.mba.strat_risk.widgets.SnackBarInformation;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    ViewModelProvider.Factory factory;
    MainViewModel viewModel;
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    View viewDrawer;
    View notificationBadge;


    RelativeLayout layout;
    private boolean recentlyBackPressed = false;
    private Runnable exitRunnable = () -> recentlyBackPressed = false;
    private Handler exitHandler = new Handler();


    @Override
    protected int toolbarId() {
        return R.id.toolbar_main;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main_navigationview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = findViewById(R.id.main_relative);

        viewModel = ViewModelProviders.of(MainActivity.this, factory).get(MainViewModel.class);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        viewDrawer = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        ImageView nv_image = viewDrawer.findViewById(R.id.nv_image);
        TextView nv_name = viewDrawer.findViewById(R.id.nv_name);
        TextView nv_email = viewDrawer.findViewById(R.id.nv_email);

        Glide.with(MainActivity.this)
                .applyDefaultRequestOptions(RequestOptions.placeholderOf(R.drawable.ic_notifications_black_24dp).error(R.drawable.ic_notifications_black_24dp))
                .load(R.drawable.ic_notifications_black_24dp)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        nv_image.setBackground(getDrawable(R.drawable.background_icon_navigationview));
                    }
                });

        viewModel.getCurrentUser();
        viewModel.getUser().observe(this, user -> {
            nv_name.setText(user.getName());
            nv_email.setText(user.getEmail());
        });


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        Utilities.loadFragment(MainActivity.this, new NewsFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);
        addBadgeView(Constants.LOCAL_STATUS);

        setSupportActionBar(false, true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void addBadgeView(Integer value) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(value);
        notificationBadge = LayoutInflater.from(this).inflate(R.layout.notification_badge, menuView, false);
        itemView.addView(notificationBadge);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            System.out.println("FRAGMENT_TAG -> " + Utilities.getTagFragment(MainActivity.this));

            if (Constants.TAG_MAIN.equals(Utilities.getTagFragment(MainActivity.this))) {
                if (recentlyBackPressed) {
                    exitHandler.removeCallbacks(exitRunnable);
                    recentlyBackPressed = false;
                    moveTaskToBack(true);
                    finish();

                } else {
                    recentlyBackPressed = true;

                    SnackBarInformation.showSnackBar(MainActivity.this, layout, getString(R.string.press_again_to_exit), "fonts/montserrat_regular_.ttf");
                    exitHandler.postDelayed(exitRunnable, 2000L);
                }
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }


        drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_news:
                Utilities.loadFragment(MainActivity.this, new NewsFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);
                notificationBadge.setVisibility(View.GONE);
                addBadgeView(Constants.LOCAL_STATUS);
                break;
            case R.id.navigation_opportunity:
                Utilities.loadFragment(MainActivity.this, new OpportunityFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);
                notificationBadge.setVisibility(View.GONE);
                addBadgeView(Constants.OPPORTUNITY_STATUS);
                break;
            case R.id.navigation_interesting:
                Utilities.loadFragment(MainActivity.this, new InterestingFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);
                notificationBadge.setVisibility(View.GONE);
                addBadgeView(Constants.INTERESTING_STATUS);
                break;
            case R.id.navigation_risk:
                Utilities.loadFragment(MainActivity.this, new RiskFragment(), R.id.nav_host_fragment, Constants.TAG_MAIN);
                notificationBadge.setVisibility(View.GONE);
                addBadgeView(Constants.RISK_STATUS);
                break;
        }
        return true;
    };


    private void newsCounter(Integer id) {
        viewModel.initNews(id);
        viewModel.getLocalNews().observe(this, news -> {
            Integer count = news.size();
            //getToolbar().setTitle(count);
        });


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