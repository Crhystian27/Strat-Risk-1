package co.mba.strat_risk.network;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.IntRange;

import co.mba.strat_risk.R;
import co.mba.strat_risk.widgets.DialogInformation;


public class InternetConnection {

    private static final String TAG = "Internet Status";

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

    public static boolean getAirPlaneMode(Activity activity) {
        if (isAirplaneMode(activity) && !DialogInformation.isShowing) {
            Log.e(TAG, activity.getString(R.string.dialog_airplane_mode));
            DialogInformation.showDialog(activity, activity.getString(R.string.dialog_airplane_mode), 0, null);
        }
        return false;
    }

    public static boolean getConnection(Activity activity) {
        if (isConnected(activity) == 0 && !DialogInformation.isShowing) {
            Log.e(TAG, activity.getString(R.string.dialog_no_internet_connection));
            DialogInformation.showDialog(activity, activity.getString(R.string.dialog_no_internet_connection), 0, null);
        }
        return false;
    }

}


