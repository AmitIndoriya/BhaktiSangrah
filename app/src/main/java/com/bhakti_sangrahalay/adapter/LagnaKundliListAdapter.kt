package com.bhakti_sangrahalay.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.util.Utility

class LagnaKundliListAdapter(
    val context: Context,
    val planetName: Array<String>,
    val arrayList: ArrayList<String>
) :
    RecyclerView.Adapter<LagnaKundliListAdapter.MyView>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyView {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.lagna_kundli_list_item, parent, false)
        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.planetNameTV.text = planetName[position]
        holder.planetDegreeTV.text = arrayList[position]

        holder.planetNameTV.typeface = Utility.getSemiBoldTypeface(context)
        holder.planetDegreeTV.typeface = Utility.getMediumTypeface(context)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val planetNameTV: TextView = itemView.findViewById(R.id.pla_name_tv)
        val planetDegreeTV: TextView = itemView.findViewById(R.id.pla_degree_tv)

    }

}