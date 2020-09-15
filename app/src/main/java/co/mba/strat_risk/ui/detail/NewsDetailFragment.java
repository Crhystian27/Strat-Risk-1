package co.mba.strat_risk.ui.detail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseFragment;

public class NewsDetailFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory factory;
    private NewsDetailFragmentViewModel viewModel;
    private TextView textViewTitle, textViewBody;
    private ImageView imgDetail,bottomRisk,bottomInteresting,bottomOpportunity;


    @Override
    protected int layoutRes() {
        return R.layout.detail_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textViewTitle = view.findViewById(R.id.title_detail);
        textViewBody = view.findViewById(R.id.body_detail);
        imgDetail = view.findViewWithTag(R.id.imageDetail);
        bottomInteresting = view.findViewById(R.id.detailInteresting);
        bottomRisk = view.findViewById(R.id.detailRisk);
        bottomOpportunity = view.findViewById(R.id.detailOpportunity);

        //String title = getArguments().getString("");
        //String body = getArguments().getString("");


    }
}