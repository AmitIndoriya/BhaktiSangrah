package com.bhakti_sangrahalay.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.DoGhatiListAdapter
import com.bhakti_sangrahalay.panchang.model.DoGhatiBean
import com.bhakti_sangrahalay.ui.activity.PanchangActivity
import com.bhakti_sangrahalay.util.DatePicker
import com.bhakti_sangrahalay.util.Utility
import java.util.*

class DoGhatiFragment : Fragment(), View.OnClickListener {
    var activity: PanchangActivity? = null
    private lateinit var dayDoGhatiLabelTV: TextView
    private lateinit var sunRiseTimeTV: TextView
    private lateinit var dayDoGhatiRV: RecyclerView
    private lateinit var nightDoGhatiLabelTV: TextView
    private lateinit var sunSetTimeTV: TextView
    private lateinit var nightDoGhatiRV: RecyclerView

    private lateinit var currentDoGhatiLabelTV: TextView
    private lateinit var currentDoGhatiNameTV: TextView
    private lateinit var currentDoGhatiTime: TextView
    private lateinit var currentDoGhatiMeaningTV: TextView

    private lateinit var prevDateBtn: Button
    private lateinit var nextDateBtn: Button
    private lateinit var datePickerTV: TextView
    private lateinit var todayTV: TextView


