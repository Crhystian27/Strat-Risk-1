package co.mba.strat_risk.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import androidx.lifecycle.ViewModelProvider.Factory;

import co.mba.strat_risk.R;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.ui.NewsDetailActivity;
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
    public static void showDialog(Activity activity, String tittle, String message, String image, Integer status, News news, Fragment fragment, Factory factory, RelativeLayout layout) {
        final Dialog dialog = new Dialog(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.item_dialog_selection, null);
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        NewsFragmentViewModel newsViewModel = ViewModelProviders.of(fragment, factory).get(NewsFragmentViewModel.class);
        OpportunityFragmentViewModel opportunityViewModel = ViewModelProviders.of(fragment, factory).get(OpportunityFragmentViewModel.class);
        InterestingFragmentViewModel interestingViewModel = ViewModelProviders.of(fragment, factory).get(InterestingFragmentViewModel.class);
        RiskFragmentViewModel riskViewModel = ViewModelProviders.of(fragment, factory).get(RiskFragmentViewModel.class);

        TextView text_title = dialog.findViewById(R.id.txt_dialog_selection_title);
        text_title.setText(tittle);
        TextView text_message = dialog.findViewById(R.id.txt_dialog_selection_body);
        text_message.setText(message);

        TextView button_cancel = dialog.findViewById(R.id.txt_selection_cancel);
        TextView button_delete = dialog.findViewById(R.id.txt_selection_delete);
        ImageView button_opportunity = dialog.findViewById(R.id.floatingOpportunity);
        ImageView button_interesting = dialog.findViewById(R.id.floatingInteresting);
        ImageView button_risk = dialog.findViewById(R.id.floatingRisk);
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

        view_image.setOnClickListener(v -> {
            Intent intent = new Intent(activity, NewsDetailActivity.class);
            intent.putExtra(Constants.EXTRA_NEWS, new Gson().toJson(news));
            activity.startActivity(intent);
            dialog.dismiss();
        });

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
                    newsViewModel.addNewsDB(activity, news, Constants.OPPORTUNITY_STATUS, layout, activity.getString(R.string.snackBar_opportunity));
                    dialog.dismiss();
                });
                button_interesting.setOnClickListener(v -> {
                    newsViewModel.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });
                button_risk.setOnClickListener(v -> {
                    newsViewModel.addNewsDB(activity, news, Constants.RISK_STATUS, layout, activity.getString(R.string.snackBar_risk));
                    dialog.dismiss();
                });
                button_delete.setOnClickListener(v -> {
                    newsViewModel.addNewsDB(activity, news, Constants.DELETE_STATUS, layout, activity.getString(R.string.snackBar_remove));
                    dialog.dismiss();
                });

                break;
            case 1:
                text_title.setTextColor(activity.getColor(R.color.colorOpportunity));
                button_opportunity.setVisibility(View.GONE);
                button_interesting.setVisibility(View.VISIBLE);
                button_risk.setVisibility(View.VISIBLE);

                button_interesting.setOnClickListener(v -> {
                    opportunityViewModel.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });
                button_risk.setOnClickListener(v -> {
                    opportunityViewModel.addNewsDB(activity, news, Constants.RISK_STATUS, layout, activity.getString(R.string.snackBar_risk));
                    dialog.dismiss();
                });
                button_delete.setOnClickListener(v -> {
                    opportunityViewModel.addNewsDB(activity, news, Constants.DELETE_STATUS, layout, activity.getString(R.string.snackBar_remove));
                    dialog.dismiss();
                });
                break;
            case 2:

                text_title.setTextColor(activity.getColor(R.color.colorInteresting));
                button_opportunity.setVisibility(View.VISIBLE);
                button_interesting.setVisibility(View.GONE);
                button_risk.setVisibility(View.VISIBLE);
                button_opportunity.setOnClickListener(v -> {
                    interestingViewModel.addNewsDB(activity, news, Constants.OPPORTUNITY_STATUS, layout, activity.getString(R.string.snackBar_opportunity));
                    dialog.dismiss();
                });
                button_interesting.setOnClickListener(v -> {
                    interestingViewModel.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });
                button_risk.setOnClickListener(v -> {
                    interestingViewModel.addNewsDB(activity, news, Constants.RISK_STATUS, layout, activity.getString(R.string.snackBar_risk));
                    dialog.dismiss();
                });
                button_delete.setOnClickListener(v -> {
                    interestingViewModel.addNewsDB(activity, news, Constants.DELETE_STATUS, layout, activity.getString(R.string.snackBar_remove));
                    dialog.dismiss();
                });

                break;
            case 3:
                text_title.setTextColor(activity.getColor(R.color.colorRisk));
                button_opportunity.setVisibility(View.VISIBLE);
                button_interesting.setVisibility(View.VISIBLE);
                button_risk.setVisibility(View.GONE);
                button_opportunity.setOnClickListener(v -> {
                    riskViewModel.addNewsDB(activity, news, Constants.OPPORTUNITY_STATUS, layout, activity.getString(R.string.snackBar_opportunity));
                    dialog.dismiss();
                });
                button_interesting.setOnClickListener(v -> {
                    riskViewModel.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });
                button_risk.setOnClickListener(v -> {
                    riskViewModel.addNewsDB(activity, news, Constants.RISK_STATUS, layout, activity.getString(R.string.snackBar_risk));
                    dialog.dismiss();
                });
                button_delete.setOnClickListener(v -> {
                    riskViewModel.addNewsDB(activity, news, Constants.DELETE_STATUS, layout, activity.getString(R.string.snackBar_remove));
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
