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
import com.bhakti_sangrahalay.kundli.model.BasicKundliPlanetSubData

class KundliPlanetSubAdapter(val context: Context, private val arrayList: ArrayList<BasicKundliPlanetSubData>) : RecyclerView.Adapter<KundliPlanetSubAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.kundli_planet_sub_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.plaNameTV.text = arrayList[position].plaName
        holder.plaDegreeTV.text = arrayList[position].plaDeg
        holder.plaSlTV.text = arrayList[position].signLord
        holder.plaNlTV.text = arrayList[position].nakshLord
        holder.plaSbTV.text = arrayList[position].subLord
        holder.plaSsTV.text = arrayList[position].subSubLord
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plaNameTV: TextView = itemView.findViewById(R.id.pla_name_tv)
        val plaDegreeTV: TextView = itemView.findViewById(R.id.pla_degree_tv)
        val plaSlTV: TextView = itemView.findViewById(R.id.pla_sl_tv)
        val plaNlTV: TextView = itemView.findViewById(R.id.pla_nl_tv)
        val plaSbTV: TextView = itemView.findViewById(R.id.pla_sb_tv)
        val plaSsTV: TextView = itemView.findViewById(R.id.pla_ss_tv)
    }
}