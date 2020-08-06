package co.mba.strat_risk.widgets;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import co.mba.strat_risk.R;

/**
 * @Author Cristian David Soto
 */

public class SnackBarInformation {

    public static void showSnackBar(Activity activity, RelativeLayout layout, String message, String font) {

        final Snackbar snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_INDEFINITE);
        TextView textView = (snackbar.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(), font);
        textView.setTypeface(typeface);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextSize(16);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        snackbar.setTextColor(activity.getColor(R.color.textWhite))
                .setDuration(1400).show();

    }
}
