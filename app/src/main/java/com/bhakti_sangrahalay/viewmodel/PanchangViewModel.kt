package com.bhakti_sangrahalay.viewmodel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.calculations.*
import com.bhakti_sangrahalay.panchang.model.*
import com.bhakti_sangrahalay.util.Parser
import com.bhakti_sangrahalay.util.Utility
import com.indoriya.panchang.Place
import java.util.*
import kotlin.collections.ArrayList

class PanchangViewModel : BaseViewModel() {
    val isPlaceChangedLiveData = MutableLiveData<Boolean>()
    val calendarLiveData = MutableLiveData<Calendar>()
    var parser: Parser = Parser()
    val sunRiseTime = MutableLiveData<String>()
    val sunSetTime = MutableLiveData<String>()
    val moonRiseAndSetTime = MutableLiveData<ArrayList<String>>()
    val tithiArrayList = MutableLiveData<ArrayList<String>>()
    val nakshtraLiveData = MutableLiveData<ArrayList<String>>()
    val yogLiveData = MutableLiveData<ArrayList<String>>()
    val karanLiveData = MutableLiveData<ArrayList<String>>()
    val pakshaLiveData = MutableLiveData<String>()
    val varLiveData = MutableLiveData<String>()
    val moonSignLiveData = MutableLiveData<String>()
    val rituLiveData = MutableLiveData<String>()
    val amantMonthLiveData = MutableLiveData<String>()
    val purnimantMonthLiveData = MutableLiveData<String>()
    val shakaSamvatLiveData = MutableLiveData<String>()
    val kaliSamvatLiveData = MutableLiveData<String>()
    val vikramSamvatLiveData = MutableLiveData<String>()
    val dayDurationLiveData = MutableLiveData<String>()
    val todayPanchangModelLiveData = MutableLiveData<TodayPanchangModel>()

    val shubhMuhuratLiveData = MutableLiveData<ArrayList<String>>()
    val ashubhMuhuratLiveData = MutableLiveData<ArrayList<ArrayList<String>>>()


    val dayDoGhatiLiveData = MutableLiveData<ArrayList<DoGhatiBean>>()
    val nightDoGhatiLiveData = MutableLiveData<ArrayList<DoGhatiBean>>()
    val todayDoGhatiLiveData = MutableLiveData<DoGhatiBean>()

    val dayChogdiyaLiveData = MutableLiveData<ArrayList<ChogdiyaBean>>()
    val nightChogdiyaLiveData = MutableLiveData<ArrayList<ChogdiyaBean>>()
    val todayChogdiyaLiveData = MutableLiveData<ChogdiyaBean>()

    val dayHoraLiveData = MutableLiveData<ArrayList<HoraBean>>()
    val nightHoraLiveData = MutableLiveData<ArrayList<HoraBean>>()
    val todayHoraLiveData = MutableLiveData<HoraBean>()
    val dateLiveData = MutableLiveData<Calendar>()

    val rahuKaalLiveData = MutableLiveData<ArrayList<RahuKaalBean>>()
    val currentRahuKaalLiveData = MutableLiveData<RahuKaalBean>()
    fun changePlace(boolean: Boolean) {
        isPlaceChangedLiveData.value = boolean
    }

    fun changeCalendar(calendar: Calendar) {
        calendarLiveData.value = calendar
    }

    fun setDateFromDatePicker(calendar: Calendar) {
        dateLiveData.value = calendar
    }

    fun getSunRiseTime(calendar: Calendar, place: Place) {
        val sunRiseTime = PanchangCalculation.getSunRiseTime(calendar, place)
        this.sunRiseTime.value = Utility.convertTimeToAmPm(sunRiseTime)
    }

    fun getSunSetTime(calendar: Calendar, place: Place) {
        val sunSetTime = PanchangCalculation.getSunSetTime(calendar, place)
        this.sunSetTime.value = Utility.convertTimeToAmPm(sunSetTime)
    }

