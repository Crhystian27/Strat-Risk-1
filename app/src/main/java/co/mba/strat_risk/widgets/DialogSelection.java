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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.mba.strat_risk.R;
import co.mba.strat_risk.util.Utilities;

/**
 * @Author Cristian David Soto
 */

public class DialogSelection {


    public static boolean isShowing = false;

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("InflateParams")
    public static void showDialog(Activity activity, String tittle, String message, String image, Integer status) {
        final Dialog dialog = new Dialog(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.item_dialog_selection, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(false);

        TextView text_title = dialog.findViewById(R.id.txt_dialog_selection_title);
        text_title.setText(tittle);
        TextView text_message = dialog.findViewById(R.id.txt_dialog_selection_body);
        text_message.setText(message);

        FloatingActionButton button_delete = dialog.findViewById(R.id.floatingDelete);
        FloatingActionButton button_opportunity = dialog.findViewById(R.id.floatingOpportunity);
        FloatingActionButton button_interesting = dialog.findViewById(R.id.floatingInteresting);
        FloatingActionButton button_risk = dialog.findViewById(R.id.floatingRisk);
        ImageView view_image = dialog.findViewById(R.id.dialog_selection_img);

        Drawable drawable = activity.getDrawable(R.drawable.ic_rss);

        if (image == null) {
            Utilities.getBitmap(activity, view_image);
            Glide.with(activity.getApplicationContext())
                    .applyDefaultRequestOptions(RequestOptions.placeholderOf(R.drawable.ic_rss).error(R.drawable.ic_rss).circleCrop())
                    .load(drawable)
                    .into(view_image);
        } else {
            Glide.with(activity.getApplicationContext())
                    .applyDefaultRequestOptions(RequestOptions.placeholderOf(R.drawable.ic_rss).error(R.drawable.ic_rss).circleCrop())
                    .load(image)
                    .into(view_image);
        }


        switch (status) {
            case 0:
                text_title.setTextColor(activity.getColor(R.color.colorPrimaryLight));
                break;
            case 1:
                text_title.setTextColor(activity.getColor(R.color.colorOpportunity));
                break;
            case 2:
                text_title.setTextColor(activity.getColor(R.color.colorInteresting));
                break;
            case 3:
                text_title.setTextColor(activity.getColor(R.color.colorRisk));
                break;
        }


        isShowing = true;
        dialog.show();
    }
}
