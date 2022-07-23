package com.bhakti_sangrahalay.panchang.calculations

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.Log
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.model.PanchangModel
import com.bhakti_sangrahalay.panchang.util.IConstants
import com.bhakti_sangrahalay.panchang.util.Language
import com.bhakti_sangrahalay.panchang.util.PanchangUtil
import com.bhakti_sangrahalay.util.Utility
import com.indoriya.panchang.CMoon
import com.indoriya.panchang.Masa
import com.indoriya.panchang.Muhurta
import com.indoriya.panchang.Place
import java.util.*

object PanchangCalculation {
    private var place: Place
    private var date: Date
    private var masa: Masa

    private var cMoon: CMoon
    private var panchangUtil: PanchangUtil
    private var panchangModel: PanchangModel
    private var languageCode: Language
    private var constants: IConstants
    private const val fetchCompleteData = 1
    private const val fetchOnlyValue = 2
    private const val fetchOnlyTime = 3
    private var datePanJd = 0

    init {
        val calendar = Utility.getCalenderObj()
        date = calendar.time
        datePanJd = Masa.toJulian(calendar[Calendar.YEAR], calendar[Calendar.MONTH] + 1, calendar[Calendar.DAY_OF_MONTH]).toInt()

        masa = Masa()
        cMoon = CMoon()
        panchangUtil = PanchangUtil()
        panchangModel = PanchangModel()
        languageCode = Language.getLanguage("hi")
        constants = panchangUtil.iConstantsObj
        place = Utility.getPlaceForPanchang()
        initPanchangCalculation(calendar, place)
    }

    private fun initPanchangCalculation(calendar: Calendar, place: Place) {
        //getPanchangMasa()
        //getPanchangMuhurat()
        getSunRiseTime(calendar, place)
        getSunSetTime(calendar, place)
        getMoonRiseSetTime(calendar, place)
    }

    fun getPanchangModel(date: Date, place: Place): PanchangModel {
        PanchangCalculation.place = place
        PanchangCalculation.date = date
        val calendarPan = Calendar.getInstance()
        calendarPan.time = date
        datePanJd = Masa.toJulian(calendarPan[Calendar.YEAR], calendarPan[Calendar.MONTH] + 1, calendarPan[Calendar.DAY_OF_MONTH]).toInt()
        return panchangModel
    }

    fun getPanchangModel(): PanchangModel {
        return panchangModel
    }

    private fun getJulianDate(calendar: Calendar): Double {
        return Masa.toJulian(calendar[Calendar.YEAR], calendar[Calendar.MONTH] + 1, calendar[Calendar.DAY_OF_MONTH])
    }

    fun getSunRiseTimeInDouble(calendar: Calendar, place: Place): Double {
        return Masa.getSunRise(getJulianDate(calendar), place)
    }

    fun getSunRiseTime(calendar: Calendar, place: Place): String {
        return panchangUtil.dms(Masa.getSunRise(getJulianDate(calendar), place))
    }

    fun getSunSetTimeInDouble(calendar: Calendar, place: Place): Double {
        return Masa.getSunSet(getJulianDate(calendar), place)
    }

    fun getSunSetTime(calendar: Calendar, place: Place): String {
        return panchangUtil.dms(Masa.getSunSet(getJulianDate(calendar), place))
    }

    private fun getNextDaySunRise(calendar: Calendar, place: Place): Double {
        return Masa.getSunRise(getJulianDate(calendar).toInt() + 1.toDouble(), place)
    }

    fun getMoonRiseSetTime(calendar: Calendar, place: Place): ArrayList<String> {
        val moonRiseSetArr = ArrayList<String>()
        try {
            val moonRiseSet = cMoon.getMoonRiseSetTime(getJulianDate(calendar), place)
            moonRiseSetArr.add(if (moonRiseSet[0] != 0.0) getHoverString(moonRiseSet[0]) else constants.getExString(1))
            moonRiseSetArr.add(if (moonRiseSet[2] != 0.0) getHoverString(moonRiseSet[2]) else constants.getExString(1))
        } catch (e: Exception) {
            Log.i("", e.message + "")
        }
        return moonRiseSetArr
    }

    fun getTithi(calendar: Calendar, place: Place): ArrayList<String> {
        val tithiArr = ArrayList<String>()
        val tithiData: DoubleArray = masa.tithi(getJulianDate(calendar), place)
        tithiArr.add(getString(tithiData, "Tith", getNextDaySunRise(calendar, place), fetchOnlyValue))
        tithiArr.add(getString(tithiData, "Tith", getNextDaySunRise(calendar, place), fetchOnlyTime))
        return tithiArr
    }

