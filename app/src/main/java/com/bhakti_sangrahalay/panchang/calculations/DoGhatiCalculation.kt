package com.bhakti_sangrahalay.panchang.calculations

import android.content.res.Resources
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.model.DoGhatiBean
import com.bhakti_sangrahalay.panchang.model.PanchangBean
import com.bhakti_sangrahalay.panchang.util.PanchangUtility
import com.bhakti_sangrahalay.util.Utility
import com.indoriya.panchang.Masa
import com.indoriya.panchang.Muhurta
import com.indoriya.panchang.Place
import java.util.*

object DoGhatiCalculation {
    fun prepareDayDoGhatiData(resources: Resources, calendar: Calendar, place: Place): ArrayList<PanchangBean> {
        val arrayList: ArrayList<PanchangBean> = ArrayList<PanchangBean>()
        val doGhatiBeanArrayList: ArrayList<DoGhatiBean> = getDayDoGhatiData(resources, calendar, place)
        var panchangBean: PanchangBean
        var doGhatiBean: DoGhatiBean
        for (i in doGhatiBeanArrayList.indices) {
            doGhatiBean = doGhatiBeanArrayList[i]
            panchangBean = PanchangBean(doGhatiBean.planetName, Utility.convertTimeToAmPm(doGhatiBean.enterTime).replace("+", "Tomorrow\n"), Utility.convertTimeToAmPm(doGhatiBean.exitTime).replace("+", "Tomorrow\n"))
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun prepareNightDoGhatiData(resources: Resources, calendar: Calendar, place: Place): ArrayList<PanchangBean> {
        val arrayList: ArrayList<PanchangBean> = ArrayList<PanchangBean>()
        val doGhatiBeanArrayList: ArrayList<DoGhatiBean> = getNightDoGhatiData(resources, calendar, place)
        var panchangBean: PanchangBean
        var doGhatiBean: DoGhatiBean
        for (i in doGhatiBeanArrayList.indices) {
            doGhatiBean = doGhatiBeanArrayList[i]
            panchangBean = PanchangBean(doGhatiBean.planetName, Utility.convertTimeToAmPm(doGhatiBean.enterTime).replace("+", "Tomorrow\n"), Utility.convertTimeToAmPm(doGhatiBean.exitTime).replace("+", "Tomorrow\n"))
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun getCurrentDoGhati(resources: Resources, calendar: Calendar, place: Place): DoGhatiBean? {
        val horaList: ArrayList<DoGhatiBean> = getDoGhatiData(resources, calendar, place)
        var doGhatiBean: DoGhatiBean? = null
        for (i in horaList.indices) {
            doGhatiBean = horaList[i]
            if (PanchangUtility.isTimeBetweenTwoTime(doGhatiBean.enterTime, doGhatiBean.exitTime)) {
                /*  val duration = com.bhakti_sangrahalay.util.Utility.convertTimeToAmPm(doGhatiBean.enterTime).replace("+", "Tomorrow\n") + " - " + com.bhakti_sangrahalay.util.Utility.convertTimeToAmPm(doGhatiBean.exitTime).replace("+", "Tomorrow\n")
                  panchangBean = PanchangBean(doGhatiBean.planetName, duration, doGhatiBean.planetCurrentHoraMeaning)*/

                break
            }
        }
        return doGhatiBean
    }

    private fun getDoGhatiData(resources: Resources, cal: Calendar, place: Place): ArrayList<DoGhatiBean> {
        // val place = com.bhakti_sangrahalay.util.Utility.getPlaceForPanchang()
        val jd = Masa.toJulian(cal[Calendar.YEAR], cal[Calendar.MONTH] + 1, cal[Calendar.DATE])
        val sunRise: Double = PanchangCalculation.getSunRiseTimeInDouble(cal,place)
        val sunSet: Double = PanchangCalculation.getSunSetTimeInDouble(cal,place)
        val arrayList: ArrayList<DoGhatiBean> = ArrayList<DoGhatiBean>()
        val planetName = resources.getStringArray(R.array.do_ghati_name_list)
        val planetNameMeaning = resources.getStringArray(R.array.do_ghati_name_list_Muhurat)
        val planetNameMeaningForCurrentHora = resources.getStringArray(R.array.do_ghati_muhurut_meaning)
        val doGhatiSecondMeaning = resources.getStringArray(R.array.do_ghati_name_list_meaning_second)
        val doGhatiSecondMeaningWikipedia = resources.getStringArray(R.array.do_ghati_name_list_meaning)
        val doGhatiMuhurat = resources.getStringArray(R.array.do_ghati_huhurat)
        var entryTime: String
        var exitTime: String
        for (i in 0..29) {
            var plaName = ""
            when {
                i <= 2 -> {
                    plaName = planetName[0]
                }
                i <= 5 -> {
                    plaName = planetName[1]
                }
                i <= 8 -> {
                    plaName = planetName[2]
                }
                i <= 11 -> {
                    plaName = planetName[3]
                }
                i <= 14 -> {
                    plaName = planetName[4]
                }
                i <= 17 -> {
                    plaName = planetName[5]
                }
                i <= 21 -> {
                    plaName = planetName[6]
                }
                i <= 22 -> {
                    plaName = planetName[7]
                }
                i <= 27 -> {
                    plaName = planetName[8]
                }
                i <= 29 -> {
                    plaName = planetName[9]
                }
            }
            if (i < 15) {
                entryTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getDayDivisons(jd, place, sunRise, 15)[i])
                exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getDayDivisons(jd, place, sunRise, 15)[i + 1])
            } else {
                entryTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getNightDivisons(jd, place, sunSet, 15)[i - 15])
                exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getNightDivisons(jd, place, sunSet, 15)[i - 15 + 1])
            }
            val doGhatiBean = DoGhatiBean(planetName = plaName,
                    planetMeaning = planetNameMeaning[i],
                    doGhatiSecondMeaning = doGhatiSecondMeaning[i],
                    doGhatiSecondMeaningWikipedia = doGhatiSecondMeaningWikipedia[i],
                    planetCurrentHoraMeaning = planetNameMeaningForCurrentHora[i],
                    doGhatiMuhurat = doGhatiMuhurat[i],
                    enterTime = entryTime,
                    exitTime = exitTime,
                    duration = PanchangUtility.convertTimeToAmPm(entryTime) + " " + resources.getString(R.string.from) + " " + PanchangUtility.convertTimeToAmPm(exitTime)
            )

            arrayList.add(doGhatiBean)
        }
        return arrayList
    }

