package co.mba.strat_risk.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

import co.mba.strat_risk.R;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.util.Utilities;
import co.mba.strat_risk.widgets.DialogInformation;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {


    private Toolbar toolbar;
    private final int FAQ_ID = 1;
    private final int INFORMATION_ID = 2;

    @LayoutRes
    protected abstract int layoutRes();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
    }

    public void setToolbar(@Nullable Toolbar toolbar) {
        if (toolbar == null) {
            this.toolbar = findViewById(toolbarId());
        }
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setSupportActionBar(boolean displayHome, boolean showIconHome) {
        if (toolbar != null) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(displayHome);
            getSupportActionBar().setDisplayShowHomeEnabled(showIconHome);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (toolbar != null)
            finish();
        return false;
    }

    protected abstract int toolbarId();

    @Override
    protected void onResume() {
        super.onResume();

        if (InternetConnection.isConnected(this) != 0 && !DialogInformation.isShowing) {
            DialogInformation.showDialog(BaseActivity.this, getString(R.string.dialog_no_internet_connection),null);
        }

        //Agregar la actividad actual a la lista
        Utilities.addThisActivityToRunningActivityies(getClass());
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (toolbar.getMenu().findItem(INFORMATION_ID) == null) {
            toolbar.getMenu().add(0, INFORMATION_ID, Menu.CATEGORY_ALTERNATIVE, getString(R.string.base_info)).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        }
        if (toolbar.getMenu().findItem(FAQ_ID) == null) {
            toolbar.getMenu().add(0, FAQ_ID, Menu.CATEGORY_ALTERNATIVE, getString(R.string.base_faq)).setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private boolean pressMenuItem = false;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Elimina la actividad actual de la lista.
        Utilities.removeThisActivityFromRunningActivities(getClass());
    }
}