    fun getTithiInt(calendar: Calendar, place: Place): Int {

        val tithiData: DoubleArray = masa.tithi(getJulianDate(calendar), place)
        return tithiData[0].toInt()
    }

    fun getNakshtra(calendar: Calendar, place: Place): ArrayList<String> {
        val nakshtraArr = ArrayList<String>()
        val nakshatraData: DoubleArray = masa.nakshatra(getJulianDate(calendar), place)
        // panchangModel.nakshatra = getString(nakshatraData, "Naksh", getNextDaySunRise(), fetchCompleteData)
        nakshtraArr.add(getString(nakshatraData, "Naksh", getNextDaySunRise(calendar, place), fetchOnlyValue))
        nakshtraArr.add(getString(nakshatraData, "Naksh", getNextDaySunRise(calendar, place), fetchOnlyTime))
        return nakshtraArr
    }

    fun getYog(calendar: Calendar, place: Place): ArrayList<String> {
        val yogArr = ArrayList<String>()
        val yogaData: DoubleArray = masa.yoga(getJulianDate(calendar), place)
        //panchangModel.yoga = getString(yogaData, "Yog", getNextDaySunRise(), fetchCompleteData)
        yogArr.add(getString(yogaData, "Yog", getNextDaySunRise(calendar, place), fetchOnlyValue))
        yogArr.add(getString(yogaData, "Yog", getNextDaySunRise(calendar, place), fetchOnlyTime))
        return yogArr
    }

    fun getKaran(calendar: Calendar, place: Place): ArrayList<String> {
        val karanArr = ArrayList<String>()
        val karanaData: DoubleArray = masa.karana(getJulianDate(calendar), place)
        // panchangModel.karana = getString(karanaData, "Karan", getNextDaySunRise(), fetchCompleteData)
        karanArr.add(getString(karanaData, "Karan", getNextDaySunRise(calendar, place), fetchOnlyValue))
        karanArr.add(getString(karanaData, "Karan", getNextDaySunRise(calendar, place), fetchOnlyTime))
        return karanArr
    }

    fun getPaksh(calendar: Calendar, place: Place): String {
        return constants.getPakshas(masa.getPaksha(getJulianDate(calendar), place))
    }

    fun getVar(calendar: Calendar, place: Place): String {
        return constants.getVaras(Masa.vaara(getJulianDate(calendar)))
    }

    fun getRitu(calendar: Calendar, place: Place): String {
        return constants.getRitus(masa.ritu_drik(getJulianDate(calendar), place))
    }

    fun getMoonSign(calendar: Calendar, place: Place): ArrayList<String> {
        val moonSignArr = ArrayList<String>()
        val moonSign: DoubleArray = masa.moonsign(getJulianDate(calendar), place)
        val sunRiseVal = Masa.getSunRise(getJulianDate(calendar), place)
        if (moonSign[1] > sunRiseVal + 24.00000) {
            val nameMoonSign: String = getDay(moonSign[0], "MoonSign")
            //panchangModel.moonSign = nameMoonSign
            moonSignArr.add(nameMoonSign)
        } else {
            //panchangModel.moonSign = getString(MoonSign, "MoonSign", nextdaysunrise, fetchCompleteData)
            moonSignArr.add(getString(moonSign, "MoonSign", getNextDaySunRise(calendar, place), fetchOnlyValue))
            //moonSignArr.add(getString(moonSign, "MoonSign", getNextDaySunRise(), fetchOnlyTime))
        }
        return moonSignArr
    }

    private fun getMasaPurnimant(calendar: Calendar, place: Place): IntArray {
        return masa.masaPurnimanta(getJulianDate(calendar), place)
    }

    private fun getKaliShakaVikramSamvat(calendar: Calendar, place: Place): IntArray {
        val masaMoon = getMasaPurnimant(calendar, place)
        return masa.elapsed_year(getJulianDate(calendar), place, masaMoon[0])
    }

    fun getAmantaMonth(calendar: Calendar, place: Place): String {
        val masaMoon = getMasaPurnimant(calendar, place)
        return if (masaMoon[1] == 1) {
            constants.getMasas(masaMoon[0] - 1).toString() + " " + constants.getExString(3)
        } else {
            constants.getMasas(masaMoon[0] - 1)
        }
    }

    fun getPurnimantMonth(calendar: Calendar, place: Place): String {
        val masaMoon = getMasaPurnimant(calendar, place)
        return if (masaMoon[1] == 1) {
            constants.getMasas(masaMoon[2] - 1).toString() + " " + constants.getExString(3)
        } else {
            constants.getMasas(masaMoon[2] - 1)
        }
    }

    fun getShakaSamvatYear(calendar: Calendar, place: Place): String {
        return getKaliShakaVikramSamvat(calendar, place)[1].toString()
    }

