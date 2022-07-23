package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.model.PanchakBean
import com.bhakti_sangrahalay.util.Utility

class PanchakListAdapter(val context: Context, private val panchakBean: PanchakBean) : RecyclerView.Adapter<PanchakListAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.panchak_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.panchakStartDate.text = panchakBean.panchakTimeList[position].startDate + " " + panchakBean.panchakTimeList[position].startMonth + " (" + panchakBean.panchakTimeList[position].startDay + ")"
        holder.panchakStartTime.text = panchakBean.panchakTimeList[position].startTime
        holder.panchakEndDate.text = panchakBean.panchakTimeList[position].endDate + " " + panchakBean.panchakTimeList[position].endMonth + " (" + panchakBean.panchakTimeList[position].endDay + ")"
        holder.panchakEntTime.text = panchakBean.panchakTimeList[position].endTime

        holder.panchakStartDate.typeface = Utility.getSemiBoldTypeface(context)
        holder.panchakStartTime.typeface = Utility.getMediumTypeface(context)
        holder.panchakEndDate.typeface = Utility.getSemiBoldTypeface(context)
        holder.panchakEntTime.typeface = Utility.getMediumTypeface(context)
        if (position == panchakBean.panchakTimeList.size - 1) {
            holder.lineDivider.visibility = View.GONE
        } else {
            holder.lineDivider.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return panchakBean.panchakTimeList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val panchakStartDate: TextView = itemView.findViewById(R.id.panchak_start_date)
        val panchakStartTime: TextView = itemView.findViewById(R.id.panchak_start_time)
        val panchakEndDate: TextView = itemView.findViewById(R.id.panchak_end_date)
        val panchakEntTime: TextView = itemView.findViewById(R.id.panchak_end_time)
        val lineDivider: View = itemView.findViewById(R.id.line_divider)
    }

}