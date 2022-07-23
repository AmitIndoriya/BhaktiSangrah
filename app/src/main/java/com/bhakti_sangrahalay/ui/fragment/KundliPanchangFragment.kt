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
import com.bhakti_sangrahalay.adapter.KundliPanchangAdapter
import com.bhakti_sangrahalay.kundli.model.BasicKundliPanchangBean

class KundliPanchangFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var kundliPanchangBean: BasicKundliPanchangBean


    companion object {
        fun getInstance(kundliPanchangBean: BasicKundliPanchangBean) =
            KundliPanchangFragment().apply {
                arguments = Bundle(2).apply {
                    putSerializable("kundliPanchangBean", kundliPanchangBean)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            kundliPanchangBean =
                bundle.getSerializable("kundliPanchangBean") as BasicKundliPanchangBean
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_kundli_panchang_layout, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setListData()
        return view
    }

    private fun setListData() {
        val labelArray = arrayOf(
            resources.getString(R.string.paksha),
            resources.getString(R.string.tithi),
            resources.getString(R.string.paksha),
            resources.getString(R.string.nakshatra),
            resources.getString(R.string.hindu_day),
            resources.getString(R.string.english_day),
            resources.getString(R.string.yoga),
            resources.getString(R.string.karana),
            resources.getString(R.string.sun_rise),
            resources.getString(R.string.sun_set)
        )
        val valueArray = arrayOf(
            kundliPanchangBean.paksha,
            kundliPanchangBean.tithi,
            kundliPanchangBean.paksha,
            kundliPanchangBean.nakshatra,
            kundliPanchangBean.hinduDay,
            kundliPanchangBean.engDay,
            kundliPanchangBean.yoga,
            kundliPanchangBean.karan,
            kundliPanchangBean.sunRiseTime,
            kundliPanchangBean.sunSetTime
        )
        recyclerView.isNestedScrollingEnabled = false
        val horaListAdapter = KundliPanchangAdapter(requireActivity(), labelArray, valueArray)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = horaListAdapter


    }
}