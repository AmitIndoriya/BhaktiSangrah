package com.bhakti_sangrahalay.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.CharDashaBean
import com.bhakti_sangrahalay.util.Utility

class CharDashaAdapter(val context: Context, val charDashaList: ArrayList<CharDashaBean>) :
    RecyclerView.Adapter<CharDashaAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.char_dasha_item_list, parent, false)
        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.planetNameTV.text = charDashaList[position].planetName
        holder.durationTV.text = charDashaList[position].duration.toString() + " yr"
        holder.startDateTV.text = charDashaList[position].startYear
        holder.endDateTV.text = charDashaList[position].endYear

        holder.planetNameTV.typeface = Utility.getSemiBoldTypeface(context)
        holder.durationTV.typeface = Utility.getMediumTypeface(context)
        holder.startDateTV.typeface = Utility.getMediumTypeface(context)
        holder.endDateTV.typeface = Utility.getMediumTypeface(context)
    }

    override fun getItemCount(): Int {
        return charDashaList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val planetNameTV: TextView = itemView.findViewById(R.id.pla_name_tv)
        val durationTV: TextView = itemView.findViewById(R.id.duration_tv)
        val startDateTV: TextView = itemView.findViewById(R.id.start_date_tv)
        val endDateTV: TextView = itemView.findViewById(R.id.end_date_tv)

    }

}