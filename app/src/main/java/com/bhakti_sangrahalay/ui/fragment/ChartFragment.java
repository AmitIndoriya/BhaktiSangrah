package com.bhakti_sangrahalay.ui.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.adapter.LagnaKundliListAdapter;
import com.bhakti_sangrahalay.ui.activity.KundliOutputActivity;
import com.bhakti_sangrahalay.ui.customcomponent.ChartView;
import com.bhakti_sangrahalay.util.Constants;
import com.bhakti_sangrahalay.util.Utility;

import java.util.ArrayList;

public class ChartFragment extends Fragment {
    private KundliOutputActivity activity;

    private Resources resources;
    int lagna;
    int[] planetInRashi;
    double[] midDegreeArray;
    ArrayList<String> planetDegList;

    private ChartFragment(int[] planetInRashi, int lagna, double[] midDegreeArray) {
        this.planetInRashi = planetInRashi;
        this.lagna = lagna;
        this.midDegreeArray = midDegreeArray;
    }

    private ChartFragment(int[] planetInRashi, int lagna, double[] midDegreeArray, ArrayList<String> planetDegList) {
        this.planetInRashi = planetInRashi;
        this.lagna = lagna;
        this.midDegreeArray = midDegreeArray;
        this.planetDegList = planetDegList;
    }

    public static ChartFragment getInstance(int[] planetInRashi, int lagna, double[] midDegreeArray) {
        return new ChartFragment(planetInRashi, lagna, midDegreeArray);
    }

    public static ChartFragment getInstance(int[] planetInRashi, int lagna, double[] midDegreeArray, ArrayList<String> planetDegList) {
        return new ChartFragment(planetInRashi, lagna, midDegreeArray, planetDegList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (KundliOutputActivity) context;
        resources = getResources();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_chart_layout, container, false);
        Constants constants = Constants.getInstance(activity);
        LinearLayout linearLayout = view.findViewById(R.id.container);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(constants.getScreenWidth() - Utility.convertDpToPx(resources, 20), constants.getScreenWidth() - Utility.convertDpToPx(resources, 20));
        params.leftMargin = Utility.convertDpToPx(resources, 10);
        params.rightMargin = Utility.convertDpToPx(resources, 10);
        params.topMargin = Utility.convertDpToPx(resources, 10);
        params.bottomMargin = Utility.convertDpToPx(resources, 10);
        ChartView drawView;
        if (midDegreeArray != null && midDegreeArray.length > 0) {
            drawView = new ChartView(activity, resources, planetInRashi, lagna, midDegreeArray);
        } else {
            drawView = new ChartView(activity, resources, planetInRashi, lagna);
        }
        linearLayout.addView(drawView);
        linearLayout.setLayoutParams(params);

        if (planetDegList != null && planetDegList.size() > 0) {
            setRVAdapter(view);
        }
        return view;
    }


    void setRVAdapter(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setVisibility(View.VISIBLE);
        String[] planetNameLis = requireContext().getResources().getStringArray(R.array.planet_short_name_list);
        recyclerView.setNestedScrollingEnabled(false);
        LagnaKundliListAdapter horaListAdapter = new LagnaKundliListAdapter(requireContext(), planetNameLis,planetDegList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(requireActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(horaListAdapter);

    }

}