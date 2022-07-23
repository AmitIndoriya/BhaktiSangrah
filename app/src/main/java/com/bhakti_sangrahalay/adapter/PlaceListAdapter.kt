package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.activity.PlaceActivity
import com.bhakti_sangrahalay.model.PlaceModel
import com.bhakti_sangrahalay.util.Utility

class PlaceListAdapter(val context: Context, private val placeList: ArrayList<PlaceModel>) : RecyclerView.Adapter<PlaceListAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.place_item_layout, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.placeTV.text = placeList[position].city + ", " + placeList[position].state + ", " + placeList[position].country
        holder.placeTV.setOnClickListener {
            Utility.saveObejectInPreference((context as PlaceActivity).preferences, placeList[position])
            val intent = Intent()
           /* context.setResult(123, intent)
            context.finish()*/
        }
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeTV: TextView = itemView.findViewById(R.id.place_tv)

    }

}