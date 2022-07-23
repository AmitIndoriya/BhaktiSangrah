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

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.ui.activity.KundliHomeActivity;
import com.bhakti_sangrahalay.ui.customcomponent.ChartView;
import com.bhakti_sangrahalay.util.Constants;
import com.bhakti_sangrahalay.util.Utility;

public class ChartFragment extends Fragment {
    private KundliHomeActivity activity;
   // private Utility Utility;
    private Resources resources;
    int lagna;
    int[] planetInRashi;
    double[] midDegreeArray;

    private ChartFragment(int[] planetInRashi, int lagna, double[] midDegreeArray) {
        this.planetInRashi = planetInRashi;
        this.lagna = lagna;
        this.midDegreeArray = midDegreeArray;
    }

    public static ChartFragment getInstance(int[] planetInRashi, int lagna, double[] midDegreeArray) {
        return new ChartFragment(planetInRashi, lagna, midDegreeArray);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (KundliHomeActivity) context;
        resources = getResources();
       /* if (Utility == null) {
            Utility = new Utility(activity);
        }*/
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

        initView(view);
        return view;
    }

    private void initView(View view) {

    }

}