package com.bhakti_sangrahalay.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.activity.DainikCalendarActivity
import com.bhakti_sangrahalay.adapter.MonthFestivalAdapter
import com.bhakti_sangrahalay.model.DainikCalendarModel
import com.bhakti_sangrahalay.model.MonthFestivalModel
import com.bhakti_sangrahalay.model.TodayPanchangModelNew
import com.bhakti_sangrahalay.panchang.calculations.PanchangCalculation
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.util.Utility
import com.google.android.material.card.MaterialCardView
import java.util.*

class DainikCalendarFragment : Fragment(), View.OnClickListener {
    var activity: DainikCalendarActivity? = null
    private lateinit var calendar: Calendar
    private lateinit var dainikCalendarModel: DainikCalendarModel
    private var fragId = 0
    private lateinit var calendarContainer: MaterialCardView
    private lateinit var todayDayMonthTV: TextView
    private lateinit var todayPakshTV: TextView
    private lateinit var samvatTV: TextView
    private lateinit var moonPhaseIV: ImageView
    private lateinit var placeTV: TextView
    private lateinit var enDateTV: TextView
    private lateinit var enMonthTV: TextView
    private lateinit var enVarTV: TextView

    private lateinit var festTableHeading: TextView
    private lateinit var festRV: RecyclerView


    companion object {

        fun getInstance(id: Int, dainikCalendarModel: DainikCalendarModel) = DainikCalendarFragment().apply {
            arguments = Bundle(2).apply {
                putInt("id", id)
                putSerializable("dainikCalendarModel", dainikCalendarModel)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as DainikCalendarActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            fragId = bundle.getInt("id")
            dainikCalendarModel = bundle.getSerializable("dainikCalendarModel") as DainikCalendarModel
            calendar = dainikCalendarModel.calendar
        }
        // (activity as DainikCalendarActivity).viewModel.getTodayPanchang(calendar, Utility.getPlaceForPanchang())
        (activity as DainikCalendarActivity).viewModel.todayPanchangModelLiveData.observe(this, { setTodayPanchangData(it) })


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_daily_calendar, container, false)
        intiView(view)
        initTodayPanchangView(view)
        initCalendar()
        setTypeFace()
        setTodayPanchangData(dainikCalendarModel.todayPanchangModel)
        val month = dainikCalendarModel.calendar[Calendar.MONTH]
        festTableHeading.setText(resources.getString(R.string.fest_table_heading).replace("#", Utility.getMonthList()[month]))
        setFestData(dainikCalendarModel.monthFestivalModel)

        return view
    }

    private fun intiView(view: View) {
        calendarContainer = view.findViewById(R.id.calendar_container)
        festTableHeading = view.findViewById(R.id.fest_table_header_tv)
        festRV = view.findViewById(R.id.fest_rv)
    }

    fun setTypeFace() {
     /*   todayDayMonthTV.setTypeface(BaseActivity.boldTypeface)
        todayPakshTV.setTypeface(BaseActivity.mediumTypeface)
        samvatTV.setTypeface(BaseActivity.mediumTypeface)
        placeTV.setTypeface(BaseActivity.mediumTypeface)
        enDateTV.setTypeface(BaseActivity.boldTypeface)
        enMonthTV.setTypeface(BaseActivity.mediumTypeface)
        enVarTV.setTypeface(BaseActivity.mediumTypeface)
        festTableHeading.setTypeface(BaseActivity.semiBoldTypeface)*/
    }

    private fun initTodayPanchangView(view: View) {

        val includedView = view.findViewById<View>(R.id.today_panchang_layout)

        todayDayMonthTV = getView(includedView, R.id.day_month_tv) as TextView
        todayPakshTV = getView(includedView, R.id.paksh_tv) as TextView
        samvatTV = getView(includedView, R.id.samvat_tv) as TextView
        moonPhaseIV = getView(includedView, R.id.moon_phase_iv) as ImageView
        placeTV = getView(includedView, R.id.place_tv) as TextView
        enDateTV = getView(includedView, R.id.en_date_tv) as TextView
        enMonthTV = getView(includedView, R.id.en_month_tv) as TextView
        enVarTV = getView(includedView, R.id.en_var_tv) as TextView
    }

    private fun getView(parentView: View, id: Int): View {
        return parentView.findViewById(id)
    }

    private fun initCalendar() {
    /*    val cv = CalendarView(activity)
        calendarContainer.addView(cv)
        cv.updateCalendar(calendar.time)*/
    }

    @SuppressLint("SetTextI18n")
    fun setTodayPanchangData(todayPanchangModel: TodayPanchangModelNew) {
        todayDayMonthTV.text = todayPanchangModel.tithi
        todayPakshTV.text = todayPanchangModel.paksh + ", " + todayPanchangModel.hindiMonth
        samvatTV.text = todayPanchangModel.samvat + " " + resources.getString(R.string.vikram_samvat)
        placeTV.text = "Jaipur, India"
        val date = todayPanchangModel.calendar[Calendar.DATE].toString()
        enDateTV.text = if (date.length == 1) {
            "0$date"
        } else {
            date
        }
        Log.i("MonthYear", "--" + todayPanchangModel.calendar[Calendar.MONTH] + "," + todayPanchangModel.calendar[Calendar.YEAR])
        val monthName = resources.getStringArray(R.array.month_name)
        enMonthTV.text = monthName[todayPanchangModel.calendar[Calendar.MONTH]] + ", " + todayPanchangModel.calendar[Calendar.YEAR]
        enVarTV.text = todayPanchangModel.vara
        val index: Int = todayPanchangModel.tithiInt
        moonPhaseIV.setImageDrawable(PanchangCalculation.getMoonPhaseImage(resources, index))
    }

    private fun setFestData(monthFestivalModel: MonthFestivalModel) {
        festRV.isNestedScrollingEnabled = false
        val monthFestivalAdapter = activity?.let { MonthFestivalAdapter(it, monthFestivalModel.festivalList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        festRV.layoutManager = mLayoutManager
        festRV.itemAnimator = DefaultItemAnimator()
        festRV.adapter = monthFestivalAdapter

    }


    override fun onClick(p0: View?) {

    }
}