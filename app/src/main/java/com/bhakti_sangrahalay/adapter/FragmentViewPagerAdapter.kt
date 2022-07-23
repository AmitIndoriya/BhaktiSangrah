package com.bhakti_sangrahalay.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentViewPagerAdapter(fragmentManager: FragmentManager, private val fragmentList: ArrayList<Fragment>) :
        FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    // 3
    override fun getCount(): Int {
        return fragmentList.size
    }
}