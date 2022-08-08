package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.ui.activity.BaseActivity

class KundliListAdapter(val context: Context, val birthDetailBeanList: ArrayList<BirthDetailBean>) :
    RecyclerView.Adapter<KundliListAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.kundli_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        val birthDetailBean = birthDetailBeanList[position]
        val dateTimeBean = birthDetailBeanList[position].dateTimeBean
        val placeBean = birthDetailBeanList[position].placeBean
        holder.nameTV.text = birthDetailBean.name
        holder.dobTV.text =
            dateTimeBean.day + " " + dateTimeBean.month + " " + dateTimeBean.year + " | "
        holder.tobTV.text = dateTimeBean.hrs + " " + dateTimeBean.min + " " + dateTimeBean.sec
        holder.pobTV.text = placeBean.place

        holder.nameTV.typeface = (context as BaseActivity).semiBoldTypeface
        holder.dobTV.typeface = context.mediumTypeface
        holder.tobTV.typeface = context.mediumTypeface
        holder.pobTV.typeface = context.mediumTypeface
    }

    override fun getItemCount(): Int {
        return birthDetailBeanList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.name_tv)
        val dobTV: TextView = itemView.findViewById(R.id.dob_tv)
        val tobTV: TextView = itemView.findViewById(R.id.tob_tv)
        val pobTV: TextView = itemView.findViewById(R.id.pob_tv)
    }
}