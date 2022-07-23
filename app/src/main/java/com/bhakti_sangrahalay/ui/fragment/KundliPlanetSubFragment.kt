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
import com.bhakti_sangrahalay.adapter.KundliPlanetAdapter
import com.bhakti_sangrahalay.adapter.KundliPlanetSubAdapter
import com.bhakti_sangrahalay.kundli.model.BasicKundliPlanetSubData

class KundliPlanetSubFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    lateinit var arrayList: ArrayList<BasicKundliPlanetSubData>


    companion object {
        fun getInstance(arrayList: ArrayList<BasicKundliPlanetSubData>) = KundliPlanetSubFragment().apply {
            arguments = Bundle(2).apply {
                putSerializable("arrayList", arrayList)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            arrayList = bundle.getSerializable("arrayList") as ArrayList<BasicKundliPlanetSubData>
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_kundli_planet_sub_layout, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setListData()
        return view
    }

    private fun setListData() {
        recyclerView.isNestedScrollingEnabled = false
        val horaListAdapter = requireActivity().let { KundliPlanetSubAdapter(it, arrayList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = horaListAdapter
    }
}