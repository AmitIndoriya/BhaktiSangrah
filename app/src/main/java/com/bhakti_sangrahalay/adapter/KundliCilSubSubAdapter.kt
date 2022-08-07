package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.KPCilSubSubBean

class KundliCilSubSubAdapter(
    val context: Context,
    private val arrayList: ArrayList<KPCilSubSubBean>
) : RecyclerView.Adapter<KundliCilSubSubAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cil_sub_sub_list_item, parent, false)
        return MyView(itemView)

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.planetNameTV.text = arrayList[position].planetName.toString()
        holder.starTv.text = arrayList[position].star
        holder.subTv.text = arrayList[position].sub
        holder.subSubTv.text = arrayList[position].subSub
        holder.posStatusTv.text = arrayList[position].posStatus
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val planetNameTV: TextView = itemView.findViewById(R.id.val_pla_name_tv)
        val starTv: TextView = itemView.findViewById(R.id.val_star_tv)
        val subTv: TextView = itemView.findViewById(R.id.val_sub_tv)
        val subSubTv: TextView = itemView.findViewById(R.id.val_sub_sub_tv)
        val posStatusTv: TextView = itemView.findViewById(R.id.val_pos_status_tv)
    }


}