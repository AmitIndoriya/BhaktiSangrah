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
import com.bhakti_sangrahalay.adapter.KundliCilSubAdapter
import com.bhakti_sangrahalay.kundli.model.KPCilSubBean

class KPCilSubFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<KPCilSubBean>


    companion object {
        fun getInstance(arrayList: ArrayList<KPCilSubBean>) =
            KPCilSubFragment().apply {
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
                bundle.getSerializable("arrayList") as ArrayList<KPCilSubBean>
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(
                R.layout.frag_kundli_cil_sub_layout,
                container,
                false
            )
        recyclerView = view.findViewById(R.id.recycler_view)
        setListData()
        return view
    }

    private fun setListData() {
        recyclerView.isNestedScrollingEnabled = false
        val kundliCilSubAdapter =
            KundliCilSubAdapter(requireActivity(), arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = kundliCilSubAdapter


    }
}