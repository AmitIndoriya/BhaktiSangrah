package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.model.PlanetSignificationBean

class KundliPlanetSignificationAdapter(
    val context: Context,
    private val arrayList: ArrayList<PlanetSignificationBean>
) : RecyclerView.Adapter<KundliPlanetSignificationAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.planet_signification_list_item, parent, false)
        return MyView(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.planetNameTV.text = arrayList[position].planetName
        holder.houseSignififedTv.text = arrayList[position].houseSignified
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val planetNameTV: TextView = itemView.findViewById(R.id.pla_name_tv)
        val houseSignififedTv: TextView = itemView.findViewById(R.id.house_signififed_tv)
    }


}