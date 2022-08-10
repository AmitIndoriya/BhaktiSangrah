package com.bhakti_sangrahalay.adapternew

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.ui.activitynew.KathaActivity

class PauranikKathaAdapter(
    val context: Context,
    private val nameList: Array<String>,
    private val imageList: IntArray,
    private val rowFileList: IntArray
) : RecyclerView.Adapter<PauranikKathaAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.pauranik_list_item, parent, false)
        val params = itemView.layoutParams as GridLayoutManager.LayoutParams
        params.height = parent.measuredWidth / 2
        itemView.layoutParams = params
        return MyView(itemView)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.textView.text = nameList[position]
        holder.imageView.setImageDrawable(
            context.resources.getDrawable(
                imageList[position],
                null
            )
        )
        holder.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("resId", rowFileList[position])
            bundle.putString("title", nameList[position])
            val intent = Intent(context, KathaActivity::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    class MyView(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardview)
        val textView: TextView = view.findViewById(R.id.textview)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

}