    fun getMoonRiseAndSetTime(calendar: Calendar, place: Place) {
        val moonRiseAndSetTime = PanchangCalculation.getMoonRiseSetTime(calendar, place)
        this.moonRiseAndSetTime.value = moonRiseAndSetTime
    }

    fun getTithi(calendar: Calendar, place: Place) {
        val tithiArrayList = PanchangCalculation.getTithi(calendar, place)
        this.tithiArrayList.value = tithiArrayList
    }

    fun getNakshtra(calendar: Calendar, place: Place) {
        val nakshtra = PanchangCalculation.getNakshtra(calendar, place)
        nakshtraLiveData.value = nakshtra
    }

    fun getYog(calendar: Calendar, place: Place) {
        val yog = PanchangCalculation.getYog(calendar, place)
        yogLiveData.value = yog
    }

    fun getKaran(calendar: Calendar, place: Place) {
        val karan = PanchangCalculation.getKaran(calendar, place)
        karanLiveData.value = karan
    }

    fun getPaksha(calendar: Calendar, place: Place) {
        val paksha = PanchangCalculation.getPaksh(calendar, place)
        pakshaLiveData.value = paksha
    }

    fun getVar(calendar: Calendar, place: Place) {
        val vara = PanchangCalculation.getVar(calendar, place)
        varLiveData.value = vara
    }

    fun getRitu(calendar: Calendar, place: Place) {
        val ritu = PanchangCalculation.getRitu(calendar, place)
        rituLiveData.value = ritu
    }

    fun getMoonSign(calendar: Calendar, place: Place) {
        val moonSign = PanchangCalculation.getMoonSign(calendar, place)[0]
        moonSignLiveData.value = moonSign
    }

    fun getAmantaMonth(calendar: Calendar, place: Place) {
        val amantMonth = PanchangCalculation.getAmantaMonth(calendar, place)
        amantMonthLiveData.value = amantMonth
    }

    fun getPurnimantMonth(calendar: Calendar, place: Place) {
        val purnimantMonth = PanchangCalculation.getPurnimantMonth(calendar, place)
        purnimantMonthLiveData.value = purnimantMonth
    }

    fun getShakaSamvat(calendar: Calendar, place: Place) {
        val shakaSamvat = PanchangCalculation.getShakaSamvatYear(calendar, place)
        shakaSamvatLiveData.value = shakaSamvat
    }

    fun getKaliSamvat(calendar: Calendar, place: Place) {
        val kaliSamvat = PanchangCalculation.getKaliSamvatYear(calendar, place)
        kaliSamvatLiveData.value = kaliSamvat
    }

    fun getVikramSamvat(calendar: Calendar, place: Place) {
        val vikramSamvat = PanchangCalculation.getVikramSamvatYear(calendar, place)
        vikramSamvatLiveData.value = vikramSamvat
    }

    fun getDayDuration(calendar: Calendar, place: Place) {
        val dayDuration = PanchangCalculation.getDayDuration(calendar, place)
        dayDurationLiveData.value = dayDuration
    }

    fun getShubhMuhurat(calendar: Calendar, place: Place) {
        val abhijitMuhurat = PanchangCalculation.getAbhijitMuhurat(calendar, place)
        shubhMuhuratLiveData.value = abhijitMuhurat
    }

    fun getAshubhMuhurat(calendar: Calendar, place: Place) {
        val mhuratDuration = PanchangCalculation.getAshubMuhuratList(calendar, place)
        ashubhMuhuratLiveData.value = mhuratDuration
    }

    fun getTodayPanchang(calendar: Calendar, place: Place) {
        val todayPanchangModel = TodayPanchangModel(
            tithi = PanchangCalculation.getTithi(calendar, place)[0],
            tithiInt = PanchangCalculation.getTithiInt(calendar, place),
            paksh = PanchangCalculation.getPaksh(calendar, place),
            samvat = PanchangCalculation.getVikramSamvatYear(calendar, place),
            hindiMonth = PanchangCalculation.getAmantaMonth(calendar, place),
            vara = PanchangCalculation.getVar(calendar, place)
        )
        todayPanchangModelLiveData.value = todayPanchangModel
    }

