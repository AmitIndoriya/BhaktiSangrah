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
import com.bhakti_sangrahalay.adapter.HoraListAdapter
import com.bhakti_sangrahalay.panchang.model.HoraBean
import com.bhakti_sangrahalay.ui.activity.PanchangActivity
import com.bhakti_sangrahalay.util.DatePicker
import com.bhakti_sangrahalay.util.Utility
import java.util.*

class HoraFragment : Fragment(), View.OnClickListener {
    var activity: PanchangActivity? = null
    private lateinit var dayHoraLabelTV: TextView
    private lateinit var sunRiseTimeTV: TextView
    private lateinit var dayHoraRV: RecyclerView
    private lateinit var nightHoraLabelTV: TextView
    private lateinit var sunSetTimeTV: TextView
    private lateinit var nightHoraRV: RecyclerView

    private lateinit var currentHoraLabelTV: TextView
    private lateinit var currentHoraNameTV: TextView
    private lateinit var currentHoraTime: TextView
    private lateinit var currentHoraMeaningTV: TextView

    private lateinit var prevDateBtn: Button
    private lateinit var nextDateBtn: Button
    private lateinit var datePickerTV: TextView
    private lateinit var todayTV: TextView


    companion object {
        fun getInstance() = HoraFragment()
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
        (activity as PanchangActivity).viewModel.getCurrentHoraData(resources)
        (activity as PanchangActivity).viewModel.getDayHoraData(resources, calendar)
        (activity as PanchangActivity).viewModel.getNightHoraData(resources, calendar)

        (activity as PanchangActivity).viewModel.todayHoraLiveData.observe(this, { setCurrentHora(it) })
        (activity as PanchangActivity).viewModel.dayHoraLiveData.observe(this, { setDayHoraList(it) })
        (activity as PanchangActivity).viewModel.nightHoraLiveData.observe(this, { setNightHoraList(it) })
        (activity as PanchangActivity).viewModel.sunRiseTime.observe(this, { setDayHeadingData(it) })
        (activity as PanchangActivity).viewModel.sunSetTime.observe(this, { setNightHeadingData(it) })
        (activity as PanchangActivity).viewModel.dateLiveData.observe(this, { setPanchangDataAfterSelectDateFromPicker(it) })
        (activity as PanchangActivity).viewModel.calendarLiveData.observe(this, { setDate(it) })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_hora_layout, container, false)
        initView(view)
        setTypeface()
        setClickListener()
        setDate((activity as PanchangActivity).calendar)
        return view
    }

    private fun setClickListener() {
        prevDateBtn.setOnClickListener(this)
        nextDateBtn.setOnClickListener(this)
        todayTV.setOnClickListener(this)
        datePickerTV.setOnClickListener(this)
    }

    @SuppressLint("CutPasteId")
    private fun initView(view: View) {
        dayHoraLabelTV = view.findViewById(R.id.day_hora_tv)
        sunRiseTimeTV = view.findViewById(R.id.sunrise_tv)
        dayHoraRV = view.findViewById(R.id.day_hora_rv)
        nightHoraLabelTV = view.findViewById(R.id.night_hora_tv)
        sunSetTimeTV = view.findViewById(R.id.sunset_tv)
        nightHoraRV = view.findViewById(R.id.night_rv)

        currentHoraLabelTV = view.findViewById(R.id.tvCurrenthora)
        currentHoraNameTV = view.findViewById(R.id.tvplanetHoraName)
        currentHoraTime = view.findViewById(R.id.tvhoratime)
        currentHoraMeaningTV = view.findViewById(R.id.tvplanetsHoramean)

        prevDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.previous_date_btn)
        nextDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.next_date_btn)
        datePickerTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.date_picker_tv)
        todayTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.current_date_btn)
    }

    private fun setTypeface() {
        dayHoraLabelTV.typeface = Utility.getSemiBoldTypeface(context)
        sunRiseTimeTV.typeface = Utility.getSemiBoldTypeface(context)
        nightHoraLabelTV.typeface = Utility.getSemiBoldTypeface(context)
        sunSetTimeTV.typeface = Utility.getSemiBoldTypeface(context)

        currentHoraLabelTV.typeface = Utility.getBoldTypeface(context)
        currentHoraNameTV.typeface = Utility.getSemiBoldTypeface(context)
        currentHoraTime.typeface = Utility.getMediumTypeface(context)
        currentHoraMeaningTV.typeface = Utility.getMediumTypeface(context)

        datePickerTV.typeface = Utility.getSemiBoldTypeface(context)
        todayTV.typeface = Utility.getSemiBoldTypeface(context)

    }

    private fun setPanchangDataAfterSelectDateFromPicker(calendar: Calendar) {
        (activity as PanchangActivity).viewModel.getDayHoraData(resources, calendar)
        (activity as PanchangActivity).viewModel.getNightHoraData(resources, calendar)
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


    private fun setDayHoraList(horaDataList: ArrayList<HoraBean>) {
        dayHoraRV.isNestedScrollingEnabled = false
        val horaListAdapter = activity?.let { HoraListAdapter(it, horaDataList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        dayHoraRV.layoutManager = mLayoutManager
        dayHoraRV.itemAnimator = DefaultItemAnimator()
        dayHoraRV.adapter = horaListAdapter
    }

    private fun setNightHoraList(horaDataList: ArrayList<HoraBean>) {
        nightHoraRV.isNestedScrollingEnabled = false
        val horaListAdapter = activity?.let { HoraListAdapter(it, horaDataList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        nightHoraRV.layoutManager = mLayoutManager
        nightHoraRV.itemAnimator = DefaultItemAnimator()
        nightHoraRV.adapter = horaListAdapter
    }

    private fun setCurrentHora(horaBean: HoraBean) {
        currentHoraNameTV.text = horaBean.planetName
        currentHoraTime.text = horaBean.duration
        currentHoraMeaningTV.text = horaBean.planetMeaning
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.previous_date_btn -> {
                Toast.makeText(activity, "pre", Toast.LENGTH_SHORT).show()
                (activity as PanchangActivity).calendar.add(Calendar.DATE, -1)
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getDayHoraData(resources, (activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getNightHoraData(resources, (activity as PanchangActivity).calendar)
            }
            R.id.next_date_btn -> {
                (activity as PanchangActivity).calendar.add(Calendar.DATE, 1)
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getDayHoraData(resources, (activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getNightHoraData(resources, (activity as PanchangActivity).calendar)
            }
            R.id.date_picker_tv -> {
                activity?.let { DatePicker().showDatePicker(it).show(requireActivity().getSupportFragmentManager(), "MATERIAL_DATE_PICKER") }
            }
            R.id.current_date_btn -> {
                (activity as PanchangActivity).calendar = Calendar.getInstance()
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getDayHoraData(resources, (activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getNightHoraData(resources, (activity as PanchangActivity).calendar)
            }
        }
    }
}