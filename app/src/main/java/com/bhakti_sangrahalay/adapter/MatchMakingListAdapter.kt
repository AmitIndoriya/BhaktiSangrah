package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.matchmaking.model.GunaListBean
import com.bhakti_sangrahalay.ui.activity.BaseActivity

class MatchMakingListAdapter(
    val context: Context,
    private val arrayList: ArrayList<GunaListBean>
) :
    RecyclerView.Adapter<MatchMakingListAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_making_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {

        holder.gunaTV.text = arrayList[position].gunName
        holder.maximumTV.text = arrayList[position].maxVal.toString()
        holder.obtainedTV.text = arrayList[position].received.toString()
        holder.areaOfLifeTV.text = arrayList[position].areaOfLife

        holder.gunaTV.typeface = (context as BaseActivity).mediumTypeface
        holder.maximumTV.typeface = context.mediumTypeface
        holder.obtainedTV.typeface = context.mediumTypeface
        holder.areaOfLifeTV.typeface = context.mediumTypeface

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gunaTV: TextView = itemView.findViewById(R.id.guna_tv)
        val maximumTV: TextView = itemView.findViewById(R.id.max_tv)
        val obtainedTV: TextView = itemView.findViewById(R.id.obtained_tv)
        val areaOfLifeTV: TextView = itemView.findViewById(R.id.area_of_life_tv)
    }


}