    fun getVikramSamvatYear(calendar: Calendar, place: Place): String {
        return getKaliShakaVikramSamvat(calendar, place)[2].toString()
    }

    fun getKaliSamvatYear(calendar: Calendar, place: Place): String {
        return getKaliShakaVikramSamvat(calendar, place)[3].toString()
    }

    fun getDayDuration(calendar: Calendar, place: Place): String {
        return panchangUtil.dms(Muhurta.dayDuration(getJulianDate(calendar), place))
    }


    /* private fun getPanchangMasa() {
         val sunriseval = Masa.getSunRise(getJulianDate(calendar), place)
         val sunsetval = Masa.getSunSet(getJulianDate(calendar), place)
         val nextdaysunrise = Masa.getSunRise(datePanJd + 1.toDouble(), place)
         val masa_moon: IntArray = masa.masaPurnimanta(getJulianDate(calendar), place)
         val kalishakavikramsamvat: IntArray = masa.elapsed_year(getJulianDate(calendar), place, masa_moon[0])
         val moonrisesetval: DoubleArray = cMoon.getMoonRiseSetTime(getJulianDate(calendar), place)
         val MoonSign: DoubleArray = masa.moonsign(getJulianDate(calendar), place)
         panchangModel.sunRiseDouble = sunriseval
         panchangModel.sunSetDouble = sunsetval
         panchangModel.sunRise = panchangUtil.dms(sunriseval)
         panchangModel.sunSet = panchangUtil.dms(sunsetval)
         panchangModel.moonRise = if (moonrisesetval[0] != 0.0) GetHoverString(moonrisesetval[0]) else constants.getExString(1)
         panchangModel.moonSet = if (moonrisesetval[2] != 0.0) GetHoverString(moonrisesetval[2]) else constants.getExString(2)
         panchangModel.ritu = constants.getRitus(masa.ritu_drik(getJulianDate(calendar), place))
         panchangModel.shakaSamvatName = constants.getSamvats(masa.samvatsara(getJulianDate(calendar), place, masa_moon[0], false))
         if (masa_moon[1] == 1) {
             panchangModel.monthAmanta = constants.getMasas(masa_moon[0] - 1).toString() + " " + constants.getExString(3)
             panchangModel.monthPurnimanta = constants.getMasas(masa_moon[2] - 1).toString() + " " + constants.getExString(3)
         } else {
             panchangModel.monthAmanta = constants.getMasas(masa_moon[0] - 1)
             panchangModel.monthPurnimanta = constants.getMasas(masa_moon[2] - 1)
         }
         panchangModel.pakshaName = constants.getPakshas(masa.getPaksha(getJulianDate(calendar), place))
         panchangModel.vaara = constants.getVaras(Masa.vaara(getJulianDate(calendar)))
         panchangModel.kaliSamvat = kalishakavikramsamvat[3].toString() + ""
         panchangModel.shakaSamvatYear = kalishakavikramsamvat[1].toString() + ""
         panchangModel.vikramSamvat = kalishakavikramsamvat[2].toString() + ""

         *//*
         * Getting MoonSign
         * *//*if (MoonSign[1] > sunriseval + 24.00000) {
            val nameMoonSign: String = getDay(MoonSign[0], "MoonSign")
            panchangModel.moonSign = nameMoonSign
            panchangModel.moonSignValue = nameMoonSign
        } else {
            panchangModel.moonSign = getString(MoonSign, "MoonSign", nextdaysunrise, fetchCompleteData)
            panchangModel.moonSignValue = getString(MoonSign, "MoonSign", nextdaysunrise, fetchOnlyValue)
            panchangModel.moonSignTime = getString(MoonSign, "MoonSign", nextdaysunrise, fetchOnlyTime)
        }

        *//*
         * Getting Tithi
         * *//*
        val tithiData: DoubleArray = masa.tithi(getJulianDate(calendar), place)
        panchangModel.tithiInt = tithiData
        panchangModel.tithi = getString(tithiData, "Tith", nextdaysunrise, fetchCompleteData)
        panchangModel.tithiValue = getString(tithiData, "Tith", nextdaysunrise, fetchOnlyValue)
        panchangModel.tithiTime = getString(tithiData, "Tith", nextdaysunrise, fetchOnlyTime)
        *//*
         * Getting Nakshatra
         * *//*
        val nakshatraData: DoubleArray = masa.nakshatra(getJulianDate(calendar), place)
        panchangModel.nakshatra = getString(nakshatraData, "Naksh", nextdaysunrise, fetchCompleteData)
        panchangModel.nakshatraValue = getString(nakshatraData, "Naksh", nextdaysunrise, fetchOnlyValue)
        panchangModel.nakshatraTime = getString(nakshatraData, "Naksh", nextdaysunrise, fetchOnlyTime)
        *//*
         * Getting Karana
         * *//*
        val karanaData: DoubleArray = masa.karana(getJulianDate(calendar), place)
        panchangModel.karana = getString(karanaData, "Karan", nextdaysunrise, fetchCompleteData)
        panchangModel.karanaValue = getString(karanaData, "Karan", nextdaysunrise, fetchOnlyValue)
        panchangModel.karanaTime = getString(karanaData, "Karan", nextdaysunrise, fetchOnlyTime)
        *//*
         * Date : 25-Sep-2015
         * Getting Yoga
         * *//*
        val yogaData: DoubleArray = masa.yoga(getJulianDate(calendar), place)
        panchangModel.yoga = getString(yogaData, "Yog", nextdaysunrise, fetchCompleteData)
        panchangModel.yogaValue = getString(yogaData, "Yog", nextdaysunrise, fetchOnlyValue)
        panchangModel.yogaTime = getString(yogaData, "Yog", nextdaysunrise, fetchOnlyTime)
    }*/

