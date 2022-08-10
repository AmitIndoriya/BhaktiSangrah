package com.bhakti_sangrahalay.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.activity.PlaceActivity
import com.bhakti_sangrahalay.adapter.FragmentViewPagerAdapter
import com.bhakti_sangrahalay.databinding.ActivityPanchangHomeBinding
import com.bhakti_sangrahalay.fragment.*
import com.bhakti_sangrahalay.viewmodel.PanchangViewModel

import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject


class PanchangActivity : BaseActivity() {
    @Inject
    lateinit var preferences: SharedPreferences
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: ActivityPanchangHomeBinding
    lateinit var viewModel: PanchangViewModel
    var selectedFragment = 0
    var calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
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

        super.onCreate(savedInstanceState)
        initFont()
        attachViewModel()
        binding = ActivityPanchangHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(resources.getString(R.string.panchag_aur_muhurat))

        setUpViewPagerAdapter()
        setUpTabLayoutWithViewPager()

    }

    override fun attachViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[PanchangViewModel::class.java]
    }

    override fun setTypeface() {
    }

    private fun setUpTabLayoutWithViewPager() {
        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        supportActionBar?.elevation = 0F
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.setSelectedTabIndicatorColor(
            resources.getColor(
                R.color.aqua_marine,
                null
            )
        )
        val tabTexts = resources.getStringArray(R.array.panchang_tab)
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i)
            if (tab != null) {
                tab.customView = getTabView(tabTexts[i])
            }
        }
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
        val fragList = getFragmentList()
        val adapter = FragmentViewPagerAdapter(supportFragmentManager, fragList)
        binding.viewPager.adapter = adapter
        binding.viewPager.currentItem = intent.getIntExtra("index", 0)
        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
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
        tv.setTextColor(resources.getColor(R.color.white, null))
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