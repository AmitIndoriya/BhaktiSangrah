package com.bhakti_sangrahalay.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.BhadraListAdapter
import com.bhakti_sangrahalay.ui.activity.PanchangActivity
import com.bhakti_sangrahalay.util.Utility
import java.util.*

class BhadraFragment : Fragment(), View.OnClickListener {
    var activity: PanchangActivity? = null
    private lateinit var startDateTV: TextView
    private lateinit var endDateTV: TextView
    private lateinit var bhadraRV: RecyclerView

    private lateinit var prevIV: Button
    private lateinit var nextIV: Button
    private lateinit var dateTV: TextView
    private lateinit var currentDateTV: TextView
    private var currentMonth: Int = 1


    companion object {
        fun getInstance() = BhadraFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as PanchangActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_panchak_layout, container, false)
        initView(view)
        intiDatePickerView(view)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DATE, 1)
        setDatePickerDate(calendar)
        setBhadraData(calendar)
        setVisibality()
        setTypeface()
        setClickListener()
        return view
    }

    private fun initView(view: View) {
        startDateTV = view.findViewById(R.id.start_date_tv)
        endDateTV = view.findViewById(R.id.end_date_tv)
        bhadraRV = view.findViewById(R.id.panchak_rv)
    }

    private fun intiDatePickerView(view: View) {
        val includeLayout = view.findViewById<View>(R.id.date_picker)
        prevIV = includeLayout.findViewById(R.id.previous_date_btn)
        nextIV = includeLayout.findViewById(R.id.next_date_btn)
        dateTV = includeLayout.findViewById(R.id.date_picker_tv)
        currentDateTV = includeLayout.findViewById(R.id.current_date_btn)
    }

    fun setTypeface() {
        dateTV.typeface = Utility.getSemiBoldTypeface(activity)
        startDateTV.typeface = Utility.getSemiBoldTypeface(activity)
        endDateTV.typeface = Utility.getSemiBoldTypeface(activity)
    }

    private fun setVisibality() {
        currentDateTV.visibility = View.GONE
    }

    private fun setClickListener() {
        prevIV.setOnClickListener(this)
        nextIV.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun setDatePickerDate(calendar: Calendar) {
        dateTV.text = Utility.getMonthList()[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)
    }

    private fun setBhadraData(calendar: Calendar) {

        val panchakList = (activity as PanchangActivity).viewModel.getBhadraData(calendar, Utility.getPlaceForPanchang())

        bhadraRV.isNestedScrollingEnabled = false
        val horaListAdapter = activity?.let { BhadraListAdapter(it, panchakList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        bhadraRV.layoutManager = mLayoutManager
        bhadraRV.itemAnimator = DefaultItemAnimator()
        bhadraRV.adapter = horaListAdapter
    }


    override fun onClick(v: View) {

        when (v.id) {
            R.id.previous_date_btn -> {

                val calendar = Calendar.getInstance()
                calendar.set(Calendar.DATE, 1)
                calendar.add(Calendar.MONTH, currentMonth)
                setDatePickerDate(calendar)
                --currentMonth
                setBhadraData(calendar)
            }
            R.id.next_date_btn -> {

                val calendar = Calendar.getInstance()
                calendar.set(Calendar.DATE, 1)
                calendar.add(Calendar.MONTH, currentMonth)
                setDatePickerDate(calendar)
                ++currentMonth
                setBhadraData(calendar)
            }

        }
    }

}