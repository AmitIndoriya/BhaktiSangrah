package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.model.HoraBean
import com.bhakti_sangrahalay.panchang.model.RahuKaalBean
import com.bhakti_sangrahalay.util.Utility
import java.util.ArrayList

class RahuKaalListAdapter(val context: Context, private val rahuKaalList: ArrayList<RahuKaalBean>) : RecyclerView.Adapter<RahuKaalListAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hora_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.horaName.text = rahuKaalList[position].date
        holder.horaDuration.text = rahuKaalList[position].from + " " + context.resources.getString(R.string.from) + " " + rahuKaalList[position].to

        holder.horaName.typeface = Utility.getSemiBoldTypeface(context)
        holder.horaDuration.typeface = Utility.getMediumTypeface(context)
        if (position == rahuKaalList.size - 1) {
            holder.lineDivider.visibility = View.GONE
        } else {
            holder.lineDivider.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return rahuKaalList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val horaName: TextView = itemView.findViewById(R.id.hora_name)
        val horaDuration: TextView = itemView.findViewById(R.id.hora_duration_tv)
        val lineDivider: View = itemView.findViewById(R.id.line_divider)
    }

}