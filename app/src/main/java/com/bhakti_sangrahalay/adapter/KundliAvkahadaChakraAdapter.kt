package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R

class KundliAvkahadaChakraAdapter(
    val context: Context,
    private val labelArray: Array<String>,
    private val valueArray: Array<String>
) : RecyclerView.Adapter<KundliAvkahadaChakraAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.kundli_avkahada_chakra_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.labelTV.text = labelArray[position]
        holder.valueTV.text = valueArray[position]
    }

    override fun getItemCount(): Int {
        return labelArray.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labelTV: TextView = itemView.findViewById(R.id.label_tv)
        val valueTV: TextView = itemView.findViewById(R.id.value_tv)

    }
}