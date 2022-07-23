package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.PrastharashtakvargaBean

class KundliPrastharashtakvargaAdapter(
    val context: Context,
    val heading: Array<String>,
    private val arrayList: ArrayList<PrastharashtakvargaBean>
) : RecyclerView.Adapter<KundliPrastharashtakvargaAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.kundli_prastharashtakvarga_list_item, parent, false)
        return MyView(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.headingTV.text = heading[position]

        setListAdapter(holder.recyclerView, arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headingTV: TextView = itemView.findViewById(R.id.heading_tv)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view)
    }

    fun setListAdapter(
        recyclerView: RecyclerView,
        prastharashtakvargaBean: PrastharashtakvargaBean
    ) {
        val labelArray = arrayOf("SU", "MO", "MA", "ME", "JU", "VE", "SA", "AS")
        val valueArray = arrayOf(
            prastharashtakvargaBean.su,
            prastharashtakvargaBean.mo,
            prastharashtakvargaBean.ma,
            prastharashtakvargaBean.me,
            prastharashtakvargaBean.ju,
            prastharashtakvargaBean.ve,
            prastharashtakvargaBean.sa,
            prastharashtakvargaBean.asc
        )

        recyclerView.isNestedScrollingEnabled = false
        val horaListAdapter =
            KundliPrastharashtakvargaItemAdapter(context, labelArray, valueArray)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = horaListAdapter
    }
}