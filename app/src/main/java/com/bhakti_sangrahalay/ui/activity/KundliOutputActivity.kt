package com.bhakti_sangrahalay.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.FragmentViewPagerAdapter
import com.bhakti_sangrahalay.databinding.ActivityKundliOutputLayoutBinding
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.ui.fragment.*
import com.bhakti_sangrahalay.viewmodel.KundliOutputActivityViewModel
import com.google.android.material.tabs.TabLayout
import me.ertugrul.lib.OnItemReselectedListener
import me.ertugrul.lib.OnItemSelectedListener


class KundliOutputActivity : BaseActivity() {
    lateinit var viewModel: KundliOutputActivityViewModel
    private lateinit var birthDetailBean: BirthDetailBean
    private lateinit var binding: ActivityKundliOutputLayoutBinding
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
        binding = ActivityKundliOutputLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(resources.getString(R.string.kundli))
        getDataFromIntent()
        setUpViewPager(2)
        setListener()
    }

    fun setListener() {
        findViewById<View>(R.id.more_super_bottom_bar)
        binding.bottomBar.setOnItemSelectListener(object : OnItemSelectedListener {
            override fun onItemSelect(pos: Int) {
                when (pos) {
                    0 -> {
                        setUpViewPager(0)
                    }
                    1 -> {
                        setUpViewPager(1)
                    }
                    2 -> {
                        setUpViewPager(2)
                    }
                    4 -> {

                        /* val popup = PopupMenu(
                             this@KundliOutputActivity,
                             binding.bottomBar.rootView.findViewById(R.id.more_super_bottom_bar)
                             //findViewById(R.id.more_super_bottom_bar)
                         )
                         val inflater = popup.menuInflater
                         inflater.inflate(R.menu.kundli_overflow_bottm_nav_item, popup.menu)
                         popup.show()
                         //openPopMenu()*/
                    }
                }
            }

        })

        binding.bottomBar.setOnItemReselectListener(object : OnItemReselectedListener {
            override fun onItemReselect(pos: Int) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun getDataFromIntent() {
        birthDetailBean = intent.extras?.get("BirthDetail") as BirthDetailBean
        viewModel.getKundliDataList(assets, birthDetailBean)
    }

    fun openPopMenu() {
        val popupMenu = PopupMenu(this, findViewById(R.id.more_super_bottom_bar))

        popupMenu.getMenuInflater()
            .inflate(R.menu.kundli_overflow_bottm_nav_item, popupMenu.getMenu())
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(menuItem: MenuItem): Boolean {
                Toast.makeText(
                    this@KundliOutputActivity,
                    "You Clicked " + menuItem.getTitle(),
                    Toast.LENGTH_SHORT
                ).show()
                return true
            }

        })
        // Showing the popup menu
        // Showing the popup menu
        popupMenu.show()
    }

    private fun setUpViewPager(pos: Int) {
        supportActionBar?.elevation = 0F
        when (pos) {
            0 -> {
                val kundliModuleList = resources.getStringArray(R.array.kundli_module_list)
                setViewPagerAdapter(kundliModuleList, getKundliFragmentList())
            }
            1 -> {
                val shodavargaModuleList = resources.getStringArray(R.array.shosahvarga_module_list)
                setViewPagerAdapter(shodavargaModuleList, getShodashvargaFragmentList())
            }
            2 -> {
                val kpModuleList = resources.getStringArray(R.array.kp_module_list)
                setViewPagerAdapter(kpModuleList, getKpSystemFragmentList())
            }
        }

    }

    private fun setViewPagerAdapter(tabTextArr: Array<String>, fragList: ArrayList<Fragment>) {
        val adapter = FragmentViewPagerAdapter(supportFragmentManager, fragList)
        binding.viewPager.adapter = adapter
        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.aqua_marine))

        for (i in 0..binding.tabLayout.tabCount) {
            binding.tabLayout.getTabAt(i)?.customView = getTabView(tabTextArr[i])
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

    private fun getShodashvargaFragmentList(): ArrayList<Fragment> {
        val fragList = ArrayList<Fragment>()
        fragList.add(
            ChartFragment.getInstance(
                viewModel.getLagnaKundliPlanetRashiArray(),
                viewModel.getLagnaKundliPlanetRashiArray()[12],
                null
            )
        )//1
        fragList.add(ChartFragment.getInstance(viewModel.getDrekkanaArray(), 0, null))//2
        fragList.add(ChartFragment.getInstance(viewModel.getChaturthamanshArray(), 0, null))//3
        fragList.add(ChartFragment.getInstance(viewModel.getChaturthamanshArray(), 0, null))//4
        fragList.add(ChartFragment.getInstance(viewModel.getSaptamamshaArray(), 0, null))//5
        fragList.add(ChartFragment.getInstance(viewModel.getNavmanshArray(), 0, null))//6
        fragList.add(ChartFragment.getInstance(viewModel.getDashamamshaArray(), 0, null))//7
        //fragList.add(ChartFragment.getInstance(viewModel.getD(),0,null))//8
        fragList.add(ChartFragment.getInstance(viewModel.getShodashamshaArray(), 0, null))//9
        fragList.add(ChartFragment.getInstance(viewModel.getVimshamshaArray(), 0, null))//10
        fragList.add(ChartFragment.getInstance(viewModel.getSaptavimshamshaArray(), 0, null))//11
        fragList.add(ChartFragment.getInstance(viewModel.getChaturvimshamshaArray(), 0, null))//12
        fragList.add(ChartFragment.getInstance(viewModel.getTrimshamshaArray(), 0, null))//13
        fragList.add(ChartFragment.getInstance(viewModel.getKhavedamshaArray(), 0, null))//14
        fragList.add(ChartFragment.getInstance(viewModel.getAkshvedamshaArray(), 0, null))//15
        fragList.add(ChartFragment.getInstance(viewModel.getShashtiamshaArray(), 0, null))//16


        return fragList
    }

    private fun getKpSystemFragmentList(): ArrayList<Fragment> {
        val fragList = ArrayList<Fragment>()
        val chalitArr: IntArray = viewModel.getKPKundliPlanetRashiArray()
        fragList.add(
            ChartFragment.getInstance(
                chalitArr,
                chalitArr[12],
                viewModel.getKpDegreeArray()
            )
        )
        val kpRashiArr: IntArray = viewModel.getKPLagnaRashiKundliPlanetsRashiArray()
        fragList.add(ChartFragment.getInstance(kpRashiArr, kpRashiArr[12], null))
        fragList.add(KundliPlanetSubFragment.getInstance(viewModel.getKPPlanetsData(this)))
        fragList.add(KundliPlanetSubFragment.getInstance(viewModel.getKPCuspData(this)))
        fragList.add(
            KundliPlanetSignificationFrament.getInstance(
                viewModel.getKPPlanetSignificationData(this)
            )
        )
        fragList.add(
            KundliHouseSignificatorFragment.getInstance(
                viewModel.getKPHouseSignificatorsData(this)
            )
        )
        fragList.add(
            KundliPlanetSignificationView2Fragment.getInstance(
                viewModel.getPlanetSignifiactionView2Data()
            )
        )
        fragList.add(
            KundliNakshtraNadiFragment.getInstance(
                viewModel.getNakshtraNadiData(this)
            )
        )
        fragList.add(
            KundliCilSubSubFragment.getInstance(
                viewModel.getCilSubSubData(this)
            )
        )
        fragList.add(
            KPCilSubFragment.getInstance(
                viewModel.getCilSubData(this)
            )
        )

        return fragList
    }
}