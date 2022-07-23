package com.bhakti_sangrahalay.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.MuhuratAdapter
import com.bhakti_sangrahalay.panchang.calculations.PanchangCalculation
import com.bhakti_sangrahalay.panchang.model.TodayPanchangModel
import com.bhakti_sangrahalay.ui.activity.PanchangActivity
import com.bhakti_sangrahalay.util.DatePicker
import com.bhakti_sangrahalay.util.Utility
import com.indoriya.panchang.Place
import java.util.*
import kotlin.collections.ArrayList

class DailyPanchangFragment : Fragment(), View.OnClickListener {

    var activity: PanchangActivity? = null
    private lateinit var sunRiseLabelTV: TextView
    private lateinit var sunRiseTimeTV: TextView
    private lateinit var sunSetLabelTV: TextView
    private lateinit var sunSetTimeTV: TextView
    private lateinit var moonRiseLabelTV: TextView
    private lateinit var moonRiseTimeTV: TextView
    private lateinit var moonSetLabelTV: TextView
    private lateinit var moonSetTimeTV: TextView

    private lateinit var tithiLabelTV: TextView
    private lateinit var tithiValTV: TextView
    private lateinit var tithiTimeTV: TextView
    private lateinit var nakshtraLabelTV: TextView
    private lateinit var nakshtraValTV: TextView
    private lateinit var nakshtraTimeTV: TextView
    private lateinit var yogLabelTV: TextView
    private lateinit var yogValTV: TextView
    private lateinit var yogTimeTV: TextView
    private lateinit var karanLabelTV: TextView
    private lateinit var karanValTV: TextView
    private lateinit var karanTimeTV: TextView

    private lateinit var pakshaLabelTV: TextView
    private lateinit var pakshaValTV: TextView
    private lateinit var dayLabelTV: TextView
    private lateinit var dayValTV: TextView
    private lateinit var moonSignLabelTV: TextView
    private lateinit var moonSignValTV: TextView
    private lateinit var rituLabelTV: TextView
    private lateinit var rituValTV: TextView

    private lateinit var amantaLabelTV: TextView
    private lateinit var amantaValTV: TextView
    private lateinit var purnimantaLabelTV: TextView
    private lateinit var purnimantaValTV: TextView
    private lateinit var shakSamvatLabelTV: TextView
    private lateinit var shakSamvatValTV: TextView
    private lateinit var kaliSamvatLabelTV: TextView
    private lateinit var kaliSamvatValTV: TextView
    private lateinit var vikramSamvatLabelTV: TextView
    private lateinit var vikramSamvatValTV: TextView
    private lateinit var dayDurationLabelTV: TextView
    private lateinit var dayDurationValTV: TextView

    private lateinit var abhijitMuhuratValTV: TextView
    private lateinit var abhijitMuhuratTimeTV: TextView
    private lateinit var ashubMuhuratRV: RecyclerView

    private lateinit var todayDayMonthTV: TextView
    private lateinit var todayPakshTV: TextView
    private lateinit var samvatTV: TextView
    private lateinit var moonPhaseIV: ImageView
    private lateinit var placeTV: TextView
    private lateinit var enDateTV: TextView
    private lateinit var enMonthTV: TextView
    private lateinit var enVarTV: TextView

    private lateinit var prevDateBtn: Button
    private lateinit var nextDateBtn: Button
    private lateinit var datePickerTV: TextView
    private lateinit var todayTV: TextView

