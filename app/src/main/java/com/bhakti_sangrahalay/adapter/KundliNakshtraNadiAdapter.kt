package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.KPNakshatraNadiBean

class KundliNakshtraNadiAdapter(
    val context: Context,
    private val arrayList: ArrayList<KPNakshatraNadiBean>
) : RecyclerView.Adapter<KundliNakshtraNadiAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.nakshtra_nadi_list_item, parent, false)
        return MyView(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.plTV.text = arrayList[position].planet
        holder.stlTV.text = arrayList[position].starLord
        holder.sblTV.text = arrayList[position].subLord
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plTV: TextView = itemView.findViewById(R.id.pl_tv)
        val stlTV: TextView = itemView.findViewById(R.id.stl_tv)
        val sblTV: TextView = itemView.findViewById(R.id.sbl_tv)
    }


}