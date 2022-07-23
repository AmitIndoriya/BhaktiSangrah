package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.model.ChogdiyaBean
import com.bhakti_sangrahalay.util.Utility

class ChogdiaListAdapter(val context: Context, private val chogdiyaList: List<ChogdiyaBean>) : RecyclerView.Adapter<ChogdiaListAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hora_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.chogdiyaName.text = chogdiyaList[position].planetName
        holder.chogdiyaDuration.text = chogdiyaList[position].duration

        holder.chogdiyaName.typeface = Utility.getSemiBoldTypeface(context)
        holder.chogdiyaDuration.typeface = Utility.getMediumTypeface(context)
        if (position == chogdiyaList.size - 1) {
            holder.lineDivider.visibility = View.GONE
        } else {
            holder.lineDivider.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return chogdiyaList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chogdiyaName: TextView = itemView.findViewById(R.id.hora_name)
        val chogdiyaDuration: TextView = itemView.findViewById(R.id.hora_duration_tv)
        val lineDivider: View = itemView.findViewById(R.id.line_divider)
    }

}