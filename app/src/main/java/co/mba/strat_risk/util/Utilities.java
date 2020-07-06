package co.mba.strat_risk.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import co.mba.strat_risk.R;

public class Utilities {

    public static ArrayList<Class> runningActivities = new ArrayList<>();

    /* <ACTIVITY_STATUS> */
    public static void addThisActivityToRunningActivityies(Class cls) {
        if (!runningActivities.contains(cls)) runningActivities.add(cls);
    }

    public static void removeThisActivityFromRunningActivities(Class cls) {
        runningActivities.remove(cls);
    }

    public static boolean isActivityInBackStack(Class cls) {
        return runningActivities.contains(cls);
    }

    public static int activitiesInQueue() {
        return runningActivities.size();
    }

    public static void OpenSendEmail(Context context) {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        Log.e(String.valueOf(context.getClass()), " " + Arrays.toString(Constants.EXTRA_EMAIL));
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{Arrays.toString(Constants.EXTRA_EMAIL)});
        i.putExtra(Intent.EXTRA_SUBJECT, context.getResources().getString(R.string.btn_contact));
        try {
            context.startActivity(Intent.createChooser(i, context.getResources().getString(R.string.send_mail)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, context.getResources().getString(R.string.msj_there_are_no_email_clients_installed_), Toast.LENGTH_SHORT).show();
        }

    }
}
