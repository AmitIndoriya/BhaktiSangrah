package com.bhakti_sangrahalay.panchang.calculations

import android.content.res.Resources
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.model.ChogdiyaBean
import com.bhakti_sangrahalay.panchang.model.PanchangBean
import com.bhakti_sangrahalay.panchang.util.PanchangUtility
import com.bhakti_sangrahalay.util.Utility
import com.indoriya.panchang.Masa
import com.indoriya.panchang.Muhurta
import com.indoriya.panchang.Place
import java.util.*

object ChogdiyaCalculation {
    fun prepareDayChogdiaData(resources: Resources, calendar: Calendar, place: Place): ArrayList<PanchangBean> {
        val arrayList = ArrayList<PanchangBean>()
        val chogdiyaBeanArrayList: ArrayList<ChogdiyaBean> = getDayChogdiaData(resources, calendar, place)
        var panchangBean: PanchangBean
        var chogdiyaBean: ChogdiyaBean
        for (i in chogdiyaBeanArrayList.indices) {
            chogdiyaBean = chogdiyaBeanArrayList[i]
            panchangBean = PanchangBean(chogdiyaBean.planetName, Utility.convertTimeToAmPm(chogdiyaBean.enterTime).replace("+", "Tomorrow\n"), Utility.convertTimeToAmPm(chogdiyaBean.exitTime).replace("+", "Tomorrow\n"))
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun prepareNightChogdiaData(resources: Resources, calendar: Calendar, place: Place): ArrayList<PanchangBean> {
        val arrayList = ArrayList<PanchangBean>()
        val chogdiyaBeanArrayList: ArrayList<ChogdiyaBean> = getNightChogdiaData(resources, calendar, place)
        var panchangBean: PanchangBean
        var chogdiyaBean: ChogdiyaBean
        for (i in chogdiyaBeanArrayList.indices) {
            chogdiyaBean = chogdiyaBeanArrayList[i]
            panchangBean = PanchangBean(chogdiyaBean.planetName, Utility.convertTimeToAmPm(chogdiyaBean.enterTime).replace("+", "Tomorrow\n"), Utility.convertTimeToAmPm(chogdiyaBean.exitTime).replace("+", "Tomorrow\n"))
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun getCurrentChogdia(resources: Resources, calendar: Calendar, place: Place): ChogdiyaBean? {
        val horaList: ArrayList<ChogdiyaBean> = getChogdiaData(resources, calendar, place)
        var chogdiyaBean: ChogdiyaBean? = null
        for (i in horaList.indices) {
            chogdiyaBean = horaList[i]
            if (PanchangUtility.isTimeBetweenTwoTime(chogdiyaBean.enterTime, chogdiyaBean.exitTime)) {
                /*val timeDuration = Utility.convertTimeToAmPm(chogdiyaBean.enterTime).replace("+", "Tomorrow\n") + " - " + Utility.convertTimeToAmPm(chogdiyaBean.exitTime).replace("+", "Tomorrow\n")
                panchangBean = PanchangBean(chogdiyaBean.planetName, timeDuration, chogdiyaBean.planetMeaning)*/
                break
            }
        }
        return chogdiyaBean
    }

    private fun getChogdiaData(resources: Resources, cal: Calendar, place: Place): ArrayList<ChogdiyaBean> {

        val jd = Masa.toJulian(cal[Calendar.YEAR], cal[Calendar.MONTH] + 1, cal[Calendar.DATE])
        val sunRise: Double = PanchangCalculation.getSunRiseTimeInDouble(cal, place)
        val sunSet: Double = PanchangCalculation.getSunSetTimeInDouble(cal, place)
        val arrayList = ArrayList<ChogdiyaBean>()
        val calendar: Calendar = GregorianCalendar(cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DATE])
        val reslut = calendar[Calendar.DAY_OF_WEEK]
        val valueforday: Int = getStartChoghadiaForDay(reslut)
        val valuefornight: Int = getStartChoghadiaForNight(reslut)
        val chogadiyanameforday = getChogadiaDayName(resources, valueforday)
        val chogadiyanamefornight = getChogadiaNightName(resources, valuefornight)
        val chogadiyanamefordaymeaning = getChogadiaDayNameMeaning(resources, valueforday)
        val chogadiyanamefornightmeaning = getChogadiaNightNameMeaning(resources, valuefornight)
        var chogdiaName: String
        var chogdiaMeaning: String
        var entryTime: String
        var exitTime: String
        var chogdiyaBean: ChogdiyaBean
        for (i in 0..15) {

            if (i < 8) {
                chogdiaName = chogadiyanameforday[i]
                chogdiaMeaning = chogadiyanamefordaymeaning[i]
                entryTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getDayDivisons(jd, place, sunRise, 8)[i])
                exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getDayDivisons(jd, place, sunRise, 8)[i + 1])
            } else {
                chogdiaName = chogadiyanamefornight[i - 8]
                chogdiaMeaning = chogadiyanamefornightmeaning[i - 8]
                entryTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getNightDivisons(jd, place, sunSet, 8)[i - 8])
                exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getNightDivisons(jd, place, sunSet, 8)[i - 8 + 1])
            }
            chogdiyaBean = ChogdiyaBean(planetName = chogdiaName, planetMeaning = chogdiaMeaning,
                    enterTime = entryTime,
                    exitTime = exitTime,
                    duration = PanchangUtility.convertTimeToAmPm(entryTime) + " " + resources.getString(R.string.from) + " " + PanchangUtility.convertTimeToAmPm(exitTime))

            arrayList.add(chogdiyaBean)
        }
        return arrayList
    }

    fun getDayChogdiaData(resources: Resources, cal: Calendar, place: Place): ArrayList<ChogdiyaBean> {

        val jd = Masa.toJulian(cal[Calendar.YEAR], cal[Calendar.MONTH] + 1, cal[Calendar.DATE])
        val sunRise: Double = PanchangCalculation.getSunRiseTimeInDouble(cal, place)
        val arrayList = ArrayList<ChogdiyaBean>()
        val calendar: Calendar = GregorianCalendar(cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DATE])
        val reslut = calendar[Calendar.DAY_OF_WEEK]
        val valueforday: Int = getStartChoghadiaForDay(reslut)
        val chogadiyanameforday = getChogadiaDayName(resources, valueforday)
        val chogadiyanamefordaymeaning = getChogadiaDayNameMeaning(resources, valueforday)
        var chogdiaName: String
        var chogdiaMeaning: String
        var entryTime: String
        var exitTime: String
        var chogdiyaBean: ChogdiyaBean
        for (i in 0..7) {
            chogdiaName = chogadiyanameforday[i]
            chogdiaMeaning = chogadiyanamefordaymeaning[i]
            entryTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getDayDivisons(jd, place, sunRise, 8)[i])
            exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getDayDivisons(jd, place, sunRise, 8)[i + 1])
            chogdiyaBean = ChogdiyaBean(planetName = chogdiaName, planetMeaning = chogdiaMeaning, enterTime = entryTime, exitTime = exitTime,
                    duration = PanchangUtility.convertTimeToAmPm(entryTime) + " " + resources.getString(R.string.from) + " " + PanchangUtility.convertTimeToAmPm(exitTime))

            arrayList.add(chogdiyaBean)
        }
        return arrayList
    }

    fun getNightChogdiaData(resources: Resources, cal: Calendar, place: Place): ArrayList<ChogdiyaBean> {

        val jd = Masa.toJulian(cal[Calendar.YEAR], cal[Calendar.MONTH] + 1, cal[Calendar.DATE])
        val sunSet: Double = PanchangCalculation.getSunSetTimeInDouble(cal, place)
        val arrayList = ArrayList<ChogdiyaBean>()
        val calendar: Calendar = GregorianCalendar(cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DATE])
        val reslut = calendar[Calendar.DAY_OF_WEEK]
        val valuefornight: Int = getStartChoghadiaForNight(reslut)
        val chogadiyanamefornight = getChogadiaNightName(resources, valuefornight)
        val chogadiyanamefornightmeaning = getChogadiaNightNameMeaning(resources, valuefornight)
        var chogdiaName: String
        var chogdiaMeaning: String
        var entryTime: String
        var exitTime: String
        var chogdiyaBean: ChogdiyaBean
        for (i in 0..7) {

            chogdiaName = chogadiyanamefornight[i]
            chogdiaMeaning = chogadiyanamefornightmeaning[i]
            entryTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getNightDivisons(jd, place, sunSet, 8)[i])
            exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getNightDivisons(jd, place, sunSet, 8)[i + 1])

            chogdiyaBean = ChogdiyaBean(planetName = chogdiaName, planetMeaning = chogdiaMeaning, enterTime = entryTime, exitTime = exitTime,
                    duration = PanchangUtility.convertTimeToAmPm(entryTime) + " " + resources.getString(R.string.from) + " " + PanchangUtility.convertTimeToAmPm(exitTime))

            arrayList.add(chogdiyaBean)
        }
        return arrayList
    }

    private fun getStartChoghadiaForDay(day: Int): Int {
        var startDay = 0
        when (day) {
            Calendar.SUNDAY -> startDay = 0
            Calendar.MONDAY -> startDay = 3
            Calendar.TUESDAY -> startDay = 6
            Calendar.WEDNESDAY -> startDay = 2
            Calendar.THURSDAY -> startDay = 5
            Calendar.FRIDAY -> startDay = 1
            Calendar.SATURDAY -> startDay = 4
        }
        return startDay
    }

    private fun getStartChoghadiaForNight(day: Int): Int {
        var startNight = 0
        when (day) {
            Calendar.SUNDAY -> startNight = 0
            Calendar.MONDAY -> startNight = 2
            Calendar.TUESDAY -> startNight = 4
            Calendar.WEDNESDAY -> startNight = 6
            Calendar.THURSDAY -> startNight = 1
            Calendar.FRIDAY -> startNight = 3
            Calendar.SATURDAY -> startNight = 5
        }
        return startNight
    }

    private fun getChogadiaDayName(resources: Resources, startDay: Int): ArrayList<String> {
        val dayChoghadiaName = ArrayList<String>()
        val chogadiyadayName = resources.getStringArray(
                R.array.chogadiya_day_names_list)
        var j = 0
        var i = startDay
        while (i < 7) {
            dayChoghadiaName.add(chogadiyadayName[i])
            i++
            j++
        }
        for (l in 0..startDay) {
            dayChoghadiaName.add(chogadiyadayName[l])
            j++
        }
        return dayChoghadiaName
    }

    private fun getChogadiaDayNameMeaning(resources: Resources, startDay: Int): ArrayList<String> {
        val dayChoghadiaNameMeaning = java.util.ArrayList<String>()
        val chogadiyadayName = resources.getStringArray(
                R.array.chogadiya_day_names_list_meaning)
        var j = 0
        var i = startDay
        while (i < 7) {
            dayChoghadiaNameMeaning.add(chogadiyadayName[i])
            i++
            j++
        }
        for (l in 0..startDay) {
            dayChoghadiaNameMeaning.add(chogadiyadayName[l])
            j++
        }
        return dayChoghadiaNameMeaning
    }

    private fun getChogadiaNightName(resources: Resources, startDay: Int): ArrayList<String> {
        val nightChoghadiaName = ArrayList<String>()
        val chogadiyaNightName = resources.getStringArray(R.array.chogadiya_night_names_list)
        var j = 0
        var i = startDay
        while (i < 7) {
            nightChoghadiaName.add(chogadiyaNightName[i])
            i++
            j++
        }
        for (l in 0..startDay) {
            nightChoghadiaName.add(chogadiyaNightName[l])
            j++
        }
        return nightChoghadiaName
    }

    private fun getChogadiaNightNameMeaning(resources: Resources, startDay: Int): ArrayList<String> {
        val nightChoghadiaNameMeaning = ArrayList<String>()
        val chogadiyaNightNameMeaning = resources.getStringArray(R.array.chogadiya_night_names_list_meaning)
        var j = 0
        var i = startDay
        while (i < 7) {
            if (chogadiyaNightNameMeaning.size > i) {
                nightChoghadiaNameMeaning.add(chogadiyaNightNameMeaning[i])
            }
            i++
            j++
        }
        for (l in 0..startDay) {
            if (chogadiyaNightNameMeaning.size > l) {
                nightChoghadiaNameMeaning.add(chogadiyaNightNameMeaning[l])
            }
            j++
        }
        return nightChoghadiaNameMeaning
    }
}