    companion object {
        fun getInstance() = DoGhatiFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as PanchangActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val place = Utility.getPlaceForPanchang()
        val calendar = (activity as PanchangActivity).calendar
        (activity as PanchangActivity).viewModel.getSunRiseTime(calendar, place)
        (activity as PanchangActivity).viewModel.getSunSetTime(calendar, place)
        (activity as PanchangActivity).viewModel.getCurrentDoGhatiData(resources)
        (activity as PanchangActivity).viewModel.getDayDoGhatiData(resources, calendar)
        (activity as PanchangActivity).viewModel.getNightDoGhatiData(resources, calendar)
        (activity as PanchangActivity).viewModel.todayDoGhatiLiveData.observe(this, { setCurrentDoGhati(it) })
        (activity as PanchangActivity).viewModel.dayDoGhatiLiveData.observe(this, { setDayDoGhatiList(it) })
        (activity as PanchangActivity).viewModel.nightDoGhatiLiveData.observe(this, { setNightDoGhatiList(it) })
        (activity as PanchangActivity).viewModel.sunRiseTime.observe(this, { setDayHeadingData(it) })
        (activity as PanchangActivity).viewModel.sunSetTime.observe(this, { setNightHeadingData(it) })
        (activity as PanchangActivity).viewModel.dateLiveData.observe(this, { setPanchangDataAfterSelectDateFromPicker(it) })


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_doghati_layout, container, false)
        initView(view)
        setTypeface()
        setClickListener()
        setDate((activity as PanchangActivity).calendar)
        return view
    }

    @SuppressLint("CutPasteId")
    private fun initView(view: View) {
        dayDoGhatiLabelTV = view.findViewById(R.id.day_doGhati_tv)
        sunRiseTimeTV = view.findViewById(R.id.sunrise_tv)
        dayDoGhatiRV = view.findViewById(R.id.day_do_ghati_rv)
        nightDoGhatiLabelTV = view.findViewById(R.id.night_do_ghati_tv)
        sunSetTimeTV = view.findViewById(R.id.sunset_tv)
        nightDoGhatiRV = view.findViewById(R.id.night_do_ghati_rv)

        currentDoGhatiLabelTV = view.findViewById(R.id.tvCurrentdoGhati)
        currentDoGhatiNameTV = view.findViewById(R.id.tvplanetdoGhatiName)
        currentDoGhatiTime = view.findViewById(R.id.tvdoGhatitime)
        currentDoGhatiMeaningTV = view.findViewById(R.id.tvplanetsdoGhatimean)

        prevDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.previous_date_btn)
        nextDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.next_date_btn)
        datePickerTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.date_picker_tv)
        todayTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.current_date_btn)
    }

    private fun setTypeface() {
        dayDoGhatiLabelTV.typeface = Utility.getSemiBoldTypeface(context)
        sunRiseTimeTV.typeface = Utility.getSemiBoldTypeface(context)
        nightDoGhatiLabelTV.typeface = Utility.getSemiBoldTypeface(context)
        sunSetTimeTV.typeface = Utility.getSemiBoldTypeface(context)

        currentDoGhatiLabelTV.typeface = Utility.getBoldTypeface(context)
        currentDoGhatiNameTV.typeface = Utility.getSemiBoldTypeface(context)
        currentDoGhatiTime.typeface = Utility.getMediumTypeface(context)
        currentDoGhatiMeaningTV.typeface = Utility.getMediumTypeface(context)

        datePickerTV.typeface = Utility.getSemiBoldTypeface(context)
        todayTV.typeface = Utility.getSemiBoldTypeface(context)

    }

    private fun setPanchangDataAfterSelectDateFromPicker(calendar: Calendar) {
        (activity as PanchangActivity).viewModel.getDayDoGhatiData(resources, (activity as PanchangActivity).calendar)
        (activity as PanchangActivity).viewModel.getNightDoGhatiData(resources, (activity as PanchangActivity).calendar)
        setDate(calendar)
    }

    private fun setDate(calendar: Calendar) {
        datePickerTV.text = Utility.getDateForPanchangHeading(calendar.time)
    }

    @SuppressLint("SetTextI18n")
    fun setDayHeadingData(sunRiseTime: String) {
        sunRiseTimeTV.text = resources.getString(R.string.sun_rise) + " - " + sunRiseTime
    }

    @SuppressLint("SetTextI18n")
    fun setNightHeadingData(sunSetTime: String) {
        sunSetTimeTV.text = resources.getString(R.string.sun_set) + " - " + sunSetTime
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentDoGhati(doGhatiBean: DoGhatiBean) {
        currentDoGhatiNameTV.text = doGhatiBean.planetName + " (" + doGhatiBean.planetMeaning + ")"
        currentDoGhatiTime.text = doGhatiBean.duration
        currentDoGhatiMeaningTV.text = doGhatiBean.planetMeaning
    }

    private fun setDayDoGhatiList(doGhatiDataList: ArrayList<DoGhatiBean>) {
        dayDoGhatiRV.isNestedScrollingEnabled = false
        val doGhatiListAdapter = activity?.let { DoGhatiListAdapter(it, doGhatiDataList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        dayDoGhatiRV.layoutManager = mLayoutManager
        dayDoGhatiRV.itemAnimator = DefaultItemAnimator()
        dayDoGhatiRV.adapter = doGhatiListAdapter
    }

    private fun setNightDoGhatiList(doGhatiDataList: ArrayList<DoGhatiBean>) {
        nightDoGhatiRV.isNestedScrollingEnabled = false
        val doGhatiListAdapter = activity?.let { DoGhatiListAdapter(it, doGhatiDataList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        nightDoGhatiRV.layoutManager = mLayoutManager
        nightDoGhatiRV.itemAnimator = DefaultItemAnimator()
        nightDoGhatiRV.adapter = doGhatiListAdapter
    }

    private fun setClickListener() {
        prevDateBtn.setOnClickListener(this)
        nextDateBtn.setOnClickListener(this)
        todayTV.setOnClickListener(this)
        datePickerTV.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.previous_date_btn -> {
                (activity as PanchangActivity).calendar.add(Calendar.DATE, -1)
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getDayDoGhatiData(resources, (activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getNightDoGhatiData(resources, (activity as PanchangActivity).calendar)
            }
            R.id.next_date_btn -> {
                (activity as PanchangActivity).calendar.add(Calendar.DATE, 1)
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getDayDoGhatiData(resources, (activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getNightDoGhatiData(resources, (activity as PanchangActivity).calendar)
            }
            R.id.date_picker_tv -> {
                activity?.let { DatePicker().showDatePicker(it).show(requireActivity().getSupportFragmentManager(), "MATERIAL_DATE_PICKER") }
            }
            R.id.current_date_btn -> {
                (activity as PanchangActivity).calendar = Calendar.getInstance()
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getDayDoGhatiData(resources, (activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getNightDoGhatiData(resources, (activity as PanchangActivity).calendar)
            }
        }
    }

}