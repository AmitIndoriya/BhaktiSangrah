package com.bhakti_sangrahalay.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.FragmentViewPagerAdapter
import com.bhakti_sangrahalay.databinding.ActivityBirthDetailInputLayoutBinding
import com.bhakti_sangrahalay.ui.fragment.BirthDetailInputFragment
import com.bhakti_sangrahalay.ui.fragment.KundliListFragment
import com.bhakti_sangrahalay.viewmodel.BirthDetaiInputActivityViewModel
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import javax.inject.Inject

class BirthDetailInputActivityNew : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityBirthDetailInputLayoutBinding
    lateinit var viewModel: BirthDetaiInputActivityViewModel

    override fun attachViewModel() {
        val viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[BirthDetaiInputActivityViewModel::class.java]

    }

    override fun setTypeface() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initFont()
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        attachViewModel()
        binding = ActivityBirthDetailInputLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTypeface()
        setUpViewpager()
        setTitle(resources.getString(R.string.kundli))
    }

    private fun setUpViewpager() {
        supportActionBar?.elevation = 0F
        val tabs = resources.getStringArray(R.array.birth_detail_module_list)
        setViewPagerAdapter(tabs, getFragmentList())
    }

    private fun setViewPagerAdapter(tabTextArr: Array<String>, fragList: ArrayList<Fragment>) {
        val adapter = FragmentViewPagerAdapter(supportFragmentManager, fragList)
        binding.viewPager.adapter = adapter
        binding.tabLayout.tabMode = TabLayout.MODE_FIXED
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.setSelectedTabIndicatorColor(
            resources.getColor(
                R.color.aqua_marine,
                null
            )
        )

        for (i in 0..binding.tabLayout.tabCount) {
            binding.tabLayout.getTabAt(i)?.customView = getTabView(tabTextArr[i])
        }
    }
    private fun getTabView(text: String?): View? {
        @SuppressLint("InflateParams") val view =
            LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null)
        val tv = view.findViewById<TextView>(R.id.tabtext)
        tv.setTextColor(resources.getColor(R.color.white, null))
        tv.typeface = semiBoldTypeface
        tv.text = text
        tv.textSize = 18f
        return view
    }

    private fun getFragmentList(): ArrayList<Fragment> {

        val fragList = ArrayList<Fragment>()
        fragList.add(KundliListFragment.getInstance())
        fragList.add(BirthDetailInputFragment.getInstance())
        return fragList
    }
}