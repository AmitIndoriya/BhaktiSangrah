package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.BasicKundliPlanetData

class KundliPlanetAdapter(val context: Context, private val arrayList: ArrayList<BasicKundliPlanetData>) : RecyclerView.Adapter<KundliPlanetAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.kundli_planet_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.plaNameTV.text = arrayList[position].plaName
        holder.plaSignTV.text = arrayList[position].sign
        holder.plaDegreeTV.text = arrayList[position].degree
        holder.plaNakshTV.text = arrayList[position].naks
        holder.plaRelTV.text = arrayList[position].plaCharan
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plaNameTV: TextView = itemView.findViewById(R.id.pla_name_tv)
        val plaSignTV: TextView = itemView.findViewById(R.id.pla_sign_tv)
        val plaDegreeTV: TextView = itemView.findViewById(R.id.pla_degree_tv)
        val plaNakshTV: TextView = itemView.findViewById(R.id.pla_naksh_tv)
        val plaRelTV: TextView = itemView.findViewById(R.id.pla_rel_tv)
    }
}