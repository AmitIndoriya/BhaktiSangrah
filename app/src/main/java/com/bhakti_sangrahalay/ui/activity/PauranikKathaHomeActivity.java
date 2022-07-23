package com.bhakti_sangrahalay.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.adapter.PauranikKathaAdapter;
import com.bhakti_sangrahalay.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PauranikKathaHomeActivity extends BaseActivity {
    private Resources resources;
    Utility utility;
    RecyclerView recyclerView;
    List<String> source = null;
    ArrayList<Integer> imagesSource = null;
    ArrayList<Integer> rowFile = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        utility = new Utility();
        setContentView(R.layout.activity_pauranik_katha_home);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        source = getName();
        imagesSource = getImages();
        rowFile = getRowFile();

        PauranikKathaAdapter adapter1 = new PauranikKathaAdapter(this, source, imagesSource, rowFile);
        recyclerView.setAdapter(adapter1);
        setTitle(resources.getString(R.string.pauranik_katha));
    }


    public List<String> getName() {
        String[] strArr = resources.getStringArray(R.array.pauranik_katha_name_list);
        List<String> source = Arrays.asList(strArr);
        return source;
    }

    public ArrayList<Integer> getImages() {
        int[] intArr = utility.getIntArray(resources, R.array.pauranik_katha_icon_list);
        ArrayList<Integer> intList = new ArrayList<Integer>(intArr.length);
        for (int i : intArr) {
            intList.add(i);
        }
        return intList;
    }

    public ArrayList<Integer> getRowFile() {
        int[] intArr = utility.getIntArray(resources, R.array.pauranik_katha_row_file_list);
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