    fun getDayDoGhatiData(resources: Resources, cal: Calendar, place: Place): ArrayList<DoGhatiBean> {
        // val place = com.bhakti_sangrahalay.util.Utility.getPlaceForPanchang()
        val jd = Masa.toJulian(cal[Calendar.YEAR], cal[Calendar.MONTH] + 1, cal[Calendar.DATE])
        val sunRise: Double = PanchangCalculation.getSunRiseTimeInDouble(cal,place)
        val arrayList: ArrayList<DoGhatiBean> = ArrayList<DoGhatiBean>()
        val planetName = resources.getStringArray(R.array.do_ghati_name_list)
        val planetNameMeaning = resources.getStringArray(R.array.do_ghati_name_list_Muhurat)
        val planetNameMeaningForCurrentHora = resources.getStringArray(R.array.do_ghati_muhurut_meaning)
        val doGhatiSecondMeaning = resources.getStringArray(R.array.do_ghati_name_list_meaning_second)
        val doGhatiSecondMeaningWikipedia = resources.getStringArray(R.array.do_ghati_name_list_meaning)
        val doGhatiMuhurat = resources.getStringArray(R.array.do_ghati_huhurat)
        var entryTime: String
        var exitTime: String
        for (i in 0..14) {
            var plaName = ""
            when {
                i <= 2 -> {
                    plaName = planetName[0]
                }
                i <= 5 -> {
                    plaName = planetName[1]
                }
                i <= 8 -> {
                    plaName = planetName[2]
                }
                i <= 11 -> {
                    plaName = planetName[3]
                }
                i <= 14 -> {
                    plaName = planetName[4]
                }
            }
            entryTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getDayDivisons(jd, place, sunRise, 15)[i])
            exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getDayDivisons(jd, place, sunRise, 15)[i + 1])
            val doGhatiBean = DoGhatiBean(planetName = plaName,
                    planetMeaning = planetNameMeaning[i],
                    doGhatiSecondMeaning = doGhatiSecondMeaning[i],
                    doGhatiSecondMeaningWikipedia = doGhatiSecondMeaningWikipedia[i],
                    planetCurrentHoraMeaning = planetNameMeaningForCurrentHora[i],
                    doGhatiMuhurat = doGhatiMuhurat[i],
                    enterTime = entryTime,
                    exitTime = exitTime,
                    duration = PanchangUtility.convertTimeToAmPm(entryTime) + " " + resources.getString(R.string.from) + " " + PanchangUtility.convertTimeToAmPm(exitTime)
            )