    fun getAshubMuhuratList(calendar: Calendar, place: Place): ArrayList<ArrayList<String>> {
        val arrayList = ArrayList<ArrayList<String>>()
        arrayList.add(getDushtaMuhurtas(calendar, place))
        arrayList.add(getKantakaMrityu(calendar, place))
        arrayList.add(getYamaGhanta(calendar, place))
        arrayList.add(getRahuKaalVela(calendar, place))
        arrayList.add(getKulika(calendar, place))
        arrayList.add(getKalavelaArdhayaam(calendar, place))
        arrayList.add(getYamagandaVela(calendar, place))
        arrayList.add(getGulikaKaalVela(calendar, place))
        return arrayList
    }

    private fun getFifteenMuhurat(calendar: Calendar, place: Place): DoubleArray {
        return Muhurta.getFifteenMuhurtaForDay(getJulianDate(calendar), place)
    }

    private fun getKulikaKantakaKalavelaYama(calendar: Calendar, place: Place): IntArray {
        return Muhurta.getKulikaKantakaKalavelaYama(getJulianDate(calendar), place)
    }

    private fun getDushtaMuhurta(calendar: Calendar, place: Place): IntArray {
        return Muhurta.getDushtaMuhurta(getJulianDate(calendar), place)
    }

    private fun getEightDivisions(calendar: Calendar, place: Place): DoubleArray {
        return Muhurta.getDayDivisons(getJulianDate(calendar), place, Muhurta.getSunRise(getJulianDate(calendar), place), 8)
    }

    private fun getRahuYamaGulikaKaal(calendar: Calendar, place: Place): IntArray {

        return Muhurta.getRahuYamaGulikaKaal(getJulianDate(calendar), place)
    }


