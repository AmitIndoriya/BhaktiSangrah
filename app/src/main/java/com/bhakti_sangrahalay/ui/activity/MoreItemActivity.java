package com.bhakti_sangrahalay.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.adapter.MoreItemAdater;
import com.bhakti_sangrahalay.contansts.GlobalVariables;
import com.bhakti_sangrahalay.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoreItemActivity extends BaseActivity {
    RecyclerView recyclerView;
    private Resources resources;
    Utility utility;
    int type;
    String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        utility = new Utility();
        Bundle bundle = getIntent().getExtras();
        type = bundle.getInt("type");
        title = bundle.getString("title");
        List<String> source = null;
        ArrayList<Integer> imagesSource = null;
        ArrayList<Integer> rowFile = null;

        setContentView(R.layout.more_item_activity_layout);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        if (type == GlobalVariables.aarti) {
            source = getName();
            imagesSource = getImages();
            rowFile = getRowFile();
        } else if (type == GlobalVariables.chalisha) {
            source = getTitleForChalisha();
            imagesSource = getIconForChalisha();
            rowFile = getRowFileForChalisha();
        }
        MoreItemAdater adapter1 = new MoreItemAdater(this, source, imagesSource, rowFile, type);
        recyclerView.setAdapter(adapter1);
        setTitle(title);
    }

    public List<String> getName() {
        String[] strArr = resources.getStringArray(R.array.god_name_list);
        List<String> source = Arrays.asList(strArr);
        return source;
    }

    public ArrayList<Integer> getImages() {
        int[] intArr = utility.getIntArray(resources, R.array.god_icon_list);
        ArrayList<Integer> intList = new ArrayList<Integer>(intArr.length);
        for (int i : intArr) {
            intList.add(i);
        }
        return intList;
    }

    public ArrayList<Integer> getRowFile() {
        int[] intArr = utility.getIntArray(resources, R.array.aarti_row_file_list);
        ArrayList<Integer> intList = new ArrayList<Integer>(intArr.length);
        for (int i : intArr) {
            intList.add(i);
        }
        return intList;
    }

    public List<String> getTitleForChalisha() {
        String[] strArr = resources.getStringArray(R.array.chalish_title_list);
        List<String> source = Arrays.asList(strArr);
        return source;
    }

    public ArrayList<Integer> getIconForChalisha() {
        int[] intArr = utility.getIntArray(resources, R.array.chalish_icon_list);
        ArrayList<Integer> intList = new ArrayList<Integer>(intArr.length);
        for (int i : intArr) {
            intList.add(i);
        }
        return intList;
    }

    public ArrayList<Integer> getRowFileForChalisha() {
        int[] intArr = utility.getIntArray(resources, R.array.chalish_raw_file_list);
        ArrayList<Integer> intList = new ArrayList<Integer>(intArr.length);
        for (int i : intArr) {
            intList.add(i);
        }
        return intList;
    }

    @Override
    public void attachViewModel() {

    }

    @Override
    public void setTypeface() {

    }
}
