package com.bhakti_sangrahalay.viewmodel

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.model.*
import com.bhakti_sangrahalay.panchang.calculations.PanchangCalculation
import com.bhakti_sangrahalay.util.Parser
import com.bhakti_sangrahalay.util.Utility
import com.indoriya.panchang.Place
import java.util.*
import kotlin.collections.ArrayList

class DainikCalendarViewModel : ViewModel() {
    var resources: Resources? = null
    var utility: Utility = Utility()
    var parser: Parser = Parser()
    val dailyCalendarModelLiveData = MutableLiveData<ArrayList<DainikCalendarModel>>()
    val todayPanchangModelLiveData = MutableLiveData<TodayPanchangModelNew>()
    private lateinit var yearFestivalModel: YearFestivalModel


    fun getTodayPanchang(calendar: Calendar, place: Place): TodayPanchangModelNew {
        return TodayPanchangModelNew(
            tithi = PanchangCalculation.getTithi(calendar, place)[0],
            tithiInt = PanchangCalculation.getTithiInt(calendar, place),
            paksh = PanchangCalculation.getPaksh(calendar, place),
            samvat = PanchangCalculation.getVikramSamvatYear(calendar, place),
            hindiMonth = PanchangCalculation.getAmantaMonth(calendar, place),
            vara = PanchangCalculation.getVar(calendar, place),
            calendar = calendar
        )
    }

    private fun getTodayPanchangAsyc(calendar: Calendar, place: Place): TodayPanchangModelNew {

        return TodayPanchangModelNew(
            tithi = PanchangCalculation.getTithi(calendar, place)[0],
            tithiInt = PanchangCalculation.getTithiInt(calendar, place),
            paksh = PanchangCalculation.getPaksh(calendar, place),
            samvat = PanchangCalculation.getVikramSamvatYear(calendar, place),
            hindiMonth = PanchangCalculation.getAmantaMonth(calendar, place),
            vara = PanchangCalculation.getVar(calendar, place),
            calendar = calendar
        )
    }

    private fun getFestivalData(index: Int): MonthFestivalModel {
        return yearFestivalModel.monthFestivalLis[index]
    }

    fun getDailyCalendarData(place: Place) {
        yearFestivalModel =
            parser.getFestivalList(Utility.readFromFile(resources, R.raw.festivals_list))
        val dailyCalendarData = ArrayList<DainikCalendarModel>()
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
        for (i in 0..11) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, i - currentMonth)
            val todayPanchangModel = getTodayPanchangAsyc(calendar, place)
            val festivalModel = getFestivalData(i)
            /*val daysData = getMonthDayData(fragID, calendar, place, resources)*/
            dailyCalendarData.add(
                DainikCalendarModel(
                    calendar,
                    todayPanchangModel,
                    festivalModel,
                    null
                )
            )
        }
        dailyCalendarModelLiveData.postValue(dailyCalendarData)
    }

    fun getTithiInt(calendar: Calendar, place: Place): Int {
        return PanchangCalculation.getTithiInt(calendar, place)
    }


}