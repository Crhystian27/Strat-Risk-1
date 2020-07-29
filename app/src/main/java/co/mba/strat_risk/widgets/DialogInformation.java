package co.mba.strat_risk.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import co.mba.strat_risk.R;
import co.mba.strat_risk.util.Utilities;

/**
 * @Author Cristian David Soto
 */

public class DialogInformation {


    public static boolean isShowing = false;

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("InflateParams")
    public static void showDialog(Activity activity, String message, Integer status) {
        final Dialog dialog = new Dialog(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.item_dialog_information, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(false);

        TextView text_message = dialog.findViewById(R.id.txt_dialog_message_title);
        ImageView img = dialog.findViewById(R.id.dialog_img);
        Drawable drawable = activity.getDrawable(R.drawable.ic_rss);

        Utilities.getBitmap(activity, img);
        Glide.with(activity.getApplicationContext())
                .applyDefaultRequestOptions(RequestOptions.placeholderOf(R.drawable.ic_rss).error(R.drawable.ic_rss).circleCrop())
                .load(drawable)
                .into(img);
        text_message.setText(message);
        TextView text_accept = dialog.findViewById(R.id.txt_dialog_accept);

        if(status.equals(1)){
            dialog.setTitle(message);

        }


        text_accept.setOnClickListener(v -> {
            isShowing = false;
            dialog.dismiss();
        });
        isShowing = true;
        dialog.show();
    }
}
