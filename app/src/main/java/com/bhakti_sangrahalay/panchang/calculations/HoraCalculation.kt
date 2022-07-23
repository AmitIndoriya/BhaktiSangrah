package com.bhakti_sangrahalay.panchang.calculations

import android.content.res.Resources
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.model.HoraBean
import com.bhakti_sangrahalay.panchang.model.PanchangBean
import com.bhakti_sangrahalay.panchang.util.PanchangUtility
import com.bhakti_sangrahalay.util.Utility
import com.indoriya.panchang.Masa
import com.indoriya.panchang.Muhurta
import com.indoriya.panchang.Place
import java.util.*

object HoraCalculation {
    fun prepareDayHoraData(resources: Resources, calendar: Calendar, place: Place): ArrayList<PanchangBean> {
        val arrayList = ArrayList<PanchangBean>()
        val horaBeanArrayList = getDayHoraData(calendar[Calendar.DAY_OF_WEEK] - 1, resources, calendar, place)
        var panchangBean: PanchangBean
        var horaBean: HoraBean
        for (i in horaBeanArrayList.indices) {
            horaBean = horaBeanArrayList[i]
            panchangBean = PanchangBean(horaBean.planetName, Utility.convertTimeToAmPm(horaBean.enterTime).replace("+", "Tomorrow\n"), Utility.convertTimeToAmPm(horaBean.exitTime).replace("+", "Tomorrow\n"))
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun prepareNightHoraData(resources: Resources, calendar: Calendar, place: Place): ArrayList<PanchangBean> {
        val arrayList = ArrayList<PanchangBean>()
        val horaBeanArrayList = getNightHoraData(calendar[Calendar.DAY_OF_WEEK] - 1, resources, calendar, place)
        var panchangBean: PanchangBean
        var horaBean: HoraBean
        for (i in horaBeanArrayList.indices) {
            horaBean = horaBeanArrayList[i]
            panchangBean = PanchangBean(horaBean.planetName, Utility.convertTimeToAmPm(horaBean.enterTime).replace("+", "Tomorrow\n"), Utility.convertTimeToAmPm(horaBean.exitTime).replace("+", "Tomorrow\n"))
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun getCurrentHora(resources: Resources, calendar: Calendar, place: Place): HoraBean? {
        val horaList = getHoradata(calendar[Calendar.DAY_OF_WEEK] - 1, resources, calendar, place)
        var horaBean: HoraBean? = null
        for (i in horaList.indices) {
            horaBean = horaList[i]
            if (PanchangUtility.isTimeBetweenTwoTime(horaBean.enterTime, horaBean.exitTime)) {
                /*val horaTime = Utility.convertTimeToAmPm(horaBean.entertimedata).replace("+", "Tomorrow\n") + " - " +
                        Utility.convertTimeToAmPm(horaBean.exittimedata).replace("+", "Tomorrow\n")
                panchangBean = PanchangBean(horaBean.planetdata, horaTime, horaBean.planetCurrentHorameaning)*/
                break
            }
        }
        return horaBean
    }

    private fun getHoradata(dayOfMonth: Int, resources: Resources, calendar: Calendar, place: Place): ArrayList<HoraBean> {
        val jd = Masa.toJulian(calendar[Calendar.YEAR], calendar[Calendar.MONTH] + 1, calendar[Calendar.DATE])
        val sunRise: Double = PanchangCalculation.getSunRiseTimeInDouble(calendar, place)
        val sunSet: Double = PanchangCalculation.getSunSetTimeInDouble(calendar, place)
        val dayLordForDayHora = IntArray(24)
        val horaBeanArrayList: ArrayList<HoraBean> = ArrayList<HoraBean>()
        try {
            var entryTime: Double
            var exitTime: Double
            val planetName = resources.getStringArray(R.array.hora_planets)
            val planetNameMeaning = resources.getStringArray(R.array.hora_planets_meaning)
            val planetNameMeaningForCurrentHora = resources.getStringArray(R.array.pla_mean)
            for (i in 0..23) {
                dayLordForDayHora[i] = (dayOfMonth + i * 5) % 7

                if (i >= 12) {
                    entryTime = Muhurta.getNightDivisons(jd, place, sunSet, 12)[i - 12]
                    exitTime = Muhurta.getNightDivisons(jd, place, sunSet, 12)[i - 12 + 1]
                } else {
                    entryTime = Muhurta.getDayDivisons(jd, place, sunRise, 12)[i]
                    exitTime = Muhurta.getDayDivisons(jd, place, sunRise, 12)[i + 1]
                }
                val hora = HoraBean(planetName = planetName[dayLordForDayHora[i]],
                        planetMeaning = planetNameMeaning[dayLordForDayHora[i]],
                        planetCurrentHoraMeaning = planetNameMeaningForCurrentHora[dayLordForDayHora[i]],
                        enterTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(entryTime),
                        exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(exitTime),
                        duration = PanchangUtility.convertTimeToAmPm(PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(entryTime)) + " " + resources.getString(R.string.from) + " " + PanchangUtility.convertTimeToAmPm(PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(exitTime))

                )

                horaBeanArrayList.add(hora)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return horaBeanArrayList
    }

    fun getDayHoraData(dayOfMonth: Int, resources: Resources, calendar: Calendar, place: Place): ArrayList<HoraBean> {
        val jd = Masa.toJulian(calendar[Calendar.YEAR], calendar[Calendar.MONTH] + 1, calendar[Calendar.DATE])
        val sunRise: Double = PanchangCalculation.getSunRiseTimeInDouble(calendar, place)
        val dayLordForDayHora = IntArray(12)
        val horaBeanArrayList: ArrayList<HoraBean> = ArrayList<HoraBean>()
        try {
            var entryTime: Double
            var exitTime: Double
            val planetName = resources.getStringArray(R.array.hora_planets)
            val planetNameMeaning = resources.getStringArray(R.array.hora_planets_meaning)
            val planetNameMeaningforCurrentHora = resources.getStringArray(R.array.pla_mean)
            for (i in 0..11) {
                dayLordForDayHora[i] = (dayOfMonth + i * 5) % 7
                entryTime = Muhurta.getDayDivisons(jd, place, sunRise, 12)[i]
                exitTime = Muhurta.getDayDivisons(jd, place, sunRise, 12)[i + 1]

                val hora = HoraBean(planetName = planetName[dayLordForDayHora[i]],
                        planetMeaning = planetNameMeaning[dayLordForDayHora[i]],
                        planetCurrentHoraMeaning = planetNameMeaningforCurrentHora[dayLordForDayHora[i]],
                        enterTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(entryTime),
                        exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(exitTime),
                        duration = PanchangUtility.convertTimeToAmPm(PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(entryTime)) + " " + resources.getString(R.string.from) + " " + PanchangUtility.convertTimeToAmPm(PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(exitTime))

                )

                horaBeanArrayList.add(hora)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return horaBeanArrayList
    }

    fun getNightHoraData(dayOfMonth: Int, resources: Resources, calendar: Calendar, place: Place): ArrayList<HoraBean> {
        val jd = Masa.toJulian(calendar[Calendar.YEAR], calendar[Calendar.MONTH] + 1, calendar[Calendar.DATE])
        val sunSet: Double = PanchangCalculation.getSunSetTimeInDouble(calendar, place)
        val dayLordForDayHora = IntArray(12)
        val horaBeanArrayList: ArrayList<HoraBean> = ArrayList<HoraBean>()
        try {
            var entryTime: Double
            var exitTime: Double
            val planetName = resources.getStringArray(R.array.hora_planets)
            val planetNameMeaning = resources.getStringArray(R.array.hora_planets_meaning)
            val planetNameMeaningForCurrentHora = resources.getStringArray(R.array.pla_mean)
            for (i in 0..11) {
                dayLordForDayHora[i] = (dayOfMonth + i * 5) % 7
                entryTime = Muhurta.getNightDivisons(jd, place, sunSet, 12)[i]
                exitTime = Muhurta.getNightDivisons(jd, place, sunSet, 12)[i + 1]

                val hora = HoraBean(planetName = planetName[dayLordForDayHora[i]],
                        planetMeaning = planetNameMeaning[dayLordForDayHora[i]],
                        planetCurrentHoraMeaning = planetNameMeaningForCurrentHora[dayLordForDayHora[i]],
                        enterTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(entryTime),
                        exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(exitTime),
                        duration = PanchangUtility.convertTimeToAmPm(PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(entryTime)) + " " + resources.getString(R.string.from) + " " + PanchangUtility.convertTimeToAmPm(PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(exitTime))
                )

                horaBeanArrayList.add(hora)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return horaBeanArrayList
    }
}