package com.bhakti_sangrahalay.ui.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.adapter.KathaAdapter;
import com.bhakti_sangrahalay.model.KathaBean;
import com.bhakti_sangrahalay.util.Parser;
import com.bhakti_sangrahalay.util.Utility;

import java.util.ArrayList;

public class KathaActivity extends BaseActivity {
    RecyclerView recyclerView;
    private Resources resources;
    Parser parser;
    Utility utility;
    KathaAdapter adapter;
    int resId;
    ArrayList<KathaBean> kathaList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utility = new Utility();
        resources = getResources();
        parser = new Parser();
        resId = getIntent().getExtras().getInt("resId");
        String title = getIntent().getExtras().getString("title");
        setContentView(R.layout.katha_activity_layout);
        kathaList = parser.kathaListParser(utility.readFromFile(resources, resId));
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);
        setTitle(title);
        adapter = new KathaAdapter(this, kathaList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        super.onDestroy();
    }


    @Override
    public void attachViewModel() {

    }

    @Override
    public void setTypeface() {

    }
}
