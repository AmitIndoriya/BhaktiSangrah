package com.bhakti_sangrahalay.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class DescViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    ArrayList<Fragment> arrayList;

    public DescViewPagerAdapter(Context context, FragmentManager fm, ArrayList<Fragment> arrayList) {
        super(fm);
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public Fragment getCurrentFragment(int pos) {
        return arrayList.get(pos);
    }
}