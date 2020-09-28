package co.mba.strat_risk.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import androidx.lifecycle.ViewModelProvider.Factory;

import co.mba.strat_risk.R;
import co.mba.strat_risk.data.entity.News;
import co.mba.strat_risk.ui.interesting.InterestingFragmentViewModel;
import co.mba.strat_risk.ui.news.NewsFragmentViewModel;
import co.mba.strat_risk.ui.opportunity.OpportunityFragmentViewModel;
import co.mba.strat_risk.ui.risk.RiskFragmentViewModel;
import co.mba.strat_risk.util.Constants;

/**
 * @Author Cristian David Soto
 */

public class DialogSelection {

    public static boolean isShowing = false;
    //public static Drawable drawable;

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("InflateParams")
    public static void showDialog(Activity activity, Integer status, News news, Fragment fragment, Factory factory, RelativeLayout layout) {
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


        TextView button_cancel = dialog.findViewById(R.id.txt_selection_cancel);
        TextView button_delete = dialog.findViewById(R.id.txt_selection_delete);

        LinearLayout layoutO = dialog.findViewById(R.id.dialogSelectionOpportunity);
        LinearLayout layoutI = dialog.findViewById(R.id.dialogSelectionInteresting);
        LinearLayout layoutR = dialog.findViewById(R.id.dialogSelectionRisk);

        TextView button_opportunity = dialog.findViewById(R.id.floatingOpportunity);
        TextView button_interesting = dialog.findViewById(R.id.floatingInteresting);
        TextView button_risk = dialog.findViewById(R.id.floatingRisk);

        //ImageView view_image = dialog.findViewById(R.id.dialog_selection_img);

        /*switch (status) {
            case 0:
                drawable = activity.getDrawable(R.drawable.ic_news);
                break;
            case 1:
                drawable = activity.getDrawable(R.drawable.ic_oportunidad);
                break;
            case 2:
                drawable = activity.getDrawable(R.drawable.ic_neutral);
                break;
            case 3:
                drawable = activity.getDrawable(R.drawable.ic_amenaza);
                break;
        }*/

        /*if (image == null) {
            Utilities.getBitmap(activity, view_image);
            Glide.with(activity.getApplicationContext())
                    .applyDefaultRequestOptions(RequestOptions.placeholderOf(drawable).error(drawable).circleCrop())
                    .load(drawable)
                    .into(view_image);
        } else {
            Glide.with(activity.getApplicationContext())
                    .applyDefaultRequestOptions(RequestOptions.placeholderOf(drawable).error(drawable).circleCrop())
                    .load(image)
                    .into(view_image);
        } */

        /*view_image.setOnClickListener(v -> {
            Intent intent = new Intent(activity, NewsDetailActivity.class);
            intent.putExtra(Constants.EXTRA_NEWS, new Gson().toJson(news));
            activity.startActivity(intent);
            dialog.dismiss();
        });*/

        button_cancel.setOnClickListener(v -> {
            dialog.dismiss();
        });

        switch (status) {
            case 0:

                button_opportunity.setVisibility(View.VISIBLE);
                button_interesting.setVisibility(View.VISIBLE);
                button_risk.setVisibility(View.VISIBLE);
                layoutO.setVisibility(View.VISIBLE);
                layoutI.setVisibility(View.VISIBLE);
                layoutR.setVisibility(View.VISIBLE);
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

                button_opportunity.setVisibility(View.GONE);
                button_interesting.setVisibility(View.VISIBLE);
                button_risk.setVisibility(View.VISIBLE);

                layoutO.setVisibility(View.GONE);
                layoutI.setVisibility(View.VISIBLE);
                layoutR.setVisibility(View.VISIBLE);

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

                button_opportunity.setVisibility(View.VISIBLE);
                button_interesting.setVisibility(View.VISIBLE);
                button_risk.setVisibility(View.GONE);

                layoutO.setVisibility(View.VISIBLE);
                layoutI.setVisibility(View.VISIBLE);
                layoutR.setVisibility(View.GONE);

                button_opportunity.setOnClickListener(v -> {
                    interestingViewModel.addNewsDB(activity, news, Constants.OPPORTUNITY_STATUS, layout, activity.getString(R.string.snackBar_opportunity));
                    dialog.dismiss();
                });
               button_interesting.setOnClickListener(v -> {
                    interestingViewModel.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });
                /*button_risk.setOnClickListener(v -> {
                    interestingViewModel.addNewsDB(activity, news, Constants.RISK_STATUS, layout, activity.getString(R.string.snackBar_risk));
                    dialog.dismiss();
                });*/
                button_delete.setOnClickListener(v -> {
                    interestingViewModel.addNewsDB(activity, news, Constants.DELETE_STATUS, layout, activity.getString(R.string.snackBar_remove));
                    dialog.dismiss();
                });

                break;
            case 3:

                button_opportunity.setVisibility(View.VISIBLE);
                button_interesting.setVisibility(View.GONE);
                button_risk.setVisibility(View.VISIBLE);

                layoutO.setVisibility(View.VISIBLE);
                layoutI.setVisibility(View.GONE);
                layoutR.setVisibility(View.VISIBLE);

                button_opportunity.setOnClickListener(v -> {
                    riskViewModel.addNewsDB(activity, news, Constants.OPPORTUNITY_STATUS, layout, activity.getString(R.string.snackBar_opportunity));
                    dialog.dismiss();
                });
                /*button_interesting.setOnClickListener(v -> {
                    riskViewModel.addNewsDB(activity, news, Constants.INTERESTING_STATUS, layout, activity.getString(R.string.snackBar_interesting));
                    dialog.dismiss();
                });*/

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
        //int height = metrics.heightPixels;
        dialog.getWindow().setLayout((36 * width) / 40, ViewGroup.LayoutParams.WRAP_CONTENT);

        isShowing = true;
        dialog.show();
    }
}
