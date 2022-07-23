package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.model.FestivalModel
import com.bhakti_sangrahalay.util.Utility

class MonthFestivalAdapter(val context: Context, val festList: ArrayList<FestivalModel>) : RecyclerView.Adapter<MonthFestivalAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hora_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.festName.text = festList[position].festvalName
        holder.festDate.text = festList[position].festvalDate

        holder.festName.typeface = Utility.getSemiBoldTypeface(context)
        holder.festDate.typeface = Utility.getMediumTypeface(context)
        if (position == festList.size - 1) {
            holder.lineDivider.visibility = View.GONE
        } else {
            holder.lineDivider.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return festList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val festDate: TextView = itemView.findViewById(R.id.hora_name)
        val festName: TextView = itemView.findViewById(R.id.hora_duration_tv)
        val lineDivider: View = itemView.findViewById(R.id.line_divider)
    }

}