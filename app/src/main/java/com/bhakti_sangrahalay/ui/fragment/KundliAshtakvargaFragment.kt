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
import com.bhakti_sangrahalay.adapter.KundliAshtakvargaAdapter

class KundliAshtakvargaFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    lateinit var arrayList: ArrayList<String>


    companion object {
        fun getInstance(arrayList: ArrayList<String>) = KundliAshtakvargaFragment().apply {
            arguments = Bundle(2).apply {
                putSerializable("arrayList", arrayList)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            arrayList = bundle.getSerializable("arrayList") as ArrayList<String>
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_kundli_ashtakvarga_layout, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setListData()
        return view
    }

    private fun setListData() {
        recyclerView.isNestedScrollingEnabled = false
        val horaListAdapter = KundliAshtakvargaAdapter(requireActivity(), arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = horaListAdapter
    }
}