            arrayList.add(doGhatiBean)
        }
        return arrayList
    }

    fun getNightDoGhatiData(resources: Resources, cal: Calendar, place: Place): ArrayList<DoGhatiBean> {
        //val place = com.bhakti_sangrahalay.util.Utility.getPlaceForPanchang()
        val jd = Masa.toJulian(cal[Calendar.YEAR], cal[Calendar.MONTH] + 1, cal[Calendar.DATE])
        val sunSet: Double = PanchangCalculation.getSunSetTimeInDouble(cal,place)
        val arrayList: ArrayList<DoGhatiBean> = ArrayList<DoGhatiBean>()
        val planetName = resources.getStringArray(R.array.do_ghati_name_list)
        val planetNameMeaning = resources.getStringArray(R.array.do_ghati_name_list_Muhurat)
        val planetNameMeaningForCurrentHora = resources.getStringArray(R.array.do_ghati_muhurut_meaning)
        val doGhatiSecondMeaning = resources.getStringArray(R.array.do_ghati_name_list_meaning_second)
        val doGhatiSecondMeaningWikipedia = resources.getStringArray(R.array.do_ghati_name_list_meaning)
        val doGhatiMuhurat = resources.getStringArray(R.array.do_ghati_huhurat)
        var entryTime: String
        var exitTime: String
        for (i in 0..14) {
            var plaName = ""
            when {
                i <= 2 -> {
                    plaName = planetName[5]
                }
                i <= 6 -> {
                    plaName = planetName[6]
                }
                i <= 7 -> {
                    plaName = planetName[7]
                }
                i <= 12 -> {
                    plaName = planetName[8]
                }
                i <= 14 -> {
                    plaName = planetName[9]
                }
            }

            entryTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getNightDivisons(jd, place, sunSet, 15)[i])
            exitTime = PanchangUtility.FormatDMSIn2DigitStringWithSignForhora(Muhurta.getNightDivisons(jd, place, sunSet, 15)[i + 1])
            val doGhatiBean = DoGhatiBean(planetName = plaName,
                    planetMeaning = planetNameMeaning[i],
                    doGhatiSecondMeaning = doGhatiSecondMeaning[i],
                    doGhatiSecondMeaningWikipedia = doGhatiSecondMeaningWikipedia[i],
                    planetCurrentHoraMeaning = planetNameMeaningForCurrentHora[i],
                    doGhatiMuhurat = doGhatiMuhurat[i],
                    enterTime = entryTime,
                    exitTime = exitTime,
                    duration = PanchangUtility.convertTimeToAmPm(entryTime) + " " + resources.getString(R.string.from) + " " + PanchangUtility.convertTimeToAmPm(exitTime)
            )

            arrayList.add(doGhatiBean)
        }
        return arrayList
    }

}