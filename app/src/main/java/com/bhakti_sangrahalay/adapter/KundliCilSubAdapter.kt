package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.KPCilSubBean

class KundliCilSubAdapter(
    val context: Context,
    private val arrayList: ArrayList<KPCilSubBean>
) : RecyclerView.Adapter<KundliCilSubAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cil_sub_list_item, parent, false)
        return MyView(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.houTV.text = arrayList[position].houseNo.toString()
        holder.t1Tv.text = arrayList[position].t1
        holder.t2Tv.text = arrayList[position].t2
        holder.t3Tv.text = arrayList[position].t3
        holder.t4Tv.text = arrayList[position].t4
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val houTV: TextView = itemView.findViewById(R.id.hou_tv)
        val t1Tv: TextView = itemView.findViewById(R.id.t1_tv)
        val t2Tv: TextView = itemView.findViewById(R.id.t2_tv)
        val t3Tv: TextView = itemView.findViewById(R.id.t3_tv)
        val t4Tv: TextView = itemView.findViewById(R.id.t4_tv)
    }


}