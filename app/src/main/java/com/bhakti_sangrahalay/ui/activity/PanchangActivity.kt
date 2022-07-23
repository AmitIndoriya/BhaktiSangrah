package com.bhakti_sangrahalay.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.activity.PlaceActivity
import com.bhakti_sangrahalay.adapter.FragmentViewPagerAdapter
import com.bhakti_sangrahalay.fragment.*
import com.bhakti_sangrahalay.model.PlaceModel
import com.bhakti_sangrahalay.viewmodel.PanchangViewModel

import com.google.android.material.tabs.TabLayout
import java.util.*


class PanchangActivity : BaseActivity() {
    lateinit var viewModel: PanchangViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var fragList: ArrayList<Fragment>
    lateinit var calendar: Calendar

    // private lateinit var placeModel: PlaceModel
    lateinit var preferences: SharedPreferences

    //var resources: Resources? = null
    var selectedFragment = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork() // or .detectAll() for all detectable problems
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build()
        )
        //setStatusBarColor(R.color.light_blue_color)
        super.onCreate(savedInstanceState)
        preferences = getSharedPreferences("Application", MODE_PRIVATE)
        attachViewModel()
        fragList = getFragmentList()
        calendar = Calendar.getInstance()
        //placeModel = PlaceModel()
        setContentView(R.layout.activity_panchang_home)
        setTitle(resources.getString(R.string.panchag_aur_muhurat))
        initView()
        setUpViewPagerAdapter()
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.aqua_marine))
        val tabTexts = resources.getStringArray(R.array.panchang_tab)
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            if (tab != null) {
                tab.customView = getTabView(tabTexts[i])
            }
        }
    }

    override fun attachViewModel() {
        val viewModelProvider =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
        viewModel = viewModelProvider[PanchangViewModel::class.java]
    }

    override fun setTypeface() {
    }

    private fun initView() {
        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        viewPager = findViewById(R.id.view_pager)
        supportActionBar?.elevation = 0F
    }

    private fun getFragmentList(): ArrayList<Fragment> {
        val fragList = ArrayList<Fragment>()
        fragList.add(DailyPanchangFragment.getInstance())
        fragList.add(HoraFragment.getInstance())
        fragList.add(ChogdiaFragment.getInstance())
        fragList.add(DoGhatiFragment.getInstance())
        fragList.add(RahuKaalFragment.getInstance())
        fragList.add(PanchakFragment.getInstance())
        fragList.add(BhadraFragment.getInstance())

        return fragList
    }

    private fun setUpViewPagerAdapter() {
        val adapter = FragmentViewPagerAdapter(supportFragmentManager, fragList)
        viewPager.adapter = adapter
        viewPager.currentItem = intent.getIntExtra("index", 0)
        viewPager.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                selectedFragment = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    @SuppressLint("InflateParams")
    private fun getTabView(text: String): View {
        val view = LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null)
        val tv = view.findViewById<TextView>(R.id.tabtext)
        tv.setTextColor(resources.getColor(R.color.white))
        tv.text = text
        tv.textSize = 18f
        return view
    }


    @SuppressLint("SimpleDateFormat")
    fun setDateFromDatePicker(date: Long) {
        calendar.timeInMillis = date
        viewModel.setDateFromDatePicker(calendar)
    }

    fun openPlaceActivity() {
        val intent = Intent(this, PlaceActivity::class.java)
        startActivityForResult(intent, 100)
        //resultLauncher.launch(intent)
    }

    /*var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "getResult", Toast.LENGTH_SHORT).show()
            val data: Intent? = result.data
        }
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.changePlace(true)

    }

}