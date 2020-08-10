package co.mba.strat_risk.base;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;


import co.mba.strat_risk.R;
import co.mba.strat_risk.network.InternetConnection;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;
import co.mba.strat_risk.widgets.DialogInformation;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private Toolbar toolbar;
    private final int FAQ_ID = 0;
    private final int INFORMATION_ID = 1;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        setToolbar(null);
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

    /**
     * Set Support ActionBar
     *
     * @param displayHome  si es true indica que regresa un nivel en la UI
     * @param showIconHome muestra el icono en el toolbar
     */
    public void setSupportActionBar(boolean displayHome, boolean showIconHome) {
        if (toolbar != null) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(displayHome);
            getSupportActionBar().setDisplayShowHomeEnabled(showIconHome);
        }
    }

    /**
     *
     * @return Regresa al frgament anterior con el boton superior
     */

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

        /*if (InternetConnection.isAirplaneMode(this) && !DialogInformation.isShowing) {
            Log.e(getClass().getSimpleName(), getString(R.string.dialog_airplane_mode));
            DialogInformation.showDialog(BaseActivity.this, getString(R.string.dialog_airplane_mode), 0, null);
        } else if (InternetConnection.isConnected(this) == 0 && !DialogInformation.isShowing) {
            Log.e(getClass().getSimpleName(), getString(R.string.dialog_no_internet_connection));
            DialogInformation.showDialog(BaseActivity.this, getString(R.string.dialog_no_internet_connection), 0, null);
        }*/

        InternetConnection.getAirPlaneMode(this);
        InternetConnection.getConnection(this);

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
        if (!pressMenuItem) {
            switch (item.getItemId()) {
                case INFORMATION_ID:
                    pressMenuItem = true;
                    Utilities.OpenSendEmail(BaseActivity.this, null);
                    new Handler().postDelayed(() -> pressMenuItem = false, Constants.DELAY_BUTTON_PREES);
                    break;
                case FAQ_ID:
                    pressMenuItem = true;
                    Log.e(getClass().getSimpleName(), getResources().getString(R.string.base_faq));
                    Toast.makeText(this, getResources().getString(R.string.base_faq), Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(() -> pressMenuItem = false, Constants.DELAY_BUTTON_PREES);
                    break;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Elimina la actividad actual de la lista.
        Utilities.removeThisActivityFromRunningActivities(getClass());
    }
}
