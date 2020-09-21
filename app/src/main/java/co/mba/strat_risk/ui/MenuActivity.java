package co.mba.strat_risk.ui;

import android.os.Bundle;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;

public class MenuActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    protected int toolbarId() {
        return 0;
    }
}