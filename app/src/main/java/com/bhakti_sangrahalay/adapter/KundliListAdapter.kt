package com.bhakti_sangrahalay.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.ui.activity.BirthDetailInputActivityNew


class KundliListAdapter(
    val context: Context,
    private val birthDetailBeanList: ArrayList<BirthDetailBean>
) :
    RecyclerView.Adapter<KundliListAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.kundli_list_item, parent, false)
        return MyView(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyView, position: Int) {
        val birthDetailBean = birthDetailBeanList[position]
        val dateTimeBean = birthDetailBeanList[position].dateTimeBean
        val placeBean = birthDetailBeanList[position].placeBean
        holder.nameTV.text = birthDetailBean.name
        holder.dobTV.text =
            dateTimeBean.day + " " + dateTimeBean.month + " " + dateTimeBean.year + " | "
        holder.tobTV.text = dateTimeBean.hrs + " " + dateTimeBean.min + " " + dateTimeBean.sec
        holder.pobTV.text = placeBean.place

        holder.nameTV.typeface = (context as BaseActivity).semiBoldTypeface
        holder.dobTV.typeface = context.mediumTypeface
        holder.tobTV.typeface = context.mediumTypeface
        holder.pobTV.typeface = context.mediumTypeface
        holder.rootLayout.setOnClickListener {
            (context as BirthDetailInputActivityNew).startKundliOutputActivity(
                birthDetailBeanList[position]
            )
        }
        holder.moreIV.setOnClickListener({ showPopupMenu(holder.moreIV) })
    }

    override fun getItemCount(): Int {
        return birthDetailBeanList.size
    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rootLayout: RelativeLayout = itemView.findViewById(R.id.root_layout)
        val nameTV: TextView = itemView.findViewById(R.id.name_tv)
        val dobTV: TextView = itemView.findViewById(R.id.dob_tv)
        val tobTV: TextView = itemView.findViewById(R.id.tob_tv)
        val pobTV: TextView = itemView.findViewById(R.id.pob_tv)
        val moreIV: ImageView = itemView.findViewById(R.id.more_iv)

    }

    fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.getMenuInflater()
            .inflate(com.bhakti_sangrahalay.R.menu.kundli_bottom_nav_item, popupMenu.getMenu())
        popupMenu.setOnMenuItemClickListener { menuItem ->
            Toast.makeText(
                context,
                "You Clicked " + menuItem.getTitle(),
                Toast.LENGTH_SHORT
            ).show()
            true
        }
        popupMenu.show()
    }
}