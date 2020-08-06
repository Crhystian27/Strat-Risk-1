package co.mba.strat_risk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.base.BaseFragment;
import co.mba.strat_risk.ui.MainActivity;

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


    /**
     * Verifica si hay actividades en cola y cierra la actividad actual,
     * de no haber actividades en cola envia a la actividad principal MainActivity.class
     *
     * @param context - actividad actual
     */
    /*public static void closeActivity(BaseActivity context){
        if (activitiesInQueue() > 1){
            context.finish();
        } else {
            ChangeActivity.animLeftRight(context, MainActivity.class);
        }
    }*/


    //TODO ENVIAR UN BUNDLE AL FRAGMENT
    public static void loadFragment(FragmentActivity context, BaseFragment fragment, Integer value, String TAG) {
        FragmentManager manager = context.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right, android.R.anim.slide_in_left);
        transaction.replace(value, fragment);
        transaction.addToBackStack(TAG);
        transaction.commit();
    }

    public static String getTagFragment(FragmentActivity activity) {
        int index = activity.getSupportFragmentManager().getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry backEntry = activity.getSupportFragmentManager().getBackStackEntryAt(index);
        return backEntry.getName();
    }

    /*public static String getTagFragment(FragmentActivity context){
        int index = context.getSupportFragmentManager().getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry backEntry = context.getSupportFragmentManager().getBackStackEntryAt(index);
        return backEntry.getName();
    }*/

    public static void OpenSendEmail(Activity activity, String email) {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        Log.e(String.valueOf(activity.getClass()), " " + Arrays.toString(Constants.EXTRA_EMAIL));
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{Arrays.toString(Constants.EXTRA_EMAIL)});
        i.putExtra(Intent.EXTRA_SUBJECT, activity.getResources().getString(R.string.extra_subject));
        i.putExtra(Intent.EXTRA_TEXT, email);
        try {
            activity.startActivity(Intent.createChooser(i, activity.getResources().getString(R.string.send_mail)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, activity.getResources().getString(R.string.msj_there_are_no_email_clients_installed_), Toast.LENGTH_SHORT).show();
        }
    }

    public static void getBitmap(Context context, ImageView image) {
        Drawable drawable = image.getDrawable();
        Bitmap myBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(myBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        Bitmap newBitmap = addGradient(context, myBitmap);
        image.setImageDrawable(new BitmapDrawable(context.getResources(), newBitmap));
    }

    private static Bitmap addGradient(Context context, Bitmap originalBitmap) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        Bitmap updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(updatedBitmap);

        canvas.drawBitmap(originalBitmap, 0, 0, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(-20, -20, 90, height, context.getColor(R.color.gradient_primary),
                context.getColor(R.color.gradient_accent), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawRect(0, 0, width, height, paint);

        return updatedBitmap;
    }

}