    fun getDayDoGhatiData(resources: Resources, calendar: Calendar) {
        val place = Utility.getPlaceForPanchang()
        val dayDoGhatiData = DoGhatiCalculation.getDayDoGhatiData(resources, calendar, place)
        dayDoGhatiLiveData.value = dayDoGhatiData
    }

    fun getNightDoGhatiData(resources: Resources, calendar: Calendar) {
        val place = Utility.getPlaceForPanchang()
        val nightDoGhatiData = DoGhatiCalculation.getNightDoGhatiData(resources, calendar, place)
        nightDoGhatiLiveData.value = nightDoGhatiData
    }

    fun getCurrentDoGhatiData(resources: Resources) {
        val place = Utility.getPlaceForPanchang()
        val calendar = Calendar.getInstance()
        val todayDoGhatiData = DoGhatiCalculation.getCurrentDoGhati(resources, calendar, place)
        todayDoGhatiLiveData.value = todayDoGhatiData
    }

    fun getDayChogdiyaData(resources: Resources, calendar: Calendar) {
        val place = Utility.getPlaceForPanchang()
        val dayChogdiyaData = ChogdiyaCalculation.getDayChogdiaData(resources, calendar, place)
        dayChogdiyaLiveData.value = dayChogdiyaData
    }

    fun getNightChogdiyaData(resources: Resources, calendar: Calendar) {
        val place = Utility.getPlaceForPanchang()
        val nightChogdiyaData = ChogdiyaCalculation.getNightChogdiaData(resources, calendar, place)
        nightChogdiyaLiveData.value = nightChogdiyaData
    }

    fun getCurrentChogdiyaData(resources: Resources) {
        val place = Utility.getPlaceForPanchang()
        val calendar = Calendar.getInstance()
        val todayChogdiyaData = ChogdiyaCalculation.getCurrentChogdia(resources, calendar, place)
        todayChogdiyaLiveData.value = todayChogdiyaData
    }


    fun getDayHoraData(resources: Resources, calendar: Calendar) {
        val place = Utility.getPlaceForPanchang()
        val dayHoraData = HoraCalculation.getDayHoraData(
            calendar[Calendar.DAY_OF_WEEK] - 1,
            resources,
            calendar,
            place
        )
        dayHoraLiveData.value = dayHoraData
    }

    fun getNightHoraData(resources: Resources, calendar: Calendar) {
        val place = Utility.getPlaceForPanchang()
        val nightHoraData = HoraCalculation.getNightHoraData(
            calendar[Calendar.DAY_OF_WEEK] - 1,
            resources,
            calendar,
            place
        )
        nightHoraLiveData.value = nightHoraData
    }

    fun getCurrentHoraData(resources: Resources) {
        val place = Utility.getPlaceForPanchang()
        val calendar = Calendar.getInstance()
        val todayHoraData = HoraCalculation.getCurrentHora(resources, calendar, place)
        todayHoraLiveData.value = todayHoraData
    }

    fun getWeeklyRahuKaalList(calendar: Calendar, place: Place) {
        val rahukaalList = RahuKaalCalculation.getRahuKaalData(calendar, place)
        rahuKaalLiveData.value = rahukaalList
    }

    fun getCurrentRahuKaal(place: Place) {
        val currentRahuKaal = RahuKaalCalculation.getCurrentRahuKaal(place)
        currentRahuKaalLiveData.value = currentRahuKaal
    }

    fun getPanchakData(resources: Resources): ArrayList<PanchakBean> {
        return parser.getPanchakList(Utility().readFromFile(resources, R.raw.panchak))
    }

    fun getBhadraData(calendar: Calendar, place: Place): ArrayList<BhadraBean> {
        return BhardraCalculation.getBhadraData(calendar, place)
    }
}