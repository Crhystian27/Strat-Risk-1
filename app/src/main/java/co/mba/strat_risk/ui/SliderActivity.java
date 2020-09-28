package co.mba.strat_risk.ui;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.adapter.SliderAdapter;
import co.mba.strat_risk.base.BaseActivity;


public class SliderActivity extends BaseActivity {

    @Inject
    ViewModelProvider.Factory factory;

    private SliderViewModel viewModel;
    private RelativeLayout layout;

    @Override

    protected int layoutRes() {
        return R.layout.activity_slider;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(SliderActivity.this, factory).get(SliderViewModel.class);
        initUI();
    }

    @Override
    protected int toolbarId() {
        return 0;
    }

    private void initUI() {
        hideSystemUI();
        layout = findViewById(R.id.relativeSlider);
        LinearLayout linearStart = findViewById(R.id.slider_button);
        ViewPager viewPager = findViewById(R.id.sliderViewPager);
        viewPager.setAdapter(new SliderAdapter(this));
        addIndicator(0);
        viewPager.addOnPageChangeListener(changeListener);
        linearStart.setOnClickListener(v -> {

            if (viewModel.isExistData()) {
                viewModel.initSlider(SliderActivity.this, MenuActivity.class);
            } else {
                viewModel.initSlider(SliderActivity.this, LoginActivity.class);
            }
        });
    }

    private void addIndicator(Integer position) {
        TextView[] dots = new TextView[1];

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorPrimaryLight));

            layout.addView(dots[i]);
        }

        if (position < 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryLight));
        }
    }


    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}