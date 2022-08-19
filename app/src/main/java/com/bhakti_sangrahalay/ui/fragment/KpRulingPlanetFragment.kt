package com.bhakti_sangrahalay.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.KundliRulingPlanetAdapter
import com.bhakti_sangrahalay.kundli.model.KpRulingPlanetBean

class KpRulingPlanetFragment : BaseFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<KpRulingPlanetBean>


    companion object {
        fun getInstance(arrayList: ArrayList<KpRulingPlanetBean>) =
            KpRulingPlanetFragment().apply {
                arguments = Bundle(2).apply {
                    putSerializable("arrayList", arrayList)
                }
            }
    }

    override fun setTypeface() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            arrayList =
                bundle.getSerializable("arrayList") as ArrayList<KpRulingPlanetBean>
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(
                R.layout.frag_kundli_ruling_planet_layout,
                container,
                false
            )
        recyclerView = view.findViewById(R.id.recycler_view)
        setListData()
        return view
    }

    private fun setListData() {
        recyclerView.isNestedScrollingEnabled = false
        val kundliRulingPlanetAdapter =
            KundliRulingPlanetAdapter(requireActivity(), arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = kundliRulingPlanetAdapter


    }
}