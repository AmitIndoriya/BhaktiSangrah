package com.bhakti_sangrahalay.ui.activity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.adapter.FragmentViewPagerAdapter;
import com.bhakti_sangrahalay.model.KundliBean;
import com.bhakti_sangrahalay.panchang.calculations.KundliCalculation;
import com.bhakti_sangrahalay.panchang.generator.GenerateKundliData;
import com.bhakti_sangrahalay.ui.fragment.ChartFragment;
import com.bhakti_sangrahalay.util.Parser;
import com.bhakti_sangrahalay.util.Utility;
import com.google.android.material.tabs.TabLayout;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class KundliHomeActivity extends BaseActivity {

    private ViewPager viewPager;
    ArrayList<Fragment> fragList;
    Resources resources;
    // Utility utility;
    ArrayList<KundliBean> arrayList;
    TabLayout tabLayout;
    KundliCalculation kundliCalculation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        //utility = new Utility(this);
        setContentView(R.layout.act_horoscope_layout);
        initView();
        setUpViewpager();
    }


    public void initView() {
        // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = findViewById(R.id.view_pager);


    }

    private void setUpViewPagerAdapter() {
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), fragList);
        viewPager.setAdapter(adapter);

    }


    public void setTypeface() {

    }

    public View getTabView(String text) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null);
        TextView tv = view.findViewById(R.id.tabtext);
        tv.setTextColor(resources.getColor(R.color.white));
        tv.setText(text);
        return view;


    }

  /*  private ArrayList<Fragment> getFragmentList() {
        ArrayList<Fragment> fragList = new ArrayList<>();
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getLagna(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getDrekkana(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getChaturthamansh(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getSaptamamsha(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getShashtiamsha(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getNavmansh(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getDashamamsha(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getShodashamsha(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getVimshamsha(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getSaptavimshamsha(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getChaturvimshamsha(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getTrimshamsha(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getKhavedamsha(), 0));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getAkshvedamsha(), 0));
        return fragList;
    }*/

    private ArrayList<Fragment> getKundliFragmentList() {
        ArrayList<Fragment> fragList = new ArrayList<>();
        fragList.add(ChartFragment.getInstance(kundliCalculation.getLaganKundliArray(), kundliCalculation.getLaganKundliArray()[12], null));
        fragList.add(ChartFragment.getInstance(kundliCalculation.getNavmanshKundliArray(), kundliCalculation.getNavmanshKundliArray()[12], null));
        fragList.add(ChartFragment.getInstance(kundliCalculation.getChandraKundliArray(), kundliCalculation.getChandraKundliArray()[12], null));
        int[] chalitArr = kundliCalculation.getChalitChartArray();
        fragList.add(ChartFragment.getInstance(chalitArr, chalitArr[12], kundliCalculation.getCuspsMidDegreeArrayForChalit()));
        fragList.add(ChartFragment.getInstance(kundliCalculation.getLaganKundliArray(), kundliCalculation.getKarakanshLagna(), null));
        fragList.add(ChartFragment.getInstance(kundliCalculation.getNavmanshKundliArray(), kundliCalculation.getKarakanshLagna(), null));


      /*  fragList.add(ChartFragment.getInstance(kundliCalculation.getChaturthamanshArray(), kundliCalculation.getChaturthamanshArray()[12], null));
        fragList.add(ChartFragment.getInstance(kundliCalculation.getSaptamamshaArray(), kundliCalculation.getSaptamamshaArray()[12], null));
        fragList.add(ChartFragment.getInstance(kundliCalculation.getNavmanshKundliArray(), kundliCalculation.getNavmanshKundliArray()[12], null));
        fragList.add(ChartFragment.getInstance(kundliCalculation.getDashamamshaArray(), kundliCalculation.getDashamamshaArray()[12], null));
        fragList.add(ChartFragment.getInstance(kundliCalculation.getDashamamshaArray(), kundliCalculation.getDashamamshaArray()[12], null));
        int[] chalitArr = kundliCalculation.getChalitChartArray();
        fragList.add(ChartFragment.getInstance(chalitArr, chalitArr[12], kundliCalculation.getKpDegreeArray()));
         fragList.add(ChartFragment.getInstance(kundliCalculation.getDrekkanaArray(), kundliCalculation.getDrekkanaArray()[12], null));*/

        /*fragList.add(KundliPlanetFragment.getInstance(arrayList.get(0).getPlanetDegree(), 0));
        fragList.add(KundliPlanetFragment.getInstance(arrayList.get(0).getPlanetDegree(), 1));
        fragList.add(ChartFragment.getInstance(arrayList.get(0).getLagna(), getKarakanshLagna(arrayList.get(0).getPlanetDegree())));
*/
        return fragList;
    }


    void setUpViewpager() {
        //if (response != null) {
        Parser parser = new Parser();
        arrayList = parser.parseKundliData(GenerateKundliData.getPlanets(this));
        kundliCalculation = new KundliCalculation(arrayList);
        fragList = getKundliFragmentList();
        setUpViewPagerAdapter();
        tabLayout.setupWithViewPager(viewPager);
        String[] tabTexts = resources.getStringArray(R.array.kundli_module_list);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(tabTexts[i]));
            }

        }
    }

    @Override
    public void attachViewModel() {

    }
}

