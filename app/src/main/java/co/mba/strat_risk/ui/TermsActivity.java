package co.mba.strat_risk.ui;


import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;

public class TermsActivity extends BaseActivity {

    private PDFView pdfView;


    @Override
    protected int toolbarId() {
        return R.id.toolbar_terms;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_terms;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(true, true);

        //getToolbar().setTitle();

        pdfView = findViewById(R.id.pdfView);

        getToolbar().setTitle(getString(R.string.dialog_policy_service));

        pdfView.fromAsset("terms_2020.pdf")
                .password(null).defaultPage(0)
                .enableSwipe(true).swipeHorizontal(false).enableDoubletap(true)
                .onTap(e -> true).onRender((nbPages, pageWidth, pageHeight) -> pdfView.fitToWidth())
                .enableAnnotationRendering(true)
                .onPageError((page, t) -> Log.e("DialogInformation", t.getMessage()))
                .load();

    }
}