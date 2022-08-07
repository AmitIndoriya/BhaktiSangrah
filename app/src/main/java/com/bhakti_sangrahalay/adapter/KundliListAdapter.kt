package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.ui.activity.BaseActivity

class KundliListAdapter(val context: Context) : RecyclerView.Adapter<KundliListAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.kundli_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.nameTV.text = "Amit"
        holder.dobTV.text = "23 June 1990, "
        holder.tobTV.text = "10:30 PM"
        holder.pobTV.text = "Jaipur"

        holder.nameTV.typeface = (context as BaseActivity).semiBoldTypeface
        holder.dobTV.typeface = context.mediumTypeface
        holder.tobTV.typeface = context.mediumTypeface
        holder.pobTV.typeface = context.mediumTypeface
    }

    override fun getItemCount(): Int {
        return 10
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.name_tv)
        val dobTV: TextView = itemView.findViewById(R.id.dob_tv)
        val tobTV: TextView = itemView.findViewById(R.id.tob_tv)
        val pobTV: TextView = itemView.findViewById(R.id.pob_tv)
    }
}