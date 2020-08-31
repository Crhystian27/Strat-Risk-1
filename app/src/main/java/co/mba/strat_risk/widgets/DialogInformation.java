package co.mba.strat_risk.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.checkbox.MaterialCheckBox;

import co.mba.strat_risk.R;
import co.mba.strat_risk.util.Utilities;

/**
 * @Author Cristian David Soto
 */

public class DialogInformation {


    public static boolean isShowing = false;

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("InflateParams")
    public static void showDialog(Activity activity, String message, Integer status, MaterialCheckBox materialCheckBox) {
        final Dialog dialog = new Dialog(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.item_dialog_information, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(false);

        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        TextView text_accept = dialog.findViewById(R.id.txt_dialog_accept);
        TextView text_cancel = dialog.findViewById(R.id.txt_dialog_cancel);
        TextView text_message = dialog.findViewById(R.id.txt_dialog_message_title);
        RadioGroup radioGroup = dialog.findViewById(R.id.dialog_radio_group);
        ImageView img = dialog.findViewById(R.id.dialog_img);
        Drawable drawable = activity.getDrawable(R.drawable.ic_rss);

        RadioButton radio1 = dialog.findViewById(R.id.dialog_radio_button1);
        RadioButton radio2 = dialog.findViewById(R.id.dialog_radio_button2);

        Utilities.getBitmap(activity, img);

        Glide.with(activity.getApplicationContext())
                .applyDefaultRequestOptions(RequestOptions.placeholderOf(R.drawable.ic_rss).error(R.drawable.ic_rss).circleCrop())
                .load(drawable)
                .into(img);

        switch (status) {
            case 0:
                dialog.getWindow().setLayout((39 * width) / 40, (4 * height) / 5);

                text_message.setVerticalScrollBarEnabled(false);
                radioGroup.setVisibility(View.GONE);
                text_message.setLines(2);
                text_message.setText(message);
                text_cancel.setVisibility(View.INVISIBLE);
                break;
            case 1:
                dialog.getWindow().setLayout((39 * width) / 40, (4 * height) / 5);

                text_message.setVerticalScrollBarEnabled(true);
                text_message.setLines(16);
                radioGroup.setVisibility(View.VISIBLE);
                text_cancel.setVisibility(View.VISIBLE);
                dialog.setTitle(message);
                text_message.setText(activity.getString(R.string.string_policy_private));
                radio1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_check));
                radio2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_uncheck));
                radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                    switch (checkedId) {
                        case R.id.dialog_radio_button1:
                            text_message.setText(activity.getString(R.string.string_policy_private));
                            radio1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_check));
                            radio2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_uncheck));

                            break;
                        case R.id.dialog_radio_button2:
                            text_message.setText(activity.getString(R.string.string_policy_service));
                            radio1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_uncheck));
                            radio2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_check));
                            break;
                    }
                });
                break;
        }

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        text_cancel.setOnClickListener(v -> {
            isShowing = false;
            materialCheckBox.setChecked(false);
            dialog.dismiss();
        });

        text_accept.setOnClickListener(v -> {
            isShowing = false;
            if (materialCheckBox != null) {
                materialCheckBox.setChecked(true);
            }
            dialog.dismiss();
        });
        isShowing = true;
        dialog.show();
    }
}
