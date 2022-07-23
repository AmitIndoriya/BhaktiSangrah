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
import com.bhakti_sangrahalay.adapter.KundliPrastharashtakvargaAdapter
import com.bhakti_sangrahalay.kundli.model.PrastharashtakvargaBean

class KundliPrastharashtakvargaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<PrastharashtakvargaBean>


    companion object {
        fun getInstance(arrayList: ArrayList<PrastharashtakvargaBean>) =
            KundliPrastharashtakvargaFragment().apply {
                arguments = Bundle(2).apply {
                    putSerializable("arrayList", arrayList)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            arrayList = bundle.getSerializable("arrayList") as ArrayList<PrastharashtakvargaBean>
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.frag_kundli_prastharashtakvarga_layout, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setListData()
        return view
    }

    private fun setListData() {
        val plaArray =
            arrayOf("SUN", "MOON", "MARS", "MERCURY", "JUPITER", "VENUS", "SATURN", "RAHU")
        recyclerView.isNestedScrollingEnabled = false
        val horaListAdapter =
            KundliPrastharashtakvargaAdapter(requireActivity(), plaArray, arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = horaListAdapter


    }
}