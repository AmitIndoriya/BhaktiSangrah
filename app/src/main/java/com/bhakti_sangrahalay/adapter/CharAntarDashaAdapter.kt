package com.bhakti_sangrahalay.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.CharAntaraDashaBean
import com.bhakti_sangrahalay.kundli.model.CharDashaBean
import com.bhakti_sangrahalay.util.Utility

class CharAntarDashaAdapter(val context: Context, val charDashaList: ArrayList<CharDashaBean>) :
    RecyclerView.Adapter<CharAntarDashaAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.char_antar_dasha_item_list, parent, false)
        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.planetNameTV.text = charDashaList[position].planetName
        holder.durationTV.text = charDashaList[position].duration.toString() + " Year"
        setRVAdapter(
            holder.recyclerView,
            charDashaList[position].charAntaraDashaList
        )
        holder.planetNameTV.typeface = Utility.getSemiBoldTypeface(context)
        holder.durationTV.typeface = Utility.getSemiBoldTypeface(context)
    }

    fun setRVAdapter(
        recyclerView: RecyclerView,
        arrayList: java.util.ArrayList<CharAntaraDashaBean>
    ) {
        recyclerView.isNestedScrollingEnabled = false
        val charAntarDashaListItemAdapter = CharAntarDashaListItemAdapter(context, arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = charAntarDashaListItemAdapter
    }
    override fun getItemCount(): Int {
        return charDashaList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val planetNameTV: TextView = itemView.findViewById(R.id.pla_name_tv)
        val durationTV: TextView = itemView.findViewById(R.id.duration_tv)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view)
    }

}