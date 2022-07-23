package com.bhakti_sangrahalay.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.model.KathaBean;
import com.bhakti_sangrahalay.util.Parser;
import com.bhakti_sangrahalay.util.Utility;


public class KathaDescActivity extends BaseActivity {
    Resources resources;
    Utility utility;
    Parser parser;
    TextView titleTV;
    TextView descTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        utility = new Utility();
        parser = new Parser();
        setContentView(R.layout.katha_desc_activity);
        titleTV = findViewById(R.id.title_tv);
        descTV = findViewById(R.id.desc_tv);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            KathaBean kathaBean = (KathaBean) bundle.getSerializable("katha");
            titleTV.setText(kathaBean.getTitle());
            descTV.setText(kathaBean.getDesc());
            setTitle(kathaBean.getTitle());
        }
        setTypeface();

    }

    public void setTypeface() {
      /*  titleTV.setTypeface(boldTypeface);
        descTV.setTypeface(regularTypeface);*/
    }

    @Override
    public void attachViewModel() {

    }
}
