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
import com.bhakti_sangrahalay.adapter.KundliPlanetSignificationAdapter
import com.bhakti_sangrahalay.adapter.KundliPlanetSignificationView2Adapter
import com.bhakti_sangrahalay.kundli.model.PlanetSignificationView2Bean

class KundliPlanetSignificationView2Fragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<PlanetSignificationView2Bean>


    companion object {
        fun getInstance(arrayList: ArrayList<PlanetSignificationView2Bean>) =
            KundliPlanetSignificationView2Fragment().apply {
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
                bundle.getSerializable("arrayList") as ArrayList<PlanetSignificationView2Bean>
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(
                R.layout.frag_kundli_planet_signification_view2_layout,
                container,
                false
            )
        recyclerView = view.findViewById(R.id.recycler_view)
        setListData()
        return view
    }

    private fun setListData() {
        recyclerView.isNestedScrollingEnabled = false
        val kundliPlanetSignificationAdapter =
            KundliPlanetSignificationView2Adapter(requireActivity(), arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = kundliPlanetSignificationAdapter


    }
}