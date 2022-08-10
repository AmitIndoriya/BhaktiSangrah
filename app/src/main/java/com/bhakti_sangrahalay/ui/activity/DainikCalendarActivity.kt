package com.bhakti_sangrahalay.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.FragmentViewPagerAdapter
import com.bhakti_sangrahalay.ui.fragment.DainikCalendarFragment
import com.bhakti_sangrahalay.model.DainikCalendarModel
import com.bhakti_sangrahalay.util.Utility
import com.bhakti_sangrahalay.viewmodel.DainikCalendarViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class DainikCalendarActivity : BaseActivity() {
    private lateinit var viewPager: ViewPager
    lateinit var viewModel: DainikCalendarViewModel
    private lateinit var fragIds: ArrayList<Int>
    lateinit var adapter: FragmentViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFont()
        attachViewModel()
        fragIds = getIdList()
        setContentView(R.layout.activity_dainik_calendar_layout)
        setTitle(resources.getString(R.string.hindu_calendar))
        initView()
    }

    private fun initView() {
        viewPager = findViewById(R.id.view_pager)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun attachViewModel() {
        val viewModelProvider =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
        viewModel = viewModelProvider[DainikCalendarViewModel::class.java]
        viewModel.resources = resources
        GlobalScope.launch {
            getDailyCalendarData()
        }
        viewModel.dailyCalendarModelLiveData.observe(this) { setUpViewPagerAdapter(it) }
    }

    override fun setTypeface() {
        TODO("Not yet implemented")
    }

    private fun getDailyCalendarData() {
        viewModel.getDailyCalendarData(Utility.getPlaceForPanchang())
    }


    private fun setUpViewPagerAdapter(dailyCalendarList: ArrayList<DainikCalendarModel>) {
        adapter =
            FragmentViewPagerAdapter(supportFragmentManager, getFragmentList(dailyCalendarList))
        viewPager.adapter = adapter
        viewPager.currentItem = intent.getIntExtra("index", 0)
        //viewPager.offscreenPageLimit=12
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                //selectedFragment = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun getFragmentList(dailyCalendarList: ArrayList<DainikCalendarModel>): ArrayList<Fragment> {
        val fragList = ArrayList<Fragment>()
        for (i in 0..11) {
            fragList.add(DainikCalendarFragment.getInstance(fragIds[i], dailyCalendarList[i]))
            //calendar.add(Calendar.MONTH, 1)
        }
        return fragList
    }

    private fun getIdList(): ArrayList<Int> {
        val list = ArrayList<Int>()
        list.add(101)
        list.add(102)
        list.add(103)
        list.add(104)
        list.add(105)
        list.add(106)
        list.add(107)
        list.add(108)
        list.add(109)
        list.add(110)
        list.add(111)
        list.add(112)
        return list
    }

    fun getPanchanDataForSelectedDate(calendar: Calendar) {
        (adapter.getItem(viewPager.currentItem) as DainikCalendarFragment).setTodayPanchangData(
            viewModel.getTodayPanchang(calendar, Utility.getPlaceForPanchang())
        )
    }
}