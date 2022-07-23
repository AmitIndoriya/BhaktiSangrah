package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R

class KundliAshtakvargaAdapter(
    val context: Context,
    private val arrayList: ArrayList<String>
) : RecyclerView.Adapter<KundliAshtakvargaAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.kundli_ashtakvarga_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        val rowItem = arrayList[position]
        val arr = rowItem.split(",")
        holder.rnTV.text = (position + 1).toString()
        holder.suTV.text = arr[0]
        holder.moTV.text = arr[1]
        holder.maTV.text = arr[2]
        holder.meTV.text = arr[3]
        holder.juTV.text = arr[4]
        holder.veTV.text = arr[5]
        holder.saTV.text = arr[6]
        holder.totTV.text =
            (arr[0].toInt() + arr[1].toInt() + arr[2].toInt() + arr[3].toInt() + arr[4].toInt() + arr[5].toInt() + arr[6].toInt() ).toString()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rnTV: TextView = itemView.findViewById(R.id.rn_tv)
        val suTV: TextView = itemView.findViewById(R.id.su_tv)
        val moTV: TextView = itemView.findViewById(R.id.mo_tv)
        val maTV: TextView = itemView.findViewById(R.id.ma_tv)
        val meTV: TextView = itemView.findViewById(R.id.me_tv)
        val juTV: TextView = itemView.findViewById(R.id.ju_tv)
        val veTV: TextView = itemView.findViewById(R.id.ve_tv)
        val saTV: TextView = itemView.findViewById(R.id.sa_tv)
        val totTV: TextView = itemView.findViewById(R.id.tot_tv)
    }
}