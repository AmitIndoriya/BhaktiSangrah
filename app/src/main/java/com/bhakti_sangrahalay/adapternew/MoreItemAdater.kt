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
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.ui.activity.AartiDescActivityNew
import com.bhakti_sangrahalay.ui.activity.ChalishaDescActivityNew
import com.bhakti_sangrahalay.contansts.GlobalVariables
import com.bhakti_sangrahalay.ui.activity.BaseActivity

class MoreItemAdater(
    val context: Context,
    private val nameList: Array<String>,
    private val imageList: IntArray,
    private val rowFileList: IntArray,
    val type: Int
) : RecyclerView.Adapter<MoreItemAdater.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.more_list_item_layout, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.textView.text = nameList[position].replace("\n", " ")
        holder.imageView.setImageDrawable(context.resources.getDrawable(imageList[position], null))
        holder.textView.typeface = (context as BaseActivity).mediumTypeface
        holder.imageView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("imageId", imageList[position])
            bundle.putInt("fileId", rowFileList[position])
            bundle.putString("title", context.resources.getString(R.string.aarti))
            bundle.putInt("fragNum", position)
            val intent: Intent = if (type == GlobalVariables.chalisha) {
                Intent(context, ChalishaDescActivityNew::class.java)
            } else {
                Intent(context, AartiDescActivityNew::class.java)
            }
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return nameList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textview)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
    }

}