    fun getAbhijitMuhurat(calendar: Calendar, place: Place): ArrayList<String> {
        val abhijitMuhuratArr = ArrayList<String>()
        val fifteenMuhurtas = getFifteenMuhurat(calendar, place)
        if (Masa.vaara(getJulianDate(calendar)) != 3) // For Wednesday
        {
            //panchangModel.setAbhijit(GetFromToString(panchangUtil.dms(fifteenMuhurtas[8 - 1]), panchangUtil.dms(fifteenMuhurtas[8])))

            abhijitMuhuratArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[8 - 1])))
            abhijitMuhuratArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[8])))
        } else {
            //panchangModel.abhijit = constants.getExString(0)
            abhijitMuhuratArr.add(constants.getExString(0))
            abhijitMuhuratArr.add("")
        }
        return abhijitMuhuratArr
    }

    private fun getKulika(calendar: Calendar, place: Place): ArrayList<String> {
        val kulikaArr = ArrayList<String>()
        val fifteenMuhurtas = getFifteenMuhurat(calendar, place)
        val kulikaEtc = getKulikaKantakaKalavelaYama(calendar, place)
        // panchangModel.kantaka_Mrityu = GetFromToString(fromKantaka_Mrityu, toKantaka_Mrityu)
        kulikaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[0] - 1])))
        kulikaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[0]])))
        return kulikaArr
    }

    private fun getKantakaMrityu(calendar: Calendar, place: Place): ArrayList<String> {
        val kantakaMrityuArr = ArrayList<String>()
        val fifteenMuhurtas = getFifteenMuhurat(calendar, place)
        val kulikaEtc = getKulikaKantakaKalavelaYama(calendar, place)
        // panchangModel.kantaka_Mrityu = GetFromToString(fromKantaka_Mrityu, toKantaka_Mrityu)
        kantakaMrityuArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[1] - 1])))
        kantakaMrityuArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[1]])))
        return kantakaMrityuArr
    }

    private fun getKalavelaArdhayaam(calendar: Calendar, place: Place): ArrayList<String> {
        val kalavelaArdhayaamArr = ArrayList<String>()
        val fifteenMuhurtas = getFifteenMuhurat(calendar, place)
        val kulikaEtc = getKulikaKantakaKalavelaYama(calendar, place)
        //panchangModel.kalavela_Ardhayaam = GetFromToString(fromKalavela_Ardhayaam, toKalavela_Ardhayaam)
        kalavelaArdhayaamArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[2] - 1])))
        kalavelaArdhayaamArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[2]])))
        return kalavelaArdhayaamArr
    }

    private fun getYamaGhanta(calendar: Calendar, place: Place): ArrayList<String> {
        val yamaGhantaArr = ArrayList<String>()
        val fifteenMuhurtas = getFifteenMuhurat(calendar, place)
        val kulikaEtc = getKulikaKantakaKalavelaYama(calendar, place)
        yamaGhantaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[3] - 1])))
        yamaGhantaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[kulikaEtc[3]])))
        return yamaGhantaArr
    }

    private fun getDushtaMuhurtas(calendar: Calendar, place: Place): ArrayList<String> {
        val dushtaMuhurtasArr = ArrayList<String>()
        val dushtaMuhurtas = getDushtaMuhurta(calendar, place)
        val fifteenMuhurtas = getFifteenMuhurat(calendar, place)
        if (dushtaMuhurtas.size > 1) {
            val from1: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0] - 1])
            val to1: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0]])
            val from2: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[1] - 1])
            val to2: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[1]])
            //panchangModel.dushtaMuhurtas = GetFromToString(from1, to1) + ", " + GetFromToString(from2, to2)
            dushtaMuhurtasArr.add("${Utility.convertTimeToAmPm(from1)},\n${Utility.convertTimeToAmPm(from2)}")
            dushtaMuhurtasArr.add("${Utility.convertTimeToAmPm(to1)},\n${Utility.convertTimeToAmPm(to2)}")
        } else {
            val from1: String = Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0] - 1]))
            val to1: String = Utility.convertTimeToAmPm(panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0]]))
            panchangModel.dushtaMuhurtas = getFromToString(from1, to1)
            dushtaMuhurtasArr.add(from1)
            dushtaMuhurtasArr.add(to1)
        }
        return dushtaMuhurtasArr
    }

    fun getRahuKaalVela(calendar: Calendar, place: Place): ArrayList<String> {
        val rahuKaalVelaArr = ArrayList<String>()
        // panchangModel.rahuKaalVela = GetFromToString(fromRahuKaalVela, toRahuKaalVela)
        rahuKaalVelaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(getEightDivisions(calendar, place)[getRahuYamaGulikaKaal(calendar, place)[0] - 1])))
        rahuKaalVelaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(getEightDivisions(calendar, place)[getRahuYamaGulikaKaal(calendar, place)[0]])))
        return rahuKaalVelaArr
    }

    private fun getGulikaKaalVela(calendar: Calendar, place: Place): ArrayList<String> {
        val gulikaKaalVelaArr = ArrayList<String>()
        // panchangModel.gulikaKaalVela = GetFromToString(fromGulikaKaalVela, toGulikaKaalVela)
        gulikaKaalVelaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(getEightDivisions(calendar, place)[getRahuYamaGulikaKaal(calendar, place)[1] - 1])))
        gulikaKaalVelaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(getEightDivisions(calendar, place)[getRahuYamaGulikaKaal(calendar, place)[1]])))
        return gulikaKaalVelaArr
    }

    private fun getYamagandaVela(calendar: Calendar, place: Place): ArrayList<String> {
        val yamagandaVelaArr = ArrayList<String>()
        //panchangModel.yamagandaVela = GetFromToString(fromYamagandaVela, toYamagandaVela)
        yamagandaVelaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(getEightDivisions(calendar, place)[getRahuYamaGulikaKaal(calendar, place)[2] - 1])))
        yamagandaVelaArr.add(Utility.convertTimeToAmPm(panchangUtil.dms(getEightDivisions(calendar, place)[getRahuYamaGulikaKaal(calendar, place)[2]])))
        return yamagandaVelaArr
    }

