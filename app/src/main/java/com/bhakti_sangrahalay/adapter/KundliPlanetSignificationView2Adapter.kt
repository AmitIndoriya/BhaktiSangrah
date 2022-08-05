package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.PlanetSignificationView2Bean

class KundliPlanetSignificationView2Adapter(
    val context: Context,
    private val arrayList: ArrayList<PlanetSignificationView2Bean>
) : RecyclerView.Adapter<KundliPlanetSignificationView2Adapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.planet_signification_view2_list_item, parent, false)
        return MyView(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.planetNameTV.text = arrayList[position].plaNo
        holder.l1Tv.text = arrayList[position].l1
        holder.l2Tv.text = arrayList[position].l2
        holder.l3Tv.text = arrayList[position].l3
        holder.l4Tv.text = arrayList[position].l4
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val planetNameTV: TextView = itemView.findViewById(R.id.pla_name_tv)
        val l1Tv: TextView = itemView.findViewById(R.id.l1_tv)
        val l2Tv: TextView = itemView.findViewById(R.id.l2_tv)
        val l3Tv: TextView = itemView.findViewById(R.id.l3_tv)
        val l4Tv: TextView = itemView.findViewById(R.id.l4_tv)
    }


}