    companion object {
        fun getInstance() = DailyPanchangFragment()
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
        getSampuranPanchangData((activity as PanchangActivity).calendar, place)
        (activity as PanchangActivity).viewModel.sunRiseTime.observe(this, { setSunRiseTime(it) })
        (activity as PanchangActivity).viewModel.sunSetTime.observe(this, { setSunSetTime(it) })
        (activity as PanchangActivity).viewModel.moonRiseAndSetTime.observe(this, { setMoonRiseAndSetTime(it) })
        (activity as PanchangActivity).viewModel.tithiArrayList.observe(this, { setTithi(it) })
        (activity as PanchangActivity).viewModel.nakshtraLiveData.observe(this, { setNakshtra(it) })
        (activity as PanchangActivity).viewModel.yogLiveData.observe(this, { setYog(it) })
        (activity as PanchangActivity).viewModel.karanLiveData.observe(this, { setKaran(it) })
        (activity as PanchangActivity).viewModel.pakshaLiveData.observe(this, { setPaksh(it) })
        (activity as PanchangActivity).viewModel.varLiveData.observe(this, { setDay(it) })
        (activity as PanchangActivity).viewModel.moonSignLiveData.observe(this, { setMoonSign(it) })
        (activity as PanchangActivity).viewModel.rituLiveData.observe(this, { setRitu(it) })
        (activity as PanchangActivity).viewModel.amantMonthLiveData.observe(this, { setAmanta(it) })
        (activity as PanchangActivity).viewModel.purnimantMonthLiveData.observe(this, { setPurnimant(it) })
        (activity as PanchangActivity).viewModel.shakaSamvatLiveData.observe(this, { setShakaSamvat(it) })
        (activity as PanchangActivity).viewModel.kaliSamvatLiveData.observe(this, { setKaliSamvat(it) })
        (activity as PanchangActivity).viewModel.vikramSamvatLiveData.observe(this, { setVikramSamvat(it) })
        (activity as PanchangActivity).viewModel.dayDurationLiveData.observe(this, { setDayDuration(it) })
        (activity as PanchangActivity).viewModel.shubhMuhuratLiveData.observe(this, { setShubhMuhurat(it) })
        (activity as PanchangActivity).viewModel.ashubhMuhuratLiveData.observe(this, { setAshubhMuhurat(it) })
        (activity as PanchangActivity).viewModel.todayPanchangModelLiveData.observe(this, { setTodayPanchangData(it) })
        (activity as PanchangActivity).viewModel.dateLiveData.observe(this, { setPanchangDataAfterSelectDateFromPicker(it, place) })
        (activity as PanchangActivity).viewModel.isPlaceChangedLiveData.observe(this, { setPanchangDataAfterSelectDateFromPicker((activity as PanchangActivity).calendar, Utility.getPlaceForPanchang((activity as PanchangActivity).preferences)) })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.panchang_layout, container, false)
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
        placeTV.setOnClickListener(this)
    }

    fun setTypeface() {
        sunRiseLabelTV.typeface = Utility.getMediumTypeface(activity)
        sunRiseTimeTV.typeface = Utility.getSemiBoldTypeface(activity)
        sunSetLabelTV.typeface = Utility.getMediumTypeface(activity)
        sunSetTimeTV.typeface = Utility.getSemiBoldTypeface(activity)
        moonRiseLabelTV.typeface = Utility.getMediumTypeface(activity)
        moonRiseTimeTV.typeface = Utility.getSemiBoldTypeface(activity)
        moonSetLabelTV.typeface = Utility.getMediumTypeface(activity)
        moonSetTimeTV.typeface = Utility.getSemiBoldTypeface(activity)

        tithiLabelTV.typeface = Utility.getMediumTypeface(activity)
        tithiValTV.typeface = Utility.getSemiBoldTypeface(activity)
        tithiTimeTV.typeface = Utility.getMediumTypeface(activity)
        nakshtraLabelTV.typeface = Utility.getMediumTypeface(activity)
        nakshtraValTV.typeface = Utility.getSemiBoldTypeface(activity)
        nakshtraTimeTV.typeface = Utility.getMediumTypeface(activity)
        yogLabelTV.typeface = Utility.getMediumTypeface(activity)
        yogValTV.typeface = Utility.getSemiBoldTypeface(activity)
        yogTimeTV.typeface = Utility.getMediumTypeface(activity)
        karanLabelTV.typeface = Utility.getMediumTypeface(activity)
        karanValTV.typeface = Utility.getSemiBoldTypeface(activity)
        karanTimeTV.typeface = Utility.getMediumTypeface(activity)

        pakshaLabelTV.typeface = Utility.getMediumTypeface(activity)
        pakshaValTV.typeface = Utility.getSemiBoldTypeface(activity)
        dayLabelTV.typeface = Utility.getMediumTypeface(activity)
        dayValTV.typeface = Utility.getSemiBoldTypeface(activity)
        moonSignLabelTV.typeface = Utility.getMediumTypeface(activity)
        moonSignValTV.typeface = Utility.getSemiBoldTypeface(activity)
        rituLabelTV.typeface = Utility.getMediumTypeface(activity)
        rituValTV.typeface = Utility.getSemiBoldTypeface(activity)

        amantaLabelTV.typeface = Utility.getMediumTypeface(activity)
        amantaValTV.typeface = Utility.getSemiBoldTypeface(activity)
        purnimantaLabelTV.typeface = Utility.getMediumTypeface(activity)
        purnimantaValTV.typeface = Utility.getSemiBoldTypeface(activity)
        shakSamvatLabelTV.typeface = Utility.getMediumTypeface(activity)
        shakSamvatValTV.typeface = Utility.getSemiBoldTypeface(activity)
        kaliSamvatLabelTV.typeface = Utility.getMediumTypeface(activity)
        kaliSamvatValTV.typeface = Utility.getSemiBoldTypeface(activity)
        vikramSamvatLabelTV.typeface = Utility.getMediumTypeface(activity)
        vikramSamvatValTV.typeface = Utility.getSemiBoldTypeface(activity)
        dayDurationLabelTV.typeface = Utility.getMediumTypeface(activity)
        dayDurationValTV.typeface = Utility.getSemiBoldTypeface(activity)

        abhijitMuhuratValTV.typeface = Utility.getSemiBoldTypeface(activity)
        abhijitMuhuratTimeTV.typeface = Utility.getMediumTypeface(activity)

        datePickerTV.typeface = Utility.getSemiBoldTypeface(context)
        todayTV.typeface = Utility.getSemiBoldTypeface(context)

        todayDayMonthTV.typeface = Utility.getBoldTypeface(context)
        todayPakshTV.typeface = Utility.getMediumTypeface(context)
        samvatTV.typeface = Utility.getMediumTypeface(context)
        placeTV.typeface = Utility.getMediumTypeface(context)
        enDateTV.typeface = Utility.getBoldTypeface(context)
        enMonthTV.typeface = Utility.getMediumTypeface(context)
        enVarTV.typeface = Utility.getMediumTypeface(context)
    }

    private fun setPanchangDataAfterSelectDateFromPicker(calendar: Calendar, place: Place) {
        getSampuranPanchangData(calendar, place)
        setDate(calendar)
        setPlace()
    }

    @SuppressLint("SetTextI18n")
    fun setPlace() {
        val placeModel = Utility.getObejectInPreference((activity as PanchangActivity).preferences)
        placeTV.text = placeModel.city + ", " + placeModel.country
    }

    private fun getSampuranPanchangData(calendar: Calendar, place: Place) {
        (activity as PanchangActivity).viewModel.getSunRiseTime(calendar, place)
        (activity as PanchangActivity).viewModel.getSunSetTime(calendar, place)
        (activity as PanchangActivity).viewModel.getMoonRiseAndSetTime(calendar, place)
        (activity as PanchangActivity).viewModel.getTithi(calendar, place)
        (activity as PanchangActivity).viewModel.getNakshtra(calendar, place)
        (activity as PanchangActivity).viewModel.getYog(calendar, place)
        (activity as PanchangActivity).viewModel.getKaran(calendar, place)
        (activity as PanchangActivity).viewModel.getPaksha(calendar, place)
        (activity as PanchangActivity).viewModel.getVar(calendar, place)
        (activity as PanchangActivity).viewModel.getMoonSign(calendar, place)
        (activity as PanchangActivity).viewModel.getRitu(calendar, place)
        (activity as PanchangActivity).viewModel.getAmantaMonth(calendar, place)
        (activity as PanchangActivity).viewModel.getPurnimantMonth(calendar, place)
        (activity as PanchangActivity).viewModel.getShakaSamvat(calendar, place)
        (activity as PanchangActivity).viewModel.getKaliSamvat(calendar, place)
        (activity as PanchangActivity).viewModel.getVikramSamvat(calendar, place)
        (activity as PanchangActivity).viewModel.getDayDuration(calendar, place)
        (activity as PanchangActivity).viewModel.getShubhMuhurat(calendar, place)
        (activity as PanchangActivity).viewModel.getAshubhMuhurat(calendar, place)
        (activity as PanchangActivity).viewModel.getTodayPanchang(calendar, place)
    }

    private fun setDate(calendar: Calendar) {
        datePickerTV.text = Utility.getDateForPanchangHeading(calendar.time)
    }


    private fun setSunRiseTime(sunRiseTime: String) {
        sunRiseTimeTV.text = sunRiseTime
    }

    private fun setSunSetTime(sunSetTime: String) {
        sunSetTimeTV.text = sunSetTime
    }

    private fun setMoonRiseAndSetTime(moonRiseAndSetTime: ArrayList<String>) {
        moonRiseTimeTV.text = Utility.convertTimeToAmPm(moonRiseAndSetTime[0])
        moonSetTimeTV.text = Utility.convertTimeToAmPm(moonRiseAndSetTime[1])
    }

    @SuppressLint("SetTextI18n")
    fun setTithi(tithiArray: ArrayList<String>) {
        tithiValTV.text = tithiArray[0]
        tithiTimeTV.text = " " + tithiArray[1] + " तक"
    }

    @SuppressLint("SetTextI18n")
    fun setNakshtra(nakshtraArray: ArrayList<String>) {
        nakshtraValTV.text = nakshtraArray[0]
        nakshtraTimeTV.text = nakshtraArray[1] + " तक"
    }

    @SuppressLint("SetTextI18n")
    fun setYog(yogArray: ArrayList<String>) {
        yogValTV.text = yogArray[0]
        yogTimeTV.text = yogArray[1] + " तक"
    }

    @SuppressLint("SetTextI18n")
    fun setKaran(karanArray: ArrayList<String>) {
        karanValTV.text = karanArray[0]
        karanTimeTV.text = karanArray[1]
    }

    @SuppressLint("SetTextI18n")
    fun setPaksh(paksha: String) {
        pakshaValTV.text = paksha
    }

    @SuppressLint("SetTextI18n")
    fun setDay(day: String) {
        dayValTV.text = day
    }

    @SuppressLint("SetTextI18n")
    fun setRitu(ritu: String) {
        rituValTV.text = ritu
    }

    @SuppressLint("SetTextI18n")
    fun setMoonSign(moonSign: String) {
        moonSignValTV.text = moonSign
    }

    @SuppressLint("SetTextI18n")
    fun setAmanta(amanta: String) {
        amantaValTV.text = amanta
    }

    @SuppressLint("SetTextI18n")
    fun setPurnimant(purnimant: String) {
        purnimantaValTV.text = purnimant
    }

    @SuppressLint("SetTextI18n")
    fun setShakaSamvat(shakaSamvat: String) {
        shakSamvatValTV.text = shakaSamvat
    }

    @SuppressLint("SetTextI18n")
    fun setKaliSamvat(kaliSamvat: String) {
        kaliSamvatValTV.text = kaliSamvat
    }

    @SuppressLint("SetTextI18n")
    fun setVikramSamvat(vikramSamvat: String) {
        vikramSamvatValTV.text = vikramSamvat
    }

    @SuppressLint("SetTextI18n")
    fun setDayDuration(dayDuration: String) {
        dayDurationValTV.text = dayDuration
    }

    @SuppressLint("SetTextI18n")
    private fun setShubhMuhurat(abhijitMuhurat: ArrayList<String>) {
        abhijitMuhuratValTV.text = resources.getString(R.string.abhijit)
        abhijitMuhuratTimeTV.text = abhijitMuhurat[0] + " " + resources.getString(R.string.from) + " " + abhijitMuhurat[1]
    }

    private fun setAshubhMuhurat(mhuratDuration: ArrayList<ArrayList<String>>) {
        val muhuratName: Array<String> = resources.getStringArray(R.array.ashub_muhurats)
        val colorCode: IntArray = resources.getIntArray(R.array.muhurat_card_color)
        setAshubMuhuratRecyclerView(muhuratName, mhuratDuration, colorCode)
    }


    private fun setAshubMuhuratRecyclerView(muhuratName: Array<String>, muhuratDuration: ArrayList<ArrayList<String>>, colorCode: IntArray) {
        ashubMuhuratRV.layoutManager = GridLayoutManager(activity, 2)
        val adapter1 = activity?.let { MuhuratAdapter(it, muhuratName, muhuratDuration, colorCode) }
        ashubMuhuratRV.adapter = adapter1
    }

    @SuppressLint("SetTextI18n")
    fun setTodayPanchangData(todayPanchangModel: TodayPanchangModel) {
        todayDayMonthTV.text = todayPanchangModel.tithi
        todayPakshTV.text = todayPanchangModel.paksh + ", " + todayPanchangModel.hindiMonth
        samvatTV.text = todayPanchangModel.samvat + " " + resources.getString(R.string.vikram_samvat)
        placeTV.text = "Jaipur, India"
        val date = (activity as PanchangActivity).calendar[Calendar.DATE].toString()
        enDateTV.text = if (date.length == 1) {
            "0$date"
        } else {
            date
        }
        val monthName = resources.getStringArray(R.array.month_name)
        enMonthTV.text = monthName[(activity as PanchangActivity).calendar[Calendar.MONTH]] + ", " + (activity as PanchangActivity).calendar[Calendar.YEAR]
        enVarTV.text = todayPanchangModel.vara
        val index: Int = todayPanchangModel.tithiInt
        moonPhaseIV.setImageDrawable(PanchangCalculation.getMoonPhaseImage(resources, index))

    }

    private fun initView(view: View) {
        initSunMoonView(view)
        initPanchangView(view)
        initMuhuratView(view)
        initDatePickerView(view)
        initTodayPanchangView(view)
    }

    @SuppressLint("CutPasteId")
    fun initDatePickerView(view: View) {
        prevDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.previous_date_btn)
        nextDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.next_date_btn)
        datePickerTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.date_picker_tv)
        todayTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.current_date_btn)
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

    private fun initSunMoonView(view: View) {
        val includedView = view.findViewById<View>(R.id.sun_moon_layout)
        sunRiseLabelTV = getView(includedView, R.id.sun_rise_tv) as TextView
        sunRiseTimeTV = getView(includedView, R.id.sun_rise_val_tv) as TextView
        sunSetLabelTV = getView(includedView, R.id.sun_set_tv) as TextView
        sunSetTimeTV = getView(includedView, R.id.sun_set_val_tv) as TextView
        moonRiseLabelTV = getView(includedView, R.id.moon_rise_tv) as TextView
        moonRiseTimeTV = getView(includedView, R.id.moon_rise_val_tv) as TextView
        moonSetLabelTV = getView(includedView, R.id.moon_set_tv) as TextView
        moonSetTimeTV = getView(includedView, R.id.moon_set_val_tv) as TextView
    }

    private fun initPanchangView(view: View) {
        val includedView = view.findViewById<View>(R.id.sampurn_panchang_layout)
        tithiLabelTV = getView(includedView, R.id.tithi_tv) as TextView
        tithiValTV = getView(includedView, R.id.tithi_val) as TextView
        tithiTimeTV = getView(includedView, R.id.tithi_time) as TextView

        nakshtraLabelTV = getView(includedView, R.id.nakshtra_tv) as TextView
        nakshtraValTV = getView(includedView, R.id.nakshtra_val) as TextView
        nakshtraTimeTV = getView(includedView, R.id.nakshtra_time) as TextView

        yogLabelTV = getView(includedView, R.id.yog_tv) as TextView
        yogValTV = getView(includedView, R.id.yog_val) as TextView
        yogTimeTV = getView(includedView, R.id.yog_time) as TextView

        karanLabelTV = getView(includedView, R.id.karan_tv) as TextView
        karanValTV = getView(includedView, R.id.karan_val) as TextView
        karanTimeTV = getView(includedView, R.id.karan_time) as TextView

        pakshaLabelTV = getView(includedView, R.id.paksha_tv) as TextView
        pakshaValTV = getView(includedView, R.id.paksha_val) as TextView

        dayLabelTV = getView(includedView, R.id.day_tv) as TextView
        dayValTV = getView(includedView, R.id.day_val) as TextView

        moonSignLabelTV = getView(includedView, R.id.moon_sign_tv) as TextView
        moonSignValTV = getView(includedView, R.id.moon_sign_val) as TextView

        rituLabelTV = getView(includedView, R.id.ritu_tv) as TextView
        rituValTV = getView(includedView, R.id.ritu_val) as TextView

        amantaLabelTV = getView(includedView, R.id.amanta_tv) as TextView
        amantaValTV = getView(includedView, R.id.amanta_val) as TextView
        purnimantaLabelTV = getView(includedView, R.id.purnimant_tv) as TextView
        purnimantaValTV = getView(includedView, R.id.purnimant_val) as TextView
        shakSamvatLabelTV = getView(includedView, R.id.saksamvat_tv) as TextView
        shakSamvatValTV = getView(includedView, R.id.saksamvat_val) as TextView
        kaliSamvatLabelTV = getView(includedView, R.id.kalisamvat_tv) as TextView
        kaliSamvatValTV = getView(includedView, R.id.kalisamvat_val) as TextView
        vikramSamvatLabelTV = getView(includedView, R.id.vikramsamvat_tv) as TextView
        vikramSamvatValTV = getView(includedView, R.id.vikramsamvat_val) as TextView
        dayDurationLabelTV = getView(includedView, R.id.day_duration_tv) as TextView
        dayDurationValTV = getView(includedView, R.id.day_duration_val) as TextView


    }

    private fun initMuhuratView(view: View) {
        val includedView = view.findViewById<View>(R.id.sampurn_panchang_layout)
        abhijitMuhuratValTV = getView(includedView, R.id.abhijit_muhurat_tv) as TextView
        abhijitMuhuratTimeTV = getView(includedView, R.id.abhijit_muhurat_duration_tv) as TextView
        ashubMuhuratRV = getView(includedView, R.id.ashub_recycler_view) as RecyclerView
    }


    private fun getView(parentView: View, id: Int): View {
        return parentView.findViewById(id)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.previous_date_btn -> {
                val place = Utility.getPlaceForPanchang()
                (activity as PanchangActivity).calendar.add(Calendar.DATE, -1)
                setDate((activity as PanchangActivity).calendar)
                getSampuranPanchangData((activity as PanchangActivity).calendar, place)

            }
            R.id.next_date_btn -> {
                val place = Utility.getPlaceForPanchang()
                (activity as PanchangActivity).calendar.add(Calendar.DATE, 1)
                setDate((activity as PanchangActivity).calendar)
                getSampuranPanchangData((activity as PanchangActivity).calendar, place)
                //(activity as PanchangActivity).viewModel.changeCalendar((activity as PanchangActivity).calendar)

            }
            R.id.date_picker_tv -> {
                activity?.let { DatePicker().showDatePicker(it).show(requireActivity().supportFragmentManager, "MATERIAL_DATE_PICKER") }
            }
            R.id.current_date_btn -> {
                val place = Utility.getPlaceForPanchang()
                (activity as PanchangActivity).calendar = Calendar.getInstance()
                setDate((activity as PanchangActivity).calendar)
                getSampuranPanchangData((activity as PanchangActivity).calendar, place)
            }
            R.id.place_tv -> {
                (activity as PanchangActivity).openPlaceActivity()
            }

        }
    }

}