package com.bhakti_sangrahalay.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.model.HoraBean
import com.bhakti_sangrahalay.util.Utility

class HoraListAdapter(val context: Context, val horaList: List<HoraBean>) : RecyclerView.Adapter<HoraListAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoraListAdapter.MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hora_list_item, parent, false)
        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: HoraListAdapter.MyView, position: Int) {
        holder.horaName.text = horaList[position].planetName
        holder.horaDuration.text = horaList[position].duration

        holder.horaName.typeface = Utility.getSemiBoldTypeface(context)
        holder.horaDuration.typeface = Utility.getMediumTypeface(context)
    }

    override fun getItemCount(): Int {
        return horaList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val horaName: TextView = itemView.findViewById(R.id.hora_name)
        val horaDuration: TextView = itemView.findViewById(R.id.hora_duration_tv)

    }

}