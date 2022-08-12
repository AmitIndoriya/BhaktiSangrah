package com.bhakti_sangrahalay.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.FragmentViewPagerAdapter
import com.bhakti_sangrahalay.contansts.Constants
import com.bhakti_sangrahalay.databinding.FragKundliOutputLayoutBinding
import com.bhakti_sangrahalay.ui.activity.KundliOutputActivity
import com.bhakti_sangrahalay.viewmodel.KundliOutputActivityViewModel
import com.google.android.material.tabs.TabLayout
import kotlin.properties.Delegates

class KundliOutputFragment : BaseFragment() {
    lateinit var binding: FragKundliOutputLayoutBinding

    // lateinit var arrayList: ArrayList<Fragment>
    lateinit var viewModel: KundliOutputActivityViewModel
    var type by Delegates.notNull<Int>()


    override fun setTypeface() {
    }

    companion object {
        fun getInstance(type: Int) =
            KundliOutputFragment().apply {
                arguments = Bundle(2).apply {
                    //putSerializable("arrayList", arrayList)
                    putInt("type", type)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            //  arrayList = bundle.getSerializable("arrayList") as ArrayList<Fragment>
            type = bundle.getInt("type")
        }
        viewModel = ViewModelProviders.of(activity!!).get(KundliOutputActivityViewModel::class.java)
    }

    private fun setViewPagerAdapter(tabTextArr: Array<String>, fragList: ArrayList<Fragment>) {
        val con = (requireActivity() as KundliOutputActivity)
        val adapter = FragmentViewPagerAdapter(childFragmentManager, fragList)
        binding.viewPager.adapter = adapter
        con.binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        con.binding.tabLayout.setupWithViewPager(binding.viewPager)
        for (i in 0..con.binding.tabLayout.tabCount) {
            con.binding.tabLayout.getTabAt(i)?.customView = con.getTabView(tabTextArr[i])
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragKundliOutputLayoutBinding.inflate(inflater, container, false)

        when (type) {
            Constants.BASIC_TYPE -> {
                val kundliModuleList = resources.getStringArray(R.array.kundli_module_list)
                setViewPagerAdapter(kundliModuleList, getKundliFragmentList())
            }
            Constants.KP_SYSTEM_TYPE -> {
                val kundliModuleList = resources.getStringArray(R.array.kp_module_list)
                setViewPagerAdapter(kundliModuleList, getKpSystemFragmentList())
            }
            Constants.SHODASHVARGA_TYPE -> {
                val kundliModuleList = resources.getStringArray(R.array.shosahvarga_module_list)
                setViewPagerAdapter(kundliModuleList, getShodashvargaFragmentList())
            }
            Constants.DASHA_TYPE -> {
                val kundliModuleList = resources.getStringArray(R.array.shosahvarga_module_list)
                setViewPagerAdapter(kundliModuleList, getDashFragmentList())

            }
        }


        return binding.root
    }

    private fun getKundliFragmentList(): ArrayList<Fragment> {

        val fragList = ArrayList<Fragment>()
        fragList.add(
            ChartFragment.getInstance(
                viewModel.getLagnaKundliPlanetRashiArray(),
                viewModel.getLagnaKundliPlanetRashiArray()[12],
                null,
                viewModel.getFormattedPlanetDegStr(requireActivity())
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
        fragList.add(KundliPlanetFragment.getInstance(viewModel.getPlanetsData(requireActivity())))
        fragList.add(KundliPlanetSubFragment.getInstance(viewModel.getPlanetsSubData(requireActivity())))
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
        fragList.add(KundliPlanetSubFragment.getInstance(viewModel.getKPPlanetsData(requireActivity())))
        fragList.add(KundliPlanetSubFragment.getInstance(viewModel.getKPCuspData(requireActivity())))
        fragList.add(
            KundliPlanetSignificationFrament.getInstance(
                viewModel.getKPPlanetSignificationData(requireActivity())
            )
        )
        fragList.add(
            KundliHouseSignificatorFragment.getInstance(
                viewModel.getKPHouseSignificatorsData(requireActivity())
            )
        )
        fragList.add(
            KundliPlanetSignificationView2Fragment.getInstance(
                viewModel.getPlanetSignifiactionView2Data()
            )
        )
        fragList.add(
            KundliNakshtraNadiFragment.getInstance(
                viewModel.getNakshtraNadiData(requireActivity())
            )
        )
        fragList.add(
            KundliCilSubSubFragment.getInstance(
                viewModel.getCilSubSubData(requireActivity())
            )
        )
        fragList.add(
            KPCilSubFragment.getInstance(
                viewModel.getCilSubData(requireActivity())
            )
        )

        return fragList
    }

    private fun getDashFragmentList(): ArrayList<Fragment> {
        val fragList = ArrayList<Fragment>()

        fragList.add(
            DasaFragment.getInstance()
        )
        fragList.add(
            CharDashaFragment.getInstance()
        )
        return fragList
    }

}