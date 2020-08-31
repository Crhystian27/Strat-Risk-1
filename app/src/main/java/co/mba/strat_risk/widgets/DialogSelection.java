package co.mba.strat_risk.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.SyncStateContract;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.mba.strat_risk.R;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.ui.interesting.InterestingFragmentViewModel;
import co.mba.strat_risk.ui.news.NewsFragmentViewModel;
import co.mba.strat_risk.ui.opportunity.OpportunityFragmentViewModel;
import co.mba.strat_risk.ui.risk.RiskFragmentViewModel;
import co.mba.strat_risk.util.Constants;
import co.mba.strat_risk.util.Utilities;

/**
 * @Author Cristian David Soto
 */

public class DialogSelection {


    public static boolean isShowing = false;

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("InflateParams")
    public static void showDialog(Activity activity, String tittle, String message, String image, Integer status, News news, NewsFragmentViewModel viewModelNews, OpportunityFragmentViewModel viewModelOpportunity, InterestingFragmentViewModel viewModelInteresting, RiskFragmentViewModel viewModelRisk, RelativeLayout layout) {
        final Dialog dialog = new Dialog(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.item_dialog_selection, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        TextView text_title = dialog.findViewById(R.id.txt_dialog_selection_title);
        text_title.setText(tittle);
        TextView text_message = dialog.findViewById(R.id.txt_dialog_selection_body);
        text_message.setText(message);

        TextView button_cancel = dialog.findViewById(R.id.txt_selection_cancel);
        TextView button_delete = dialog.findViewById(R.id.txt_selection_delete);
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

        button_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });

        switch (status) {
            case 0:
                text_title.setTextColor(activity.getColor(R.color.colorPrimaryLight));
                button_opportunity.setVisibility(View.VISIBLE);
                button_interesting.setVisibility(View.VISIBLE);
                button_risk.setVisibility(View.VISIBLE);
                button_opportunity.setOnClickListener(v -> {
                    viewModelNews.addNewsDB(activity, news, Constants.OPPORTUNITY_STATUS, layout, activity.getString(R.string.snackBar_opportunity));
                    dialog.dismiss();
                });
                button_interesting.setOnClickListener(v -> {
                    viewModelNews.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });
                button_risk.setOnClickListener(v -> {
                    viewModelNews.addNewsDB(activity, news, Constants.RISK_STATUS, layout, activity.getString(R.string.snackBar_risk));
                    dialog.dismiss();
                });
                button_delete.setOnClickListener(v -> {
                    viewModelNews.addNewsDB(activity, news, Constants.DELETE_STATUS, layout, activity.getString(R.string.snackBar_remove));
                    dialog.dismiss();
                });

                break;
            case 1:
                text_title.setTextColor(activity.getColor(R.color.colorOpportunity));
                button_opportunity.setVisibility(View.GONE);
                button_interesting.setVisibility(View.VISIBLE);
                button_risk.setVisibility(View.VISIBLE);
                button_opportunity.setOnClickListener(v -> {
                    viewModelOpportunity.addNewsDB(activity, news, Constants.OPPORTUNITY_STATUS, layout, activity.getString(R.string.snackBar_opportunity));
                    dialog.dismiss();
                });
                button_interesting.setOnClickListener(v -> {
                    viewModelOpportunity.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });
                button_risk.setOnClickListener(v -> {
                    viewModelOpportunity.addNewsDB(activity, news, Constants.RISK_STATUS, layout, activity.getString(R.string.snackBar_risk));
                    dialog.dismiss();
                });
                button_delete.setOnClickListener(v -> {
                    viewModelOpportunity.addNewsDB(activity, news, Constants.DELETE_STATUS, layout, activity.getString(R.string.snackBar_remove));
                    dialog.dismiss();
                });
                break;
            case 2:

                text_title.setTextColor(activity.getColor(R.color.colorInteresting));
                button_opportunity.setVisibility(View.VISIBLE);
                button_interesting.setVisibility(View.GONE);
                button_risk.setVisibility(View.VISIBLE);
                button_opportunity.setOnClickListener(v -> {
                    viewModelInteresting.addNewsDB(activity, news, Constants.OPPORTUNITY_STATUS, layout, activity.getString(R.string.snackBar_opportunity));
                    dialog.dismiss();
                });
                button_interesting.setOnClickListener(v -> {
                    viewModelInteresting.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });
                button_risk.setOnClickListener(v -> {
                    viewModelInteresting.addNewsDB(activity, news, Constants.RISK_STATUS, layout, activity.getString(R.string.snackBar_risk));
                    dialog.dismiss();
                });
                button_delete.setOnClickListener(v -> {
                    viewModelInteresting.addNewsDB(activity, news, Constants.DELETE_STATUS, layout, activity.getString(R.string.snackBar_remove));
                    dialog.dismiss();
                });

                break;
            case 3:
                text_title.setTextColor(activity.getColor(R.color.colorRisk));
                button_opportunity.setVisibility(View.VISIBLE);
                button_interesting.setVisibility(View.VISIBLE);
                button_risk.setVisibility(View.GONE);
                button_opportunity.setOnClickListener(v -> {
                    viewModelRisk.addNewsDB(activity, news, Constants.OPPORTUNITY_STATUS, layout, activity.getString(R.string.snackBar_opportunity));
                    dialog.dismiss();
                });
                button_interesting.setOnClickListener(v -> {
                    viewModelRisk.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });
                button_risk.setOnClickListener(v -> {
                    viewModelRisk.addNewsDB(activity, news, Constants.RISK_STATUS, layout, activity.getString(R.string.snackBar_risk));
                    dialog.dismiss();
                });
                button_delete.setOnClickListener(v -> {
                    viewModelRisk.addNewsDB(activity, news, Constants.DELETE_STATUS, layout, activity.getString(R.string.snackBar_remove));
                    dialog.dismiss();
                });
                break;
        }

        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout((36 * width) / 40, (4 * height) / 5);

        isShowing = true;
        dialog.show();
    }
}
