package com.bhakti_sangrahalay.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragList;

    public ViewPagerFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;
    }

    @Override
    public Fragment getItem(int position) {

        return fragList.get(position);

    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
