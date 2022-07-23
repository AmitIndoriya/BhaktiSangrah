package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R

class KundliPrastharashtakvargaItemAdapter(
    val context: Context,
    val labelArray: Array<String>,
    val valueArray: Array<String>
) : RecyclerView.Adapter<KundliPrastharashtakvargaItemAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.kundli_prastharashtakvarga_item_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.labelTV.text = labelArray[position]
        val values = valueArray[position].split(",")

        val viewList = holder.getViewList()
        for (i in 0..10) {
            viewList[i].text = values[i]
        }
    }

    override fun getItemCount(): Int {
        return labelArray.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val labelTV: TextView = itemView.findViewById(R.id.b0_tv)
        val b1: TextView = itemView.findViewById(R.id.b1_tv)
        val b2: TextView = itemView.findViewById(R.id.b2_tv)
        val b3: TextView = itemView.findViewById(R.id.b3_tv)
        val b4: TextView = itemView.findViewById(R.id.b4_tv)
        val b5: TextView = itemView.findViewById(R.id.b5_tv)
        val b6: TextView = itemView.findViewById(R.id.b6_tv)
        val b7: TextView = itemView.findViewById(R.id.b7_tv)
        val b8: TextView = itemView.findViewById(R.id.b8_tv)
        val b9: TextView = itemView.findViewById(R.id.b9_tv)
        val b10: TextView = itemView.findViewById(R.id.b10_tv)
        val b11: TextView = itemView.findViewById(R.id.b11_tv)
        val b12: TextView = itemView.findViewById(R.id.b12_tv)
        val b13: TextView = itemView.findViewById(R.id.b13_tv)
        fun getViewList(): ArrayList<TextView> {
            var viewList = ArrayList<TextView>()
            viewList.add(b1)
            viewList.add(b2)
            viewList.add(b3)
            viewList.add(b4)
            viewList.add(b5)
            viewList.add(b6)
            viewList.add(b7)
            viewList.add(b8)
            viewList.add(b9)
            viewList.add(b10)
            viewList.add(b11)
            viewList.add(b12)
            viewList.add(b13)
            return viewList
        }
    }
}