/*
    private fun getPanchangMuhurat(calendar: Calendar, place: Place) {
        val fifteenMuhurtas = Muhurta.getFifteenMuhurtaForDay(getJulianDate(calendar), place)
        val kulikaetc = Muhurta.getKulikaKantakaKalavelaYama(getJulianDate(calendar), place)
        val dushtaMuhurtas = Muhurta.getDushtaMuhurta(getJulianDate(calendar), place)
        val eightDivisions = Muhurta.getDayDivisons(getJulianDate(calendar), place, Muhurta.getSunRise(getJulianDate(calendar), place), 8)
        val rahuetc = Muhurta.getRahuYamaGulikaKaal(getJulianDate(calendar), place)
        panchangModel.dayDuration = panchangUtil.dms(Muhurta.dayDuration(getJulianDate(calendar), place))
        if (Masa.vaara(getJulianDate(calendar)) != 3) // For Wednesday
        {
            panchangModel.abhijit = getFromToString(panchangUtil.dms(fifteenMuhurtas[8 - 1]), panchangUtil.dms(fifteenMuhurtas[8]))
            *//*
             * Getting Abhijit
             * *//*panchangModel.abhijitFrom = panchangUtil.dms(fifteenMuhurtas[8 - 1])
            panchangModel.abhijitTo = panchangUtil.dms(fifteenMuhurtas[8])
        } else {
            panchangModel.abhijit = constants.getExString(0)
            *//*
             * Getting Abhijit
             * *//*panchangModel.abhijitFrom = constants.getExString(0)
            panchangModel.abhijitTo = ""
        }
        *//*
         * Getting Kulika
         * *//*
        val fromKulika: String = panchangUtil.dms(fifteenMuhurtas[kulikaetc[0] - 1])
        val toKulika: String = panchangUtil.dms(fifteenMuhurtas[kulikaetc[0]])
        panchangModel.kulika = getFromToString(fromKulika, toKulika)
        panchangModel.kulikaFrom = fromKulika
        panchangModel.kulikaTo = toKulika

        *//*
         * Date : 25-Sep-2015
         * Getting Kantaka_Mrityu
         * *//*
        val fromKantakaMrityu: String = panchangUtil.dms(fifteenMuhurtas[kulikaetc[1] - 1])
        val toKantakaMrityu: String = panchangUtil.dms(fifteenMuhurtas[kulikaetc[1]])
        panchangModel.kantaka_Mrityu = getFromToString(fromKantakaMrityu, toKantakaMrityu)
        panchangModel.kantaka_MrityuFrom = fromKantakaMrityu
        panchangModel.kantaka_MrityuTo = toKantakaMrityu

        *//*
         * Date : 25-Sep-2015
         * Getting Kalavela_Ardhayaam
         * *//*
        val fromKalavelaArdhayaam: String = panchangUtil.dms(fifteenMuhurtas[kulikaetc[2] - 1])
        val toKalavelaArdhayaam: String = panchangUtil.dms(fifteenMuhurtas[kulikaetc[2]])
        panchangModel.kalavela_Ardhayaam = getFromToString(fromKalavelaArdhayaam, toKalavelaArdhayaam)
        panchangModel.kalavela_ArdhayaamFrom = fromKalavelaArdhayaam
        panchangModel.kalavela_ArdhayaamTo = toKalavelaArdhayaam

        *//*
         * Date : 25-Sep-2015
         * Getting Yamaghanta
         * *//*
        val fromYamaghanta: String = panchangUtil.dms(fifteenMuhurtas[kulikaetc[3] - 1])
        val toYamaghanta: String = panchangUtil.dms(fifteenMuhurtas[kulikaetc[3]])
        panchangModel.yamaghanta = getFromToString(fromYamaghanta, toYamaghanta)
        panchangModel.yamaghantaFrom = fromYamaghanta
        panchangModel.yamaghantaTo = toYamaghanta

        if (dushtaMuhurtas.size > 1) {
            *//*
             * Date : 25-Sep-2015
             * Getting DushtaMuhurtas
             * *//*
            val from1: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0] - 1])
            val to1: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0]])
            val from2: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[1] - 1])
            val to2: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[1]])
            panchangModel.dushtaMuhurtas = getFromToString(from1, to1) + ", " + getFromToString(from2, to2)
            panchangModel.dushtaMuhurtasFrom = "$from1,\n$from2"
            panchangModel.dushtaMuhurtasTo = "$to1,\n$to2"
        } else {

            *//*
             * Date : 25-Sep-2015
             * Getting DushtaMuhurtas
             * *//*
            val from1: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0] - 1])
            val to1: String = panchangUtil.dms(fifteenMuhurtas[dushtaMuhurtas[0]])
            panchangModel.dushtaMuhurtas = getFromToString(from1, to1)
            panchangModel.dushtaMuhurtasFrom = from1
            panchangModel.dushtaMuhurtasTo = to1
        }
        *//*
         * Date : 25-Sep-2015
         * Getting RahuKaalVela
         * *//*
        val fromRahuKaalVela: String = panchangUtil.dms(eightDivisions[rahuetc[0] - 1])
        val toRahuKaalVela: String = panchangUtil.dms(eightDivisions[rahuetc[0]])
        panchangModel.rahuKaalVela = getFromToString(fromRahuKaalVela, toRahuKaalVela)
        panchangModel.rahuKaalVelaFrom = fromRahuKaalVela
        panchangModel.rahuKaalVelaTo = toRahuKaalVela

        *//*
         * Date : 25-Sep-2015
         * Getting GulikaKaalVela
         * *//*
        val fromGulikaKaalVela: String = panchangUtil.dms(eightDivisions[rahuetc[1] - 1])
        val toGulikaKaalVela: String = panchangUtil.dms(eightDivisions[rahuetc[1]])
        panchangModel.gulikaKaalVela = getFromToString(fromGulikaKaalVela, toGulikaKaalVela)
        panchangModel.gulikaKaalVelaFrom = fromGulikaKaalVela
        panchangModel.gulikaKaalVelaTo = toGulikaKaalVela

        *//*
         * Date : 25-Sep-2015
         * Getting YamagandaVela
         * *//*
        val fromYamagandaVela: String = panchangUtil.dms(eightDivisions[rahuetc[2] - 1])
        val toYamagandaVela: String = panchangUtil.dms(eightDivisions[rahuetc[2]])
        panchangModel.yamagandaVela = getFromToString(fromYamagandaVela, toYamagandaVela)
        panchangModel.yamagandaVelaFrom = fromYamagandaVela
        panchangModel.yamagandaVelaTo = toYamagandaVela


        panchangModel.dishaShoola = constants.getDishas(Muhurta.getDishaShoola(getJulianDate(calendar)) - 1)
        panchangModel.taraBala = getMergeString(Muhurta.getTaraBaliNakshatra(masa.nakshatra(getJulianDate(calendar), place)[0].toInt()), constants.taraBala)
        panchangModel.chandraBala = getMergeString(Muhurta.getChandraBaliRasi(masa.moonsign(getJulianDate(calendar), place)[0].toInt()), constants.chandraBala)
    }*/

    fun getString(dataArray: DoubleArray, stringTitle: String, nextDaySunRise: Double, id: Int): String {
        var result = ""
        for (i in dataArray.indices step 2) {
            when (languageCode) {
                Language.hi -> {
                    result = when (id) {
                        fetchCompleteData -> {
                            ((if (i == 0) "" else "$result, ")
                                    + getDay(dataArray[i], stringTitle) + " - "
                                    + getHoverStringFullNight(dataArray[i + 1], nextDaySunRise)
                                    + " तक")
                        }
                        fetchOnlyValue -> {
                            ((if (i == 0) "" else "$result, ")
                                    + getDay(dataArray[i], stringTitle))
                        }
                        else -> {
                            ((if (i == 0) "" else "$result, ")
                                    + getHoverStringFullNight(dataArray[i + 1], nextDaySunRise))
                        }
                    }

                }
                else -> {
                    result = when (id) {
                        fetchCompleteData -> {
                            ((if (i == 0) "" else "$result,\n")
                                    + getDay(dataArray[i], stringTitle) + " upto "
                                    + getHoverStringFullNight(dataArray[i + 1], nextDaySunRise))
                        }
                        fetchOnlyValue -> {
                            (if (i == 0) "" else "$result,\n") + getDay(dataArray[i], stringTitle)
                        }
                        else -> {
                            (if (i == 0) "" else "$result,\n") + getHoverStringFullNight(dataArray[i + 1], nextDaySunRise)
                        }
                    }
                }
            }
        }
        return Utility.convertTimeToAmPm(result)
    }

    private fun getDay(dayValue: Double, title: String): String {
        val day = dayValue.toInt()
        var result: String = day.toString() + ""
        when (title) {
            "MoonSign" -> result = constants.getMoonSign(day)
            "Tith" -> result = constants.getTithi(day)
            "Naksh" -> result = constants.getNakshatra(day)
            "Yog" -> result = constants.getYoga(day)
            "Karan" -> result = constants.getKaranas(day)
        }
        return result
    }

    private fun getHoverString(actualTime: Double): String {
        val result: String
        if (actualTime < 24.0) {
            result = panchangUtil.dms(actualTime)
        } else {
            val nextDate = panchangUtil.getAddDays(date, 1)
            val calnPan = Calendar.getInstance()
            calnPan.time = nextDate

            result = when (languageCode) {
                Language.hi -> panchangUtil.dms(actualTime)
                else -> panchangUtil.dms(actualTime)
            }
        }
        return result
    }

    private fun getHoverStringFullNight(actualTime: Double, nextDaySunRise: Double): String {
        val result: String
        when {
            actualTime < 24.0 -> {
                result = panchangUtil.dms(actualTime)
            }
            actualTime > nextDaySunRise + 24.00 -> {
                result = when (languageCode) {
                    Language.hi -> "पूर्ण रात्रि"
                    else -> "Full Night"
                }
            }
            else -> {
                val nextdate: Date = panchangUtil.getAddDays(date, 1)
                val calnPan = Calendar.getInstance()
                calnPan.time = nextdate
                result = when (languageCode) {
                    Language.hi -> panchangUtil.dms(actualTime)
                    else -> panchangUtil.dms(actualTime)
                }
            }
        }
        return result
    }

    private fun getFromToString(from: String, to: String): String? {
        return when (languageCode) {
            Language.hi -> "$from से  $to तक"
            else -> "From $from To $to"
        }
    }

    private fun getMergeString(data: IntArray, list: Array<String>): String {
        var str = ""
        for (item in data) {
            str += (if (str === "") "" else ", ") + list[item]
        }
        return str
    }

    fun getMoonPhaseImage(resources: Resources, index: Int): Drawable? {
        val hashMap = HashMap<Int, Drawable>()

        hashMap[1] = resources.getDrawable(R.drawable.moon_light_1)
        hashMap[2] = resources.getDrawable(R.drawable.moon_light_2)
        hashMap[3] = resources.getDrawable(R.drawable.moon_light_3)
        hashMap[4] = resources.getDrawable(R.drawable.moon_light_4)
        hashMap[5] = resources.getDrawable(R.drawable.moon_light_5)
        hashMap[6] = resources.getDrawable(R.drawable.moon_light_6)
        hashMap[7] = resources.getDrawable(R.drawable.moon_light_7)
        hashMap[8] = resources.getDrawable(R.drawable.moon_light_8)
        hashMap[9] = resources.getDrawable(R.drawable.moon_light_9)
        hashMap[10] = resources.getDrawable(R.drawable.moon_light_10)
        hashMap[11] = resources.getDrawable(R.drawable.moon_light_11)
        hashMap[12] = resources.getDrawable(R.drawable.moon_light_12)
        hashMap[13] = resources.getDrawable(R.drawable.moon_light_13)
        hashMap[14] = resources.getDrawable(R.drawable.moon_light_14)
        hashMap[15] = resources.getDrawable(R.drawable.ic_moon_light)
        hashMap[16] = resources.getDrawable(R.drawable.moon_light_dark_1)
        hashMap[17] = resources.getDrawable(R.drawable.moon_light_dark_2)
        hashMap[18] = resources.getDrawable(R.drawable.moon_light_dark_3)
        hashMap[19] = resources.getDrawable(R.drawable.moon_light_dark_4)
        hashMap[20] = resources.getDrawable(R.drawable.moon_light_dark_5)
        hashMap[21] = resources.getDrawable(R.drawable.moon_light_dark_6)
        hashMap[22] = resources.getDrawable(R.drawable.moon_light_dark_7)
        hashMap[23] = resources.getDrawable(R.drawable.moon_light_dark_8)
        hashMap[24] = resources.getDrawable(R.drawable.moon_light_dark_9)
        hashMap[25] = resources.getDrawable(R.drawable.moon_light_dark_10)
        hashMap[26] = resources.getDrawable(R.drawable.moon_light_dark_11)
        hashMap[27] = resources.getDrawable(R.drawable.moon_light_dark_12)
        hashMap[28] = resources.getDrawable(R.drawable.moon_light_dark_13)
        hashMap[29] = resources.getDrawable(R.drawable.moon_light_dark_14)
        hashMap[30] = resources.getDrawable(R.drawable.ic_moon_dark)
        return hashMap[index]
    }


    fun getBhadraData(calendar: Calendar, place: Place): ArrayList<String> {
        val list: ArrayList<String> = ArrayList<String>()
        val bhadraTime = masa.bhadraStartEndTime(getJulianDate(calendar), place)
        list.add(Utility.convertTimeToAmPm(panchangUtil.dms(bhadraTime[0])))
        list.add(Utility.convertTimeToAmPm(panchangUtil.dms(bhadraTime[1])))
        list.add(bhadraTime[2].toString())
        return list
    }

    fun isVishtiKaran(calendar: Calendar, place: Place): Boolean {
        var boolVal = false
        var karan = masa.karana(getJulianDate(calendar), place)
        if (getString(karan, "Karan", getNextDaySunRise(calendar, place), fetchOnlyValue).contains("विष्टि")) {
            boolVal = true
        }
        return boolVal
    }


}