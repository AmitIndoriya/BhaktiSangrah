package com.bhakti_sangrahalay.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.FragmentViewPagerAdapter
import com.bhakti_sangrahalay.databinding.ActMatchMakingResultLayBinding
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.matchmaking.calculation.MatchMakingCalculation
import com.bhakti_sangrahalay.ui.fragment.CharDashaFragment
import com.bhakti_sangrahalay.ui.fragment.DasaFragment
import com.bhakti_sangrahalay.ui.fragment.MatchMakingInterpretationFragment
import com.bhakti_sangrahalay.ui.fragment.MatchMakingResultFragment
import com.bhakti_sangrahalay.viewmodel.MatchMakingResultActivityViewModel
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import javax.inject.Inject

class MatchMakingResultActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MatchMakingResultActivityViewModel
    lateinit var binding: ActMatchMakingResultLayBinding
    lateinit var boyDetail: BirthDetailBean
    lateinit var girlDetail: BirthDetailBean

    override fun attachViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[MatchMakingResultActivityViewModel::class.java]
        viewModel.getMatchMakingResult(boyDetail, girlDetail)

    }

    override fun setTypeface() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initFont()
        getIntentData()
        attachViewModel()
        binding = ActMatchMakingResultLayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //addFragment(MatchMakingResultFragment.getInstance())
        toolbarSetUp()
        val kundliModuleList = resources.getStringArray(R.array.kundli_module_list)
        setViewPagerAdapter(kundliModuleList, getFragmentList())
    }

    private fun getIntentData() {
        val bundle = intent.extras
        boyDetail = bundle?.getSerializable("boy_detail") as BirthDetailBean
        girlDetail = bundle.getSerializable("girl_detail") as BirthDetailBean
    }

   /* private fun addFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val tr = fm.beginTransaction()
        tr.add(R.id.fragment_container_view, fragment)
        tr.commitAllowingStateLoss()
    }*/
   private fun toolbarSetUp() {
       setSupportActionBar(binding.toolbar)
       binding.toolbar.setTitleTextColor(resources.getColor(R.color.white, null))
       //setUpNavigationView()
       supportActionBar?.title = resources.getString(R.string.kundli)
   }
    private fun setViewPagerAdapter(tabTextArr: Array<String>, fragList: ArrayList<Fragment>) {
        val adapter = FragmentViewPagerAdapter(supportFragmentManager, fragList)
        binding.viewPager.adapter = adapter
        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        for (i in 0..binding.tabLayout.tabCount) {
            binding.tabLayout.getTabAt(i)?.customView = getTabView(tabTextArr[i])
        }
    }

    private fun getTabView(text: String?): View? {
        @SuppressLint("InflateParams") val view =
            LayoutInflater.from(this).inflate(R.layout.custom_tab_layout, null)
        val tv = view.findViewById<TextView>(R.id.tabtext)
        tv.setTextColor(resources.getColor(R.color.white, null))
        tv.text = text
        return view
    }

    private fun getFragmentList(): ArrayList<Fragment> {
        val fragList = ArrayList<Fragment>()

        fragList.add(
            MatchMakingResultFragment.getInstance()
        )
        fragList.add(
            MatchMakingInterpretationFragment.getInstance()
        )
        return fragList
    }
}