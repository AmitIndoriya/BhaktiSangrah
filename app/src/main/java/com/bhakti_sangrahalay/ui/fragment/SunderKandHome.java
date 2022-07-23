package com.bhakti_sangrahalay.ui.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.model.SunderKandHomeBean;
import com.bhakti_sangrahalay.util.Parser;
import com.bhakti_sangrahalay.util.Utility;

import java.util.ArrayList;

public class SunderKandHome extends Fragment {
    TextView titleTV;
    TextView headingTV;
    TextView subHeadingTV;
    TextView sholkTV1;
    TextView sholkTV2;
    TextView sholkTV3;
    TextView meaningTV1;
    TextView meaningTV2;
    TextView meaningTV3;
    ArrayList<SunderKandHomeBean> sunderKaandBeanList;


    public static SunderKandHome newInstance() {

        Bundle args = new Bundle();
        SunderKandHome fragment = new SunderKandHome();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parser parser = new Parser();
        Utility utility = new Utility();
        Resources resources = getResources();
        sunderKaandBeanList = parser.sunderKandSlok(utility.readFromFile(resources, R.raw.sunder_kand_home));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sunder_kand_home, container, false);
        titleTV = root.findViewById(R.id.title_tv);
        headingTV = root.findViewById(R.id.heading_tv);
        subHeadingTV = root.findViewById(R.id.sub_heading_tv);
        sholkTV1 = root.findViewById(R.id.shlok1);
        sholkTV2 = root.findViewById(R.id.shlok2);
        sholkTV3 = root.findViewById(R.id.shlok3);
        meaningTV1 = root.findViewById(R.id.shlok_meaning1);
        meaningTV2 = root.findViewById(R.id.shlok_meaning2);
        meaningTV3 = root.findViewById(R.id.shlok_meaning3);
        sholkTV1.setText(sunderKaandBeanList.get(0).getDoha());
        sholkTV2.setText(sunderKaandBeanList.get(1).getDoha());
        sholkTV3.setText(sunderKaandBeanList.get(2).getDoha());

        meaningTV1.setText(sunderKaandBeanList.get(0).getMeaning());
        meaningTV2.setText(sunderKaandBeanList.get(1).getMeaning());
        meaningTV3.setText(sunderKaandBeanList.get(2).getMeaning());
        setTypeface();
        return root;
    }

    private void setTypeface() {
   /*     titleTV.setTypeface(BaseActivity.boldTypeface);
        headingTV.setTypeface(BaseActivity.boldTypeface);
        subHeadingTV.setTypeface(BaseActivity.semiBoldTypeface);
        sholkTV1.setTypeface(BaseActivity.mediumTypeface);
        sholkTV2.setTypeface(BaseActivity.mediumTypeface);
        sholkTV3.setTypeface(BaseActivity.mediumTypeface);
        meaningTV1.setTypeface(BaseActivity.mediumTypeface);
        meaningTV2.setTypeface(BaseActivity.mediumTypeface);
        meaningTV3.setTypeface(BaseActivity.mediumTypeface);*/
    }
}
