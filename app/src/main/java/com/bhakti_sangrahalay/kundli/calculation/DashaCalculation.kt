package com.bhakti_sangrahalay.kundli.calculation

import com.bhakti_sangrahalay.kundli.model.DasaBean
import com.bhakti_sangrahalay.model.KundliBean

object DashaCalculation {
    lateinit var arrayList: ArrayList<KundliBean>
    private val yearOfVimPlanets = intArrayOf(7, 20, 6, 10, 7, 18, 16, 19, 17)
    private var nakshatra = 0
    fun setData(arrayList: ArrayList<KundliBean>) {
        this.arrayList = arrayList
    }

    private fun getPlanetDegreeArray(): DoubleArray {
        val planetDegree: Array<String> = arrayList[0].planetDegree.split(",").toTypedArray()
        val degree = DoubleArray(13)
        var str = ""
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
            str = str + degree[i] + ", "
        }
        return degree
    }

    private fun calculateBalance(moon: Double): Double {
        val n0: Double
        val balance: Double //double
        val q: Int
        var d0: Double = moon
        d0 = 9.0 * fract(d0 / 120)
        n0 = fract(d0)
        q = d0.toInt()
        balance = (1 - n0) * yearOfVimPlanets[q]
        return balance
    }

    private fun fract(doubleValue: Double): Double {
        return doubleValue - doubleValue.toInt()
    }

    private fun getNakshatra(moon: Double): Int {
        var nak = (moon * 0.075).toInt()
        if (nak >= 18) nak -= 18
        if (nak >= 9) nak -= 9
        return nak
    }

    fun getVimshttariMahaDasa(): Array<DasaBean> {
        var vimshttariMahaDasa = Array(9) { DasaBean() }
        try {
            val lastEndDate = 2020.8963470319634
            nakshatra = getNakshatra(getPlanetDegreeArray()[2])
            vimshttariMahaDasa = getMahaDasa(lastEndDate)
        } catch (e: Exception) {
        }
        return vimshttariMahaDasa
    }

    private fun getMahaDasa(lastEndDate: Double): Array<DasaBean> {

        val dasaFirstLevel = Array(9) { DasaBean() }
        var index = 0
        var plaNo = getNakshatra(getPlanetDegreeArray()[2])
        val balance = calculateBalance(getPlanetDegreeArray()[2])
        var endDate = lastEndDate + balance
        do {
            dasaFirstLevel[index] = DasaBean()
            dasaFirstLevel[index].planetNo = plaNo
            dasaFirstLevel[index].dasaTime = endDate
            plaNo++
            if (plaNo >= 9) plaNo -= 9
            index++
            endDate += yearOfVimPlanets[plaNo]
        } while (index < 9)
        return dasaFirstLevel
    }

    fun getAntaraDasa(pos: Int, preDasaArray: Array<DasaBean>): Array<DasaBean> {
        var index = 0
        val antaraDasaArray = Array(9) { DasaBean() }
        var periodSpan: Double
        var lastEndDate: Double
        if (pos == 0) {
            periodSpan = yearOfVimPlanets[preDasaArray[0].planetNo].toDouble()
            lastEndDate = preDasaArray[0].dasaTime - yearOfVimPlanets[preDasaArray[0].planetNo]
        } else {
            periodSpan = preDasaArray[pos].dasaTime - preDasaArray[pos - 1].dasaTime
            lastEndDate = preDasaArray[pos - 1].dasaTime
        }
        var plaNo = preDasaArray[pos].planetNo
        val total = 120 * yearOfVimPlanets[plaNo] / 120
        if (periodSpan < 0) {
            periodSpan += total.toDouble()
            lastEndDate -= total.toDouble()
        }

        do {
            lastEndDate += periodSpan * yearOfVimPlanets[plaNo] / 120
            antaraDasaArray[index] = DasaBean()
            antaraDasaArray[index].planetNo = plaNo
            antaraDasaArray[index].dasaTime = lastEndDate
            plaNo++
            if (plaNo >= 9) plaNo -= 9
            index++
        } while (index < 9)
        return antaraDasaArray
    }

    fun getVimPratyantraDasa(
        pos: Int,
        objArrPrDasa: Array<DasaBean>,
        _lastdashatime: Double
    ): Array<DasaBean> {
        var index = 0
        var plaNo: Int
        val pratyantraDasaArray = Array(9) { DasaBean() }
        val tempArrPrDasa: Array<DasaBean>
        var periodSpan: Double
        var lastEndDate: Double
        try {
            tempArrPrDasa = objArrPrDasa
            if (pos == 0) {
                periodSpan = tempArrPrDasa[0].dasaTime - _lastdashatime
                lastEndDate = _lastdashatime
            } else {
                periodSpan = tempArrPrDasa[pos].dasaTime - tempArrPrDasa[pos - 1].dasaTime
                lastEndDate = tempArrPrDasa[pos - 1].dasaTime
            }
            plaNo = tempArrPrDasa[pos].planetNo
            val total = 120 * yearOfVimPlanets[plaNo] / 120
            if (periodSpan < 0) {
                periodSpan += total.toDouble()
                lastEndDate -= total.toDouble()
            }
            do {
                lastEndDate += periodSpan * yearOfVimPlanets[plaNo] / 120
                pratyantraDasaArray[index] = DasaBean()
                pratyantraDasaArray[index].planetNo = (plaNo)
                pratyantraDasaArray[index].dasaTime = lastEndDate
                plaNo++
                if (plaNo >= 9) plaNo -= 9
                index++
            } while (index < 9)
        } catch (e: java.lang.Exception) {
            throw e
        }
        return pratyantraDasaArray
    }

}