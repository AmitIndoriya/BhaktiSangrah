package com.bhakti_sangrahalay.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.adapter.DescViewPagerAdapter;
import com.bhakti_sangrahalay.model.SunderKaandBean;
import com.bhakti_sangrahalay.ui.fragment.SunderKandFragment;
import com.bhakti_sangrahalay.ui.fragment.SunderKandHome;
import com.bhakti_sangrahalay.util.Parser;
import com.bhakti_sangrahalay.util.Utility;

import java.util.ArrayList;

public class SunderKandActivity extends BaseActivity implements View.OnClickListener {
    Resources resources;
    Utility utility;
    DescViewPagerAdapter pagerAdapter;
    ViewPager viewPager;
    LinearLayout nextBtn, preBtn;
    TextView counterTV;
    int setCurrentItem;
    int totalPage;
    int images[];


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sunder_kaand_act_layout);
        utility = new Utility();
        resources = getResources();
        Parser parser = new Parser();
        SunderKaandBean sunderKaandBean = parser.sunderKandParser(this, resources, utility.readFromFile(resources, R.raw.sunder_kand));
        Log.i("", sunderKaandBean.toString());
        viewPager = findViewById(R.id.view_pager);

        nextBtn = findViewById(R.id.next_btn);
        preBtn = findViewById(R.id.pre_btn);
        counterTV = findViewById(R.id.page_counter);
        pagerAdapter = new DescViewPagerAdapter(this, getSupportFragmentManager(), getFragmentList(sunderKaandBean));
        viewPager.setAdapter(pagerAdapter);
        counterTV.setText(1 + "/" + totalPage);
        setTitle(resources.getString(R.string.Sunder_kand));
        nextBtn.setOnClickListener(this);
        preBtn.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                counterTV.setText(position + 1 + "/" + totalPage);
                setCurrentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private ArrayList<Fragment> getFragmentList(SunderKaandBean sunderKaandBean) {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(SunderKandHome.newInstance());
        SunderKandFragment  sunderKandFragment;
        totalPage = sunderKaandBean.getSunderKandArrayArrayList().size()+1;
        for (int i = 0; i < sunderKaandBean.getSunderKandArrayArrayList().size(); i++) {
            sunderKandFragment = SunderKandFragment.newInstance(sunderKaandBean.getSunderKandArrayArrayList().get(i));
            arrayList.add(sunderKandFragment);
        }
        return arrayList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_btn:
                viewPager.setCurrentItem(++setCurrentItem);
                break;
            case R.id.pre_btn:
                viewPager.setCurrentItem(--setCurrentItem);
                break;
        }
    }


    @Override
    public void attachViewModel() {

    }

    @Override
    public void setTypeface() {

    }
}
