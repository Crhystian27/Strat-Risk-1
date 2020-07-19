package co.mba.strat_risk.ui.news_detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

import co.mba.strat_risk.R;
import co.mba.strat_risk.base.BaseActivity;
import co.mba.strat_risk.data.dto.ArticlesDTO;
import co.mba.strat_risk.util.Constants;

public class NewsDetailActivity extends BaseActivity {

    ArticlesDTO dto;
    String dto_extra;

    @Override
    protected int layoutRes() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dto_extra = extras.getString(Constants.EXTRA_NEWS);
        }
        dto = new Gson().fromJson(dto_extra, ArticlesDTO.class);

    }

    @Override
    protected int toolbarId() {
        return 0;
    }
}