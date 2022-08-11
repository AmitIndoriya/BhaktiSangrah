package com.bhakti_sangrahalay.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.DasaBean
import com.bhakti_sangrahalay.ui.fragment.DasaFragment
import com.bhakti_sangrahalay.util.Utility

class DashaListAdapter(
    val context: Context,
    val fragment: Fragment,
    private val horaList: List<DasaBean>
) : RecyclerView.Adapter<DashaListAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.hora_list_item, parent, false)
        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.horaName.text = horaList[position].planetName
        holder.horaDuration.text = horaList[position].dasaTimeStr

        holder.horaName.typeface = Utility.getSemiBoldTypeface(context)
        holder.horaDuration.typeface = Utility.getMediumTypeface(context)
        holder.rootLayout.setOnClickListener { (fragment as DasaFragment).changeDasaOnclick(position) }

    }

    override fun getItemCount(): Int {
        return horaList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val horaName: TextView = itemView.findViewById(R.id.hora_name)
        val horaDuration: TextView = itemView.findViewById(R.id.hora_duration_tv)
        val rootLayout: LinearLayout = itemView.findViewById(R.id.root_layout)


    }

}