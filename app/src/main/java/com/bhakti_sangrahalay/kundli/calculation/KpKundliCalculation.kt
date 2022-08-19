package com.bhakti_sangrahalay.kundli.calculation

import android.content.Context
import android.util.Log
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.inteface.CalculationInterface
import com.bhakti_sangrahalay.kundli.model.BasicKundliPlanetSubData
import com.bhakti_sangrahalay.model.HouseSignificatorsBean
import com.bhakti_sangrahalay.model.KundliBean
import com.bhakti_sangrahalay.model.PlanetSignificationBean

object KpKundliCalculation : CalculationInterface by KundliCalculationBaseObject {
    lateinit var arrayList: ArrayList<KundliBean>

    fun setData(arrayList: ArrayList<KundliBean>) {
        this.arrayList = arrayList
    }

    private fun getPlanetDegreeArray(): DoubleArray {
        val planetDegree = arrayList[0].planetDegree.split(",").toTypedArray()
        val degree = DoubleArray(13)
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
        }
        return degree
    }


    private fun getKPPlanetDegreeArray(): DoubleArray {
        val planetDegree: DoubleArray = getPlanetDegreeArray()
        val degree = DoubleArray(13)
        var tempCalculation: Double
        val ayanDiff = arrayList[0].ayan.toDouble() - arrayList[0].kpayan.toDouble()
        for (i in planetDegree.indices) {
            tempCalculation = planetDegree[i] + ayanDiff
            if (tempCalculation < 0) {
                tempCalculation += 360.00
            } else if (tempCalculation >= 360) {
                tempCalculation -= 360.00
            }
            degree[i] = tempCalculation
        }
        return degree
    }

    fun getKpDegreeArray(): DoubleArray {
        val planetDegree = arrayList[0].kpCusp.split(",").toTypedArray()
        val degree = DoubleArray(13)
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
        }
        return degree
    }

    fun getKPLagnaRashiKundliPlanetsRashiArray(): IntArray {
        val obj = getKPPlanetDegreeArray()
        val planestRashi = IntArray(13)
        planestRashi[0] = (obj[1] / 30.00).toInt() + 1
        planestRashi[1] = (obj[2] / 30.00).toInt() + 1
        planestRashi[2] = (obj[3] / 30.00).toInt() + 1
        planestRashi[3] = (obj[4] / 30.00).toInt() + 1
        planestRashi[4] = (obj[5] / 30.00).toInt() + 1
        planestRashi[5] = (obj[6] / 30.00).toInt() + 1
        planestRashi[6] = (obj[7] / 30.00).toInt() + 1
        planestRashi[7] = (obj[8] / 30.00).toInt() + 1
        planestRashi[8] = (obj[9] / 30.00).toInt() + 1
        planestRashi[9] = (obj[10] / 30.00).toInt() + 1
        planestRashi[10] = (obj[11] / 30.00).toInt() + 1
        planestRashi[11] = (obj[12] / 30.00).toInt() + 1
        planestRashi[12] = (obj[0] / 30.00).toInt() + 1
        return planestRashi
    }

    fun getKpChartArray(): IntArray {
        var lagna = 0
        val planetInRashi = IntArray(13)
        val cuspsDegreeArray = getKpDegreeArray()
        val planetDegreeArray = getKPPlanetDegreeArray()
        val lagnaDegree = planetDegreeArray[0]
        var lagnaVal = cuspsDegreeArray[0] + 1.00
        if (lagnaVal > 360.00) lagnaVal -= 360.00
        var count = 0
        while (count < planetDegreeArray.size - 1) {
            planetDegreeArray[count] = planetDegreeArray[count + 1]
            count++
        }
        planetDegreeArray[count] = lagnaDegree
        var isLagnaAdded = false
        var bhav1: Double
        var bhav2: Double
        for (i in 0..11) {
            bhav1 = cuspsDegreeArray[i]
            bhav2 = if (i < 11) {
                cuspsDegreeArray[i + 1]
            } else {
                cuspsDegreeArray[0]
            }
            for (j in 0..11) {
                if (hasInHouse(bhav2, bhav1, planetDegreeArray[j])) {
                    planetInRashi[j] = i
                }
                if (!isLagnaAdded && hasInHouse(bhav2, bhav1, lagnaVal)) {
                    lagna = i
                    isLagnaAdded = true
                }
            }
        }
        planetInRashi[12] = lagna
        return planetInRashi
    }

    fun getKPPlanetSignificationData(context: Context): ArrayList<PlanetSignificationBean> {
        val plaSigList: java.util.ArrayList<PlanetSignificationBean> =
            java.util.ArrayList<PlanetSignificationBean>()
        val planetName = context.resources.getStringArray(R.array.planet_name)
        plaSigList.add(
            PlanetSignificationBean(
                planetName[0],
                removeZeroFromPlanetSingnificationStr(arrayList[0].planetSignification1)
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[1],
                removeZeroFromPlanetSingnificationStr(arrayList[0].planetSignification2)
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[2],
                removeZeroFromPlanetSingnificationStr(arrayList[0].planetSignification3)
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[3],
                removeZeroFromPlanetSingnificationStr(arrayList[0].planetSignification4)
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[4],
                removeZeroFromPlanetSingnificationStr(arrayList[0].planetSignification5)
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[5],
                removeZeroFromPlanetSingnificationStr(arrayList[0].planetSignification6)
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[6],
                removeZeroFromPlanetSingnificationStr(arrayList[0].planetSignification7)
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[7],
                removeZeroFromPlanetSingnificationStr(arrayList[0].planetSignification8)
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[8],
                removeZeroFromPlanetSingnificationStr(arrayList[0].planetSignification9)
            )
        )
        return plaSigList
    }

    fun getKPHouseSignificatorsData(context: Context): java.util.ArrayList<HouseSignificatorsBean> {
        val planetName = context.resources.getStringArray(R.array.planet_name)
        val houseSignificatorList: java.util.ArrayList<HouseSignificatorsBean> =
            java.util.ArrayList<HouseSignificatorsBean>()
        val strTempArr = arrayOfNulls<String>(9)
        val bhavArr = arrayOf("", "", "", "", "", "", "", "", "", "", "", "")
        try {
            strTempArr[0] = arrayList[0].planetSignification1
            strTempArr[1] = arrayList[0].planetSignification2
            strTempArr[2] = arrayList[0].planetSignification3
            strTempArr[3] = arrayList[0].planetSignification4
            strTempArr[4] = arrayList[0].planetSignification5
            strTempArr[5] = arrayList[0].planetSignification6
            strTempArr[6] = arrayList[0].planetSignification7
            strTempArr[7] = arrayList[0].planetSignification8
            strTempArr[8] = arrayList[0].planetSignification9
            for (j in strTempArr.indices) {
                val tArray = strTempArr[j]!!.split(",").toTypedArray()
                for (s in tArray) {
                    when (s.toInt()) {
                        1 -> bhavArr[0] = bhavArr[0] + planetName[j] + ","
                        2 -> bhavArr[1] = bhavArr[1] + planetName[j] + ","
                        3 -> bhavArr[2] = bhavArr[2] + planetName[j] + ","
                        4 -> bhavArr[3] = bhavArr[3] + planetName[j] + ","
                        5 -> bhavArr[4] = bhavArr[4] + planetName[j] + ","
                        6 -> bhavArr[5] = bhavArr[5] + planetName[j] + ","
                        7 -> bhavArr[6] = bhavArr[6] + planetName[j] + ","
                        8 -> bhavArr[7] = bhavArr[7] + planetName[j] + ","
                        9 -> bhavArr[8] = bhavArr[8] + planetName[j] + ","
                        10 -> bhavArr[9] = bhavArr[9] + planetName[j] + ","
                        11 -> bhavArr[10] = bhavArr[10] + planetName[j] + ","
                        12 -> bhavArr[11] = bhavArr[11] + planetName[j] + ","
                    }
                }
            }
        } catch (e: Exception) {
        }
        for (i in 0..11) {
            houseSignificatorList.add(HouseSignificatorsBean(i, bhavArr[i]))
        }
        return houseSignificatorList
    }

    fun getKPPlanetsData(context: Context): ArrayList<BasicKundliPlanetSubData> {
        val plaDeg = getKPPlanetDegreeArray()
        val degSign = context.resources.getString(R.string.degree_sign)
        val minSign = context.resources.getString(R.string.minute_sign)
        val secSign = context.resources.getString(R.string.second_sign)
        val plaName = context.resources.getStringArray(R.array.planet_and_lagna_name_list)
        val rashiLord = context.resources.getStringArray(R.array.rasi_lord_short_name_list)
        val nakshLord = context.resources.getStringArray(R.array.nak_lord_short_name_list)

        val arrayList = ArrayList<BasicKundliPlanetSubData>()
        for (i in 0..12) {
            val rasiNakSubSub =
                getRashiNakSubSub(plaDeg[i], rashiLord, nakshLord)
            val arr = rasiNakSubSub.split("-")
            val rashi = arr[0]
            val naks = arr[1]
            val sub = arr[2]
            val subsub = arr[3]
            arrayList.add(
                BasicKundliPlanetSubData(
                    plaName = plaName[i],
                    plaDeg = formatDMSInStringWithSign(
                        plaDeg[i],
                        degSign,
                        minSign,
                        secSign
                    ),
                    signLord = rashi,
                    nakshLord = naks,
                    subLord = sub,
                    subSubLord = subsub
                )
            )

            Log.i(
                "Planet Data",
                plaName[i] + "-" + formatDMSInStringWithSign(
                    plaDeg[i],
                    degSign,
                    minSign,
                    secSign
                ) + "-" + getRashiNakSubSub(plaDeg[i], rashiLord, nakshLord)
            )
        }
        return arrayList
    }

    fun getKPCuspData(context: Context): ArrayList<BasicKundliPlanetSubData> {
        val plaDeg = getKpDegreeArray()
        val degSign = context.resources.getString(R.string.degree_sign)
        val minSign = context.resources.getString(R.string.minute_sign)
        val secSign = context.resources.getString(R.string.second_sign)
        val plaName = context.resources.getStringArray(R.array.planet_and_lagna_name_list)
        val rashiLord = context.resources.getStringArray(R.array.rasi_lord_short_name_list)
        val nakshLord = context.resources.getStringArray(R.array.nak_lord_short_name_list)

        val arrayList = ArrayList<BasicKundliPlanetSubData>()
        for (i in 0..11) {
            val rasiNakSubSub =
                getRashiNakSubSub(plaDeg[i], rashiLord, nakshLord)
            val arr = rasiNakSubSub.split("-")
            val rashi = arr[0]
            val naks = arr[1]
            val sub = arr[2]
            val subsub = arr[3]
            arrayList.add(
                BasicKundliPlanetSubData(
                    plaName = (i + 1).toString(),
                    plaDeg = formatDMSInStringWithSign(
                        plaDeg[i],
                        degSign,
                        minSign,
                        secSign
                    ),
                    signLord = rashi,
                    nakshLord = naks,
                    subLord = sub,
                    subSubLord = subsub
                )
            )

            Log.i(
                "Planet Data",
                plaName[i] + "-" + formatDMSInStringWithSign(
                    plaDeg[i],
                    degSign,
                    minSign,
                    secSign
                ) + "-" + getRashiNakSubSub(plaDeg[i], rashiLord, nakshLord)
            )
        }
        return arrayList
    }

    private fun removeZeroFromPlanetSingnificationStr(str: String): String {
        val arr = str.split(",")
        val result = StringBuilder()
        for (i in arr.indices) {
            if (!arr[i].equals("0")) {
                result.append(arr[i] + ", ")
            }

        }
        return result.toString().substring(0, result.toString().length - 2)
    }
}