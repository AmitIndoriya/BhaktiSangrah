package com.bhakti_sangrahalay.kundli.calculation

import android.content.Context
import android.util.Log
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.inteface.CalculationInterface
import com.bhakti_sangrahalay.kundli.model.KpRulingPlanetBean
import com.bhakti_sangrahalay.model.KundliBean

object KpRulingPlanetCalculation : CalculationInterface by KundliCalculationBaseObject {
    var y1 = intArrayOf(7, 20, 6, 10, 7, 18, 16, 19, 17)
    lateinit var arrayList: ArrayList<KundliBean>
    private lateinit var planetDegree: DoubleArray
    private lateinit var kpCuspDegree: DoubleArray


    fun setData(arrayList: ArrayList<KundliBean>) {
        KpRulingPlanetCalculation.arrayList = arrayList
        planetDegree = getKPPlanetDegreeArray()
        kpCuspDegree = getKpDegreeArray()
    }

    private fun getPlanetDegreeArray(): DoubleArray {
        val planetDegree: Array<String> = arrayList[0].planetDegree.split(",").toTypedArray()
        val degree = DoubleArray(13)
        var str = ""
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
            str = str + degree[i] + ", "
        }
        // Log.i("KpDegreeArray", str)
        return degree
    }

    private fun getKpDegreeArray(): DoubleArray {
        val planetDegree = arrayList[0].kpCusp.split(",".toRegex()).toTypedArray()
        val degree = DoubleArray(13)
        var str = ""
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
            str = str + degree[i] + ", "
        }
        Log.i("KpDegreeArray2", str)
        return degree
    }

    private fun getKPPlanetDegreeArray(): DoubleArray {
        val planetDegree = getPlanetDegreeArray()
        val degree = DoubleArray(13)
        var tempCalculation: Double
        val ayanDiff = arrayList[0].ayan.toDouble() - arrayList[0].kpayan.toDouble()
        var str = ""
        for (i in 1 until planetDegree.size) {
            tempCalculation = planetDegree[i] + ayanDiff
            if (tempCalculation < 0) {
                tempCalculation += 360.00
            } else if (tempCalculation >= 360) {
                tempCalculation -= 360.00
            }
            degree[i] = tempCalculation
            str = str + degree[i] + ", "
        }
        degree[12] = planetDegree[0]
        Log.i("KpDegreeArray1", str + "," + degree[12])
        return degree
    }

    private fun getRasiNumber(_deg: Double): Int {
        return (_deg / 30).toInt()
    }

    private fun getNakshatraNumber(_deg: Double): Int {
        return (_deg * 0.075).toInt()
    }

    private fun getSubLordNumber(deg: Double): Int {
        var d = deg
        var i = 0
        var a: Int = (d / 120.0).toInt()
        d -= a * 120.0
        a = (d * 3.0 / 40.0).toInt()
        d -= a * 40.0 / 3.0
        d *= 9.0
        var b = 0
        while (b < 9) {
            i = a + b
            if (i >= 9) i -= 9
            d -= if (y1[i] <= d) y1[i] else break
            b++
        }
        b = i
        return b
    }

    fun getRulingPlanetData(context: Context): ArrayList<KpRulingPlanetBean> {
        //_OutKpRulingPlanets.setBirthDayLord(obj.getDayLord())
        val rulingPlanetList = ArrayList<KpRulingPlanetBean>()
        val rashiNameList = context.resources.getStringArray(R.array.rasi_lord_full_name_list)
        val naksLordNameList =
            context.resources.getStringArray(R.array.nakshatra_lord_full_name_list)
        val labelList = context.resources.getStringArray(R.array.kp_ruling_planets_label_list)
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[0], ""
            )
        )
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[1], rashiNameList[getRasiNumber(planetDegree[12])]
            )
        )
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[2], naksLordNameList[getNakshatraNumber(planetDegree[12])]
            )
        )
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[3], naksLordNameList[getSubLordNumber(planetDegree[12])]
            )
        )
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[4], rashiNameList[getRasiNumber(planetDegree[2])]
            )
        )
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[5], naksLordNameList[getNakshatraNumber(planetDegree[2])]
            )
        )
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[6], naksLordNameList[getSubLordNumber(planetDegree[2])]
            )
        )

        return rulingPlanetList
    }

    fun getMiscData(context: Context): ArrayList<KpRulingPlanetBean> {
        val degSign = context.resources.getString(R.string.degree_sign)
        val minSign = context.resources.getString(R.string.minute_sign)
        val secSign = context.resources.getString(R.string.second_sign)
        val rulingPlanetList = ArrayList<KpRulingPlanetBean>()
        val rashiNameList = context.resources.getStringArray(R.array.rasi_lord_full_name_list)
        val naksLordNameList =
            context.resources.getStringArray(R.array.nakshatra_lord_full_name_list)
        val labelList = context.resources.getStringArray(R.array.kp_misc_label_list)
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[0], rashiNameList.get(getRasiNumber(arrayList[0].fortuna.toDouble()))
            )
        )
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[1], formatDMSInStringWithSign(
                    fDeg = arrayList[0].fortuna.toDouble(),
                    DegSign = degSign,
                    MinSign = minSign,
                    SecSign = secSign
                )
            )
        )
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[2],
                naksLordNameList.get(getSubLordNumber(arrayList[0].fortuna.toDouble()))
            )
        )
        rulingPlanetList.add(
            KpRulingPlanetBean(
                labelList[3], formatDMSInStringWithSign(
                    fDeg = arrayList[0].kpayan.toDouble(),
                    DegSign = degSign,
                    MinSign = minSign,
                    SecSign = secSign
                )
            )
        )
        return rulingPlanetList
    }
}