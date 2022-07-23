package com.bhakti_sangrahalay.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.util.Utility;
import com.google.android.material.card.MaterialCardView;

public class VartKathaHomeActivity extends BaseActivity implements View.OnClickListener {
    Resources resources;
    Utility utility;
    MaterialCardView cardView1;
    MaterialCardView cardView2;
    MaterialCardView cardView3;
    MaterialCardView cardView4;
    MaterialCardView cardView5;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        utility = new Utility();
        setContentView(R.layout.activity_vart_katha_home);
        cardView1 = findViewById(R.id.cardview1);
        cardView2 = findViewById(R.id.cardview2);
        cardView3 = findViewById(R.id.cardview3);
        cardView4 = findViewById(R.id.cardview4);
        //cardView5 = findViewById(R.id.cardview5);

        textView1 = findViewById(R.id.textview1);
        textView2 = findViewById(R.id.textview2);
        textView3 = findViewById(R.id.textview3);
        textView4 = findViewById(R.id.textview4);
        //textView5 = findViewById(R.id.textview5);
        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        //cardView5.setOnClickListener(this);

        setTitle(resources.getString(R.string.vart_kathayen1));
        setTypeface();
    }


    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.cardview1:
                bundle.putInt("resId", R.raw.saptahik_vrat_list);
                bundle.putString("title", resources.getString(R.string.saptahik_vart_katha));
                break;
            case R.id.cardview2:
                bundle.putInt("resId", R.raw.vishisht_vrat_katha_list);
                bundle.putString("title", resources.getString(R.string.vishisht_vart_katha));
                break;
            case R.id.cardview3:
                bundle.putInt("resId", R.raw.ekadashi_vrat_list);
                bundle.putString("title", resources.getString(R.string.ekadashi_vart_katha));
                break;
            case R.id.cardview4:
                bundle.putInt("resId", R.raw.navratri_katha_list);
                bundle.putString("title", resources.getString(R.string.navratri_vart_katha));
                break;
            /*case R.id.cardview5:
                bundle.putInt("resId", R.raw.saptahik_vrat_list);
                bundle.putString("title", resources.getString(R.string.satyanarayan_vart_katha));
                break;*/
        }
        Intent intent = new Intent(VartKathaHomeActivity.this, KathaActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void setTypeface() {
      /*  textView1.setTypeface(mediumTypeface);
        textView2.setTypeface(mediumTypeface);
        textView3.setTypeface(mediumTypeface);
        textView4.setTypeface(mediumTypeface);*/
        //textView5.setTypeface(mediumTypeface);
    }

    @Override
    public void attachViewModel() {

    }
}
