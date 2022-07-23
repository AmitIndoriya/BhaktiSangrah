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
import com.bhakti_sangrahalay.adapter.ChogdiaListAdapter
import com.bhakti_sangrahalay.panchang.model.ChogdiyaBean
import com.bhakti_sangrahalay.ui.activity.PanchangActivity
import com.bhakti_sangrahalay.util.DatePicker
import com.bhakti_sangrahalay.util.Utility
import java.util.*

class ChogdiaFragment : Fragment(), View.OnClickListener {
    var activity: PanchangActivity? = null
    private lateinit var dayChogdiaLabelTV: TextView
    private lateinit var sunRiseTimeTV: TextView
    private lateinit var dayChogdiaRV: RecyclerView
    private lateinit var nightChogdiaLabelTV: TextView
    private lateinit var sunSetTimeTV: TextView
    private lateinit var nightChogdiaRV: RecyclerView

    private lateinit var currentChogdiaLabelTV: TextView
    private lateinit var currentChogdiaNameTV: TextView
    private lateinit var currentChogdiaTime: TextView
    private lateinit var currentChogdiaMeaningTV: TextView

    private lateinit var prevDateBtn: Button
    private lateinit var nextDateBtn: Button
    private lateinit var datePickerTV: TextView
    private lateinit var todayTV: TextView


    companion object {
        fun getInstance() = ChogdiaFragment()
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
        (activity as PanchangActivity).viewModel.getCurrentChogdiyaData(resources)
        (activity as PanchangActivity).viewModel.getDayChogdiyaData(resources, calendar)
        (activity as PanchangActivity).viewModel.getNightChogdiyaData(resources, calendar)
        (activity as PanchangActivity).viewModel.todayChogdiyaLiveData.observe(this, { setCurrentChogdia(it) })
        (activity as PanchangActivity).viewModel.dayChogdiyaLiveData.observe(this, { setDayChogdiaList(it) })
        (activity as PanchangActivity).viewModel.nightChogdiyaLiveData.observe(this, { setNightChogdiaList(it) })
        (activity as PanchangActivity).viewModel.sunRiseTime.observe(this, { setDayHeadingData(it) })
        (activity as PanchangActivity).viewModel.sunSetTime.observe(this, { setNightHeadingData(it) })
        (activity as PanchangActivity).viewModel.dateLiveData.observe(this, { setPanchangDataAfterSelectDateFromPicker(it) })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_chogdia_layout, container, false)
        initView(view)
        setTypeface()
        setClickListener()
        setDate((activity as PanchangActivity).calendar)
        return view
    }

    @SuppressLint("CutPasteId")
    private fun initView(view: View) {
        dayChogdiaLabelTV = view.findViewById(R.id.day_chogdiya_tv)
        sunRiseTimeTV = view.findViewById(R.id.sunrise_tv)
        dayChogdiaRV = view.findViewById(R.id.day_chogdiya_rv)
        nightChogdiaLabelTV = view.findViewById(R.id.night_chogdiya_tv)
        sunSetTimeTV = view.findViewById(R.id.sunset_tv)
        nightChogdiaRV = view.findViewById(R.id.night_chogdya_rv)

        currentChogdiaLabelTV = view.findViewById(R.id.current_chogdiya_tv)
        currentChogdiaNameTV = view.findViewById(R.id.planet_chogdiya_name_tv)
        currentChogdiaTime = view.findViewById(R.id.chogdiya_time_tv)
        currentChogdiaMeaningTV = view.findViewById(R.id.planet_chogdiya_mean_tv)

        prevDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.previous_date_btn)
        nextDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.next_date_btn)
        datePickerTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.date_picker_tv)
        todayTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.current_date_btn)
    }

    private fun setTypeface() {
        dayChogdiaLabelTV.typeface = Utility.getSemiBoldTypeface(context)
        sunRiseTimeTV.typeface = Utility.getSemiBoldTypeface(context)
        nightChogdiaLabelTV.typeface = Utility.getSemiBoldTypeface(context)
        sunSetTimeTV.typeface = Utility.getSemiBoldTypeface(context)

        currentChogdiaLabelTV.typeface = Utility.getBoldTypeface(context)
        currentChogdiaNameTV.typeface = Utility.getSemiBoldTypeface(context)
        currentChogdiaTime.typeface = Utility.getMediumTypeface(context)
        currentChogdiaMeaningTV.typeface = Utility.getMediumTypeface(context)

        datePickerTV.typeface = Utility.getSemiBoldTypeface(context)
        todayTV.typeface = Utility.getSemiBoldTypeface(context)

    }

    private fun setPanchangDataAfterSelectDateFromPicker(calendar: Calendar) {
        (activity as PanchangActivity).viewModel.getDayChogdiyaData(resources, (activity as PanchangActivity).calendar)
        (activity as PanchangActivity).viewModel.getNightChogdiyaData(resources, (activity as PanchangActivity).calendar)
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
    private fun setCurrentChogdia(chogdiaBean: ChogdiyaBean) {
        currentChogdiaNameTV.text = chogdiaBean.planetName + " (" + chogdiaBean.planetMeaning + ")"
        currentChogdiaTime.text = chogdiaBean.duration
        currentChogdiaMeaningTV.text = chogdiaBean.planetMeaning
    }

    private fun setDayChogdiaList(ChogdiaDataList: ArrayList<ChogdiyaBean>) {
        dayChogdiaRV.isNestedScrollingEnabled = false
        val chogdiaListAdapter = activity?.let { ChogdiaListAdapter(it, ChogdiaDataList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        dayChogdiaRV.layoutManager = mLayoutManager
        dayChogdiaRV.itemAnimator = DefaultItemAnimator()
        dayChogdiaRV.adapter = chogdiaListAdapter
    }

    private fun setNightChogdiaList(ChogdiaDataList: ArrayList<ChogdiyaBean>) {
        nightChogdiaRV.isNestedScrollingEnabled = false
        val chogdiaListAdapter = activity?.let { ChogdiaListAdapter(it, ChogdiaDataList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        nightChogdiaRV.layoutManager = mLayoutManager
        nightChogdiaRV.itemAnimator = DefaultItemAnimator()
        nightChogdiaRV.adapter = chogdiaListAdapter
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
                Toast.makeText(activity, "pre", Toast.LENGTH_SHORT).show()
                (activity as PanchangActivity).calendar.add(Calendar.DATE, -1)
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getDayChogdiyaData(resources, (activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getNightChogdiyaData(resources, (activity as PanchangActivity).calendar)
            }
            R.id.next_date_btn -> {
                (activity as PanchangActivity).calendar.add(Calendar.DATE, 1)
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getDayChogdiyaData(resources, (activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getNightChogdiyaData(resources, (activity as PanchangActivity).calendar)
            }
            R.id.date_picker_tv -> {
                activity?.let { DatePicker().showDatePicker(it).show(requireActivity().getSupportFragmentManager(), "MATERIAL_DATE_PICKER") }
            }
            R.id.current_date_btn -> {
                (activity as PanchangActivity).calendar = Calendar.getInstance()
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getDayChogdiyaData(resources, (activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getNightChogdiyaData(resources, (activity as PanchangActivity).calendar)
            }
        }
    }

}