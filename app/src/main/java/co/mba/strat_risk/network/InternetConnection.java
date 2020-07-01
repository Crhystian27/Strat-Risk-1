package co.mba.strat_risk.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;
import android.widget.RelativeLayout;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InternetConnection {

    @Inject
    public InternetConnection() {
    }

    public boolean isConnected(Context context, RelativeLayout layout) {
        ConnectivityManager manager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo info = manager != null ? manager.getActiveNetworkInfo() : null;
        if (info != null && info.isConnectedOrConnecting()) {
            Log.e("Internet Status", "Connected");
            return true;
        }
        Log.e("Internet Status", "Disconnected");
        return false;
    }

    public boolean isAirplaneMode(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}


