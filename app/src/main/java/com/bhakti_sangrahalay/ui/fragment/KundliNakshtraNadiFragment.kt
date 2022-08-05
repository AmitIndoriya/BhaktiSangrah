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
import com.bhakti_sangrahalay.adapter.KundliNakshtraNadiAdapter
import com.bhakti_sangrahalay.adapter.KundliPlanetSignificationView2Adapter
import com.bhakti_sangrahalay.kundli.model.KPNakshatraNadiBean

class KundliNakshtraNadiFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<KPNakshatraNadiBean>


    companion object {
        fun getInstance(arrayList: ArrayList<KPNakshatraNadiBean>) =
            KundliNakshtraNadiFragment().apply {
                arguments = Bundle(2).apply {
                    putSerializable("arrayList", arrayList)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            arrayList =
                bundle.getSerializable("arrayList") as ArrayList<KPNakshatraNadiBean>
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(
                R.layout.frag_kundli_nakshtra_nadi_layout,
                container,
                false
            )
        recyclerView = view.findViewById(R.id.recycler_view)
        setListData()
        return view
    }

    private fun setListData() {
        recyclerView.isNestedScrollingEnabled = false
        val kundliNakshtraNadiAdapter =
            KundliNakshtraNadiAdapter(requireActivity(), arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = kundliNakshtraNadiAdapter


    }
}