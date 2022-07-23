package com.bhakti_sangrahalay.adapter

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import com.bhakti_sangrahalay.model.DainikCalendarBean

class DainikCalendarViewPagerAdapter(val context: Context, val arrayList: ArrayList<DainikCalendarBean>) : PagerAdapter() {
    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        TODO("Not yet implemented")
    }
}