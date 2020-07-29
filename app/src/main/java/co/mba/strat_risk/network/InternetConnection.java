package co.mba.strat_risk.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.IntRange;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InternetConnection {

    private static final String TAG = "Internet Status";

    @Inject
    public InternetConnection() {
    }

    @IntRange(from = 0, to = 3)
    public static Integer isConnected(Context context) {
        int result = 0;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (manager != null) {
                NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2;
                        Log.e(TAG, "Wifi");
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1;
                        Log.e(TAG, "CELLULAR");
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                        result = 3;
                        Log.e(TAG, "VPN");
                    }
                } else {
                    Log.e(TAG, "Not Connected");
                }
            }
        }
        return result;
    }

    public static boolean isAirplaneMode(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}


