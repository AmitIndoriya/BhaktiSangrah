package com.bhakti_sangrahalay.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.MainActivity
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.contansts.GlobalVariables
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.ui.activitynew.MoreItemActivity
import com.bhakti_sangrahalay.ui.activitynew.VartKathaHomeActivity

class HomeGridRVAdapter(
    private val context: Context,
    private val nameList: Array<String>,
    private val drawableBgList: IntArray,
    private val drawableList: IntArray
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
        holder.textView.typeface = (context as BaseActivity).mediumTypeface
        holder.rootView.setOnClickListener {
            when (position) {
                0 -> {
                    val bundle = Bundle()
                    bundle.putInt("type", GlobalVariables.aarti)
                    bundle.putString("title", context.resources.getString(R.string.aartiyan))
                    val intent = Intent(context, MoreItemActivity::class.java)
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                }
                1 -> {
                    val bundle = Bundle()
                    bundle.putInt("type", GlobalVariables.chalisha)
                    bundle.putString("title", context.resources.getString(R.string.chalisha))
                    val intent = Intent(context, MoreItemActivity::class.java)
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                }
                2 -> {
                    val intent = Intent(context, VartKathaHomeActivity::class.java)
                    context.startActivity(intent)
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