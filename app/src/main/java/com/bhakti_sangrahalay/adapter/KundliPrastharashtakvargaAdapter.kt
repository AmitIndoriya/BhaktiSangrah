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
        val labelArray = arrayOf("SU", "MO", "MA", "ME", "JU", "VE", "SA", "AS", "TO")
        val valueArray = arrayOf(
            prastharashtakvargaBean.su,
            prastharashtakvargaBean.mo,
            prastharashtakvargaBean.ma,
            prastharashtakvargaBean.me,
            prastharashtakvargaBean.ju,
            prastharashtakvargaBean.ve,
            prastharashtakvargaBean.sa,
            prastharashtakvargaBean.asc,
            getTotalStr(prastharashtakvargaBean),
        )

        recyclerView.isNestedScrollingEnabled = false
        val horaListAdapter =
            KundliPrastharashtakvargaItemAdapter(context, labelArray, valueArray)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = horaListAdapter
    }

    fun getTotalStr(prastharashtakvargaBean: PrastharashtakvargaBean): String {
        val suArr = prastharashtakvargaBean.su.split(",")
        val moArr = prastharashtakvargaBean.mo.split(",")
        val maArr = prastharashtakvargaBean.ma.split(",")
        val meArr = prastharashtakvargaBean.me.split(",")
        val juArr = prastharashtakvargaBean.ju.split(",")
        val veArr = prastharashtakvargaBean.ve.split(",")
        val saArr = prastharashtakvargaBean.sa.split(",")
        val ascArr = prastharashtakvargaBean.asc.split(",")

        var totStr = ""
        for (i in 0..11) {
            val tot = suArr[i].toInt() + moArr[i].toInt() + maArr[i].toInt() +
                    meArr[i].toInt() + juArr[i].toInt() + veArr[i].toInt() +
                    saArr[i].toInt() + ascArr[i].toInt()
            totStr += tot.toString() + ","
        }
        totStr += ""
        return totStr
    }
}