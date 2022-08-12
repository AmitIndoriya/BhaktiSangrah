package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.KundliChalitTableBean

class KundliChalitTableListAdapter(
    val context: Context,
    private val arrayList: ArrayList<KundliChalitTableBean>
) : RecyclerView.Adapter<KundliChalitTableListAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.chalit_table_list_item, parent, false)
        return MyView(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.bhavTV.text = arrayList[position].bhav.toString()
        holder.begSignTV.text = arrayList[position].bhBegSign
        holder.bhavBegTV.text = arrayList[position].bhBegDeg
        holder.midSignTV.text = arrayList[position].bhMidSign
        holder.bhavMidTV.text = arrayList[position].bhMidDeg
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bhavTV: TextView = itemView.findViewById(R.id.bhav_tv)
        val begSignTV: TextView = itemView.findViewById(R.id.beg_sign_tv)
        val bhavBegTV: TextView = itemView.findViewById(R.id.bhav_beg_tv)
        val midSignTV: TextView = itemView.findViewById(R.id.mid_sign_tv)
        val bhavMidTV: TextView = itemView.findViewById(R.id.bhav_mid_tv)
    }


}