package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.model.HouseSignificatorsBean

class KundliHouseSignificatorsAdapter(
    val context: Context,
    private val arrayList: ArrayList<HouseSignificatorsBean>
) : RecyclerView.Adapter<KundliHouseSignificatorsAdapter.MyView>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.house_significators_item_layout, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.planetNameTV.text = (arrayList[position].houseNo+1).toString()
        holder.houseSignififedTv.text = arrayList[position].significator.replace(","," ")
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val planetNameTV: TextView = itemView.findViewById(R.id.house_no_tv)
        val houseSignififedTv: TextView = itemView.findViewById(R.id.significators_tv)
    }


}