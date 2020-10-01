package co.mba.strat_risk.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
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
import co.mba.strat_risk.ui.TermsActivity;

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
        //int height = metrics.heightPixels;

        TextView text_accept = dialog.findViewById(R.id.txt_dialog_accept);
        TextView text_more = dialog.findViewById(R.id.more);
        TextView text_cancel = dialog.findViewById(R.id.txt_dialog_cancel);
        TextView text_message = dialog.findViewById(R.id.txt_dialog_message_title);
        RadioGroup radioGroup = dialog.findViewById(R.id.dialog_radio_group);
        ImageView img = dialog.findViewById(R.id.dialog_img);
        Drawable drawable = activity.getDrawable(R.drawable.ic_gris);

        RadioButton radio1 = dialog.findViewById(R.id.dialog_radio_button1);

        Glide.with(activity.getApplicationContext())
                .applyDefaultRequestOptions(RequestOptions.placeholderOf(drawable).error(drawable).circleCrop())
                .load(drawable)
                .into(img);

        switch (status) {
            case 0:

                text_message.setVisibility(View.VISIBLE);
                text_message.setVerticalScrollBarEnabled(false);
                radioGroup.setVisibility(View.GONE);
                text_message.setLines(2);
                text_more.setVisibility(View.GONE);
                text_message.setText(message);
                text_cancel.setVisibility(View.INVISIBLE);

                break;
            case 1:

                text_message.setVisibility(View.VISIBLE);
                text_message.setText(activity.getString(R.string.string_pfd));
                text_message.setGravity(Gravity.CENTER);
                text_more.setVisibility(View.VISIBLE);
                text_message.setTextSize(16);
                text_more.setTextSize(16);
                text_message.setVerticalScrollBarEnabled(true);
                //text_message.setLines(16);

                text_more.setOnClickListener(v -> {
                    Intent intent = new Intent(activity, TermsActivity.class);
                    activity.startActivity(intent);
                });
                radioGroup.setVisibility(View.VISIBLE);
                text_cancel.setVisibility(View.VISIBLE);
                dialog.setTitle(message);
                //text_message.setText(activity.getString(R.string.string_policy_private));
                radio1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_check));
                //radio2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_uncheck));
                radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                    switch (checkedId) {
                        case R.id.dialog_radio_button1:
                            //text_message.setText(activity.getString(R.string.string_policy_private));

                            radio1.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_check));
                            //radio2.setCompoundDrawablesWithIntrinsicBounds(null, null, null, activity.getDrawable(R.drawable.radiobutton_uncheck));

                            break;
                    }
                });
                break;


        }


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

        if (status.equals(1)) {
            dialog.getWindow().setLayout((36 * width) / 40, ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        dialog.show();
    }
}
