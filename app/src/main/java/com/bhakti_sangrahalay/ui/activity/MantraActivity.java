package com.bhakti_sangrahalay.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.adapter.MantraAdapter;
import com.bhakti_sangrahalay.model.MantraBean;
import com.bhakti_sangrahalay.util.Parser;
import com.bhakti_sangrahalay.util.Utility;

import java.util.ArrayList;

public class MantraActivity extends BaseActivity {
    private Resources resources;
    Utility utility;
    Parser parser;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        utility = new Utility();
        parser = new Parser();
        setContentView(R.layout.mantra_layout);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<MantraBean> arrayList = parser.mantraListParser(utility.readFromFile(resources, R.raw.mantra_list));

        MantraAdapter adapter = new MantraAdapter(this, arrayList, getImages());
        recyclerView.setAdapter(adapter);
        setTitle(resources.getString(R.string.mantra));
    }

    public ArrayList<Integer> getImages() {
        ArrayList<Integer> imagesSource = new ArrayList<>();
        imagesSource.add(R.drawable.gayatri_maa_image);
        imagesSource.add(R.drawable.ganeshji_aarti_image);
        imagesSource.add(R.drawable.shivji_aarti_image);
        imagesSource.add(R.drawable.hanuman_chalisha);
        imagesSource.add(R.drawable.chandra_grah_image);
        imagesSource.add(R.drawable.mangal_grah_image);
        imagesSource.add(R.drawable.budh_grah_image);
        imagesSource.add(R.drawable.guru_grah_image);
        imagesSource.add(R.drawable.sukra_grah_image);
        imagesSource.add(R.drawable.shani_dev_image);
        imagesSource.add(R.drawable.surya_dev_image);
        imagesSource.add(R.drawable.rahu_grah_image);
        imagesSource.add(R.drawable.ketu_grah_image);
        //imagesSource.add(R.drawable.others1);
        return imagesSource;
    }

    @Override
    public void attachViewModel() {

    }

    @Override
    public void setTypeface() {

    }
}
