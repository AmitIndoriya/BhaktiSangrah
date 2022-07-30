package com.bhakti_sangrahalay.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.KundliAvkahadaChakraAdapter
import com.bhakti_sangrahalay.kundli.model.BasicAvkahadaChakraBean

class KundliAvkahadaChakraFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var basicAvkahadaChakraBean: BasicAvkahadaChakraBean


    companion object {
        fun getInstance(basicAvkahadaChakraBean: BasicAvkahadaChakraBean) =
            KundliAvkahadaChakraFragment().apply {
                arguments = Bundle(2).apply {
                    putSerializable("basicAvkahadaChakraBean", basicAvkahadaChakraBean)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            basicAvkahadaChakraBean =
                bundle.getSerializable("basicAvkahadaChakraBean") as BasicAvkahadaChakraBean
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_avkahada_chakra_layout, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setListData()
        return view
    }

    private fun setListData() {
        val labelArray = arrayOf(
            resources.getString(R.string.paya),
            resources.getString(R.string.varna),
            resources.getString(R.string.yoni),
            resources.getString(R.string.gana),
            resources.getString(R.string.vasya),
            resources.getString(R.string.nadi),
            resources.getString(R.string.balance_of_dasha),
            resources.getString(R.string.lagna),
            resources.getString(R.string.lagnaLord),
            resources.getString(R.string.rasi),
            resources.getString(R.string.rasi_lord),
            resources.getString(R.string.nakshatra_pada),
            resources.getString(R.string.nakshatra_lord),
            resources.getString(R.string.julian_day),
            resources.getString(R.string.sun_sign_indian),
            resources.getString(R.string.sun_sign),
            resources.getString(R.string.ayanamsa),
            resources.getString(R.string.ayanamsa_name),
            resources.getString(R.string.obliquity),
            resources.getString(R.string.sideral_time)
        )
        val valueArray = arrayOf(
            basicAvkahadaChakraBean.paya,
            basicAvkahadaChakraBean.varna,
            basicAvkahadaChakraBean.yoni,
            basicAvkahadaChakraBean.gana,
            basicAvkahadaChakraBean.vasya,
            basicAvkahadaChakraBean.nadi,
            basicAvkahadaChakraBean.balanceOfdasha,
            basicAvkahadaChakraBean.lagna,
            basicAvkahadaChakraBean.lagnaLord,
            basicAvkahadaChakraBean.rasi,
            basicAvkahadaChakraBean.rasiLord,
            basicAvkahadaChakraBean.NakshatraPada,
            basicAvkahadaChakraBean.nakshatraLord,
            basicAvkahadaChakraBean.julianDay,
            basicAvkahadaChakraBean.sunSignIndian,
            basicAvkahadaChakraBean.sunSign,
            basicAvkahadaChakraBean.ayanamsa,
            basicAvkahadaChakraBean.ayanamsaName,
            basicAvkahadaChakraBean.obliquity,
            basicAvkahadaChakraBean.sideralTime
        )
        recyclerView.isNestedScrollingEnabled = false
        val horaListAdapter = KundliAvkahadaChakraAdapter(requireActivity(), labelArray, valueArray)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = horaListAdapter


    }
}