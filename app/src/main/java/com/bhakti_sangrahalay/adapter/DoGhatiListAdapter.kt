package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.model.DoGhatiBean
import com.bhakti_sangrahalay.util.Utility

class DoGhatiListAdapter(val context: Context, private val doGhatiList: List<DoGhatiBean>) : RecyclerView.Adapter<DoGhatiListAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hora_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.horaName.text = doGhatiList[position].planetName + "(" + doGhatiList[position].planetMeaning + ")"
        holder.horaDuration.text = doGhatiList[position].duration

        holder.horaName.typeface = Utility.getSemiBoldTypeface(context)
        holder.horaDuration.typeface = Utility.getMediumTypeface(context)
        if (position == doGhatiList.size - 1) {
            holder.lineDivider.visibility = View.GONE
        } else {
            holder.lineDivider.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return doGhatiList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val horaName: TextView = itemView.findViewById(R.id.hora_name)
        val horaDuration: TextView = itemView.findViewById(R.id.hora_duration_tv)
        val lineDivider: View = itemView.findViewById(R.id.line_divider)
    }

}