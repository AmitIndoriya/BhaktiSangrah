package com.bhakti_sangrahalay.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.MainActivity
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.ui.activity.BirthDetaiInputActivity
import com.bhakti_sangrahalay.ui.activity.PanchangActivity

class HomeGridRVAdapter(
    private val context: Context,
    private val nameList: List<String>,
    private val drawableBgList: List<Int>,
    private val drawableList: List<Int>
) : RecyclerView.Adapter<HomeGridRVAdapter.MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_grid_item_layout, parent, false)
        return MyView(itemView)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.textView.text = nameList[position]
        holder.imageView.setBackgroundResource(drawableBgList[position])
        holder.imageView.setImageResource(drawableList[position])
        holder.rootView.setOnClickListener {
            when (position) {
                0 -> {
                    context.startActivity(Intent(context, PanchangActivity::class.java))
                }
                1 -> {
                    context.startActivity(Intent(context, BirthDetaiInputActivity::class.java))
                }
                2 -> {
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
                3 -> {
                    context.startActivity(Intent(context, MainActivity::class.java))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val textView: TextView = itemView.findViewById(R.id.text)
        val rootView: View = itemView.findViewById(R.id.root)


    }

}