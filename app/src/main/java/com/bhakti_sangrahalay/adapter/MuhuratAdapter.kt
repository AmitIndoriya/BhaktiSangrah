package com.bhakti_sangrahalay.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.util.Utility

class MuhuratAdapter(val context: Context, private val muhuratName: Array<String>, private val muhuratTime: ArrayList<ArrayList<String>>, private val colorCode: IntArray) : RecyclerView.Adapter<MuhuratAdapter.MyView>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.muhurat_item_layout, parent, false)

        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.muhuratName.text = muhuratName[position]
        holder.muhuratDuration.text = getFrommatedTime(muhuratTime[position])
        holder.cardview.setCardBackgroundColor(colorCode[position])
        holder.muhuratName.typeface = Utility.getSemiBoldTypeface(context)
        holder.muhuratDuration.typeface = Utility.getMediumTypeface(context)
    }

    override fun getItemCount(): Int {
        return muhuratName.size
    }

    private fun getFrommatedTime(list: ArrayList<String>): String {
        val startTime = list[0].split(",")
        val endTime = list[1].split(",")
        var formmatedStr = ""
        for (i in startTime.indices) {
            formmatedStr = formmatedStr + startTime[i] + " " + context.resources.getString(R.string.from) + " " + endTime[i].replace("\n", "")
        }
        return formmatedStr
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val muhuratName: TextView = itemView.findViewById(R.id.muhurat_name)
        val muhuratDuration: TextView = itemView.findViewById(R.id.muhurat_duration_tv)
        val cardview: CardView = itemView.findViewById(R.id.cardview)

    }


}