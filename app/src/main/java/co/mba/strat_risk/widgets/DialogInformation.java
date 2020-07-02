package co.mba.strat_risk.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import co.mba.strat_risk.R;

/**
 * @Author Cristian David Soto
 */

public class DialogInformation {


    public static boolean isShowing = false;

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("InflateParams")
    public static void showDialog(Activity activity, String message, View.OnClickListener listener) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setCancelable(false);
        Rect displayRectangle = new Rect();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.item_dialog_information, null);
        layout.setMinimumWidth((int) (displayRectangle.width() * 0.8f));

        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final TextView textView = dialog.findViewById(R.id.txt_dialog_message_title);
        textView.setText(message);

        dialog.findViewById(R.id.txt_dialog_accept).setOnClickListener(v -> {
            isShowing = false;
            dialog.dismiss();
            if (listener != null) {
                listener.onClick(v);
            }

            isShowing = true;
            dialog.show();
        });

    }
}
