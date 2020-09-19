package co.mba.strat_risk.ui;


import android.os.Bundle;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;

public class StartActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_start;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int toolbarId() {
        return 0;
    }
}