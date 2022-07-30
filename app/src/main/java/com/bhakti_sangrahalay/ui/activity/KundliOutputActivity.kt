package com.bhakti_sangrahalay.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.FragmentViewPagerAdapter
import com.bhakti_sangrahalay.databinding.ActKundliOutputLayoutBinding
import com.bhakti_sangrahalay.ui.fragment.*
import com.bhakti_sangrahalay.viewmodel.KundliOutputActivityViewModel
import com.google.android.material.tabs.TabLayout

class KundliOutputActivity : BaseActivity() {
    lateinit var viewModel: KundliOutputActivityViewModel
    private lateinit var binding: ActKundliOutputLayoutBinding
    override fun attachViewModel() {
        val viewModelProvider =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
        viewModel = viewModelProvider[KundliOutputActivityViewModel::class.java]
    }

    override fun setTypeface() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachViewModel()
        binding = ActKundliOutputLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(resources.getString(R.string.kundli))
        setUpViewPager()
    }

    private fun setUpViewPager() {
        supportActionBar?.elevation = 0F
        val adapter = FragmentViewPagerAdapter(supportFragmentManager, getKundliFragmentList())
        binding.viewPager.adapter = adapter
        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.aqua_marine))
        val tabTexts = resources.getStringArray(R.array.kundli_module_list)
        for (i in 0..binding.tabLayout.tabCount) {
            binding.tabLayout.getTabAt(i)?.customView = getTabView(tabTexts[i])
        }
    }

    private fun getTabView(text: String?): View? {
        @SuppressLint("InflateParams") val view =
            LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null)
        val tv = view.findViewById<TextView>(R.id.tabtext)
        tv.setTextColor(resources.getColor(R.color.white))
        tv.text = text
        return view
    }

    private fun getKundliFragmentList(): ArrayList<Fragment> {
        viewModel.getKundliDataList(assets)
        val fragList = ArrayList<Fragment>()
        fragList.add(
            ChartFragment.getInstance(
                viewModel.getLagnaKundliPlanetRashiArray(),
                viewModel.getLagnaKundliPlanetRashiArray()[12],
                null
            )
        )
        fragList.add(
            ChartFragment.getInstance(
                viewModel.getNavmanshKundliPlanetRashiArray(),
                viewModel.getNavmanshKundliPlanetRashiArray()[12],
                null
            )
        )
        fragList.add(
            ChartFragment.getInstance(
                viewModel.getChandraKundliPlanetRashiArray(),
                viewModel.getChandraKundliPlanetRashiArray()[12],
                null
            )
        )
        val chalitArr = viewModel.getChalitKundliPlanetRashiArray()
        fragList.add(
            ChartFragment.getInstance(
                chalitArr,
                chalitArr[12],
                viewModel.getKpDegreeArray()
            )
        )
        fragList.add(KundliPlanetFragment.getInstance(viewModel.getPlanetsData(this)))
        fragList.add(KundliPlanetSubFragment.getInstance(viewModel.getPlanetsSubData(this)))
        fragList.add(KundliPanchangFragment.getInstance(viewModel.getPanchangData()))
        fragList.add(KundliAshtakvargaFragment.getInstance(viewModel.getAshtakvargaData()))
        fragList.add(
            ChartFragment.getInstance(
                viewModel.getLagnaKundliPlanetRashiArray(),
                viewModel.getKarakanshLagna(),
                null
            )
        )
        fragList.add(
            ChartFragment.getInstance(
                viewModel.getNavmanshKundliPlanetRashiArray(),
                viewModel.getKarakanshLagna(),
                null
            )
        )
        fragList.add(KundliPrastharashtakvargaFragment.getInstance(viewModel.getPrastharashtakvargaData()))
        fragList.add(KundliAvkahadaChakraFragment.getInstance(viewModel.getAvkahadaChakraData()))

        return fragList
    }
}