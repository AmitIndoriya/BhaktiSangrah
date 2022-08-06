package com.bhakti_sangrahalay.kundli.calculation

import android.content.Context
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.contansts.KpConstants
import com.bhakti_sangrahalay.inteface.CalculationIntefaceNew
import com.bhakti_sangrahalay.model.KundliBean
import com.bhakti_sangrahalay.util.Utility


object BaseCalculation : CalculationIntefaceNew {
    var arrayList = ArrayList<KundliBean>()
    lateinit var kpPlanetDegree: DoubleArray
    lateinit var kpCuspDegree: DoubleArray
    lateinit var kpPlanetDegreeForNN: DoubleArray
    lateinit var kpCuspDegreeForNN: DoubleArray
    fun setData(arrayList: ArrayList<KundliBean>) {
        this.arrayList = arrayList
        kpPlanetDegree = getKPPlanetDegreeArray()
        kpCuspDegree = getKpDegreeArray()
        kpPlanetDegreeForNN = getKpPlanetArrayForNakshtraNadi()
        kpCuspDegreeForNN = getKpCuspArrayForNakshtraNadi()
    }

    private fun getPlanetDegreeArray(): DoubleArray {
        val planetDegree: Array<String> =
            arrayList[0].planetDegree.split(",").toTypedArray()
        val degree = DoubleArray(13)
        var str = ""
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
            str = str + degree[i] + ", "
        }
        return degree
    }

    private fun getKpDegreeArray(): DoubleArray {
        val planetDegree =
            arrayList[0].kpCusp.split(",".toRegex()).toTypedArray()
        val degree = DoubleArray(13)
        var str = ""
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
            str = str + degree[i] + ", "
        }
        return degree
    }

    private fun getKPPlanetDegreeArray(): DoubleArray {
        val planetDegree = getPlanetDegreeArray()
        val degree = DoubleArray(13)
        var tempCalculation: Double
        val ayanDiff = arrayList[0].ayan.toDouble() - arrayList[0].kpayan.toDouble()
        for (i in 1 until planetDegree.size) {
            tempCalculation = planetDegree[i] + ayanDiff
            if (tempCalculation < 0) {
                tempCalculation += 360.00
            } else if (tempCalculation >= 360) {
                tempCalculation -= 360.00
            }
            degree[i] = tempCalculation
        }
        degree[12] = planetDegree[0]
        return degree
    }

    private fun getKpCuspArrayForNakshtraNadi(): DoubleArray {
        val cuspArray = getKpDegreeArray()
        val kpCuspArray = DoubleArray(12)
        for (i in 0..11) {
            kpCuspArray[i] = cuspArray[i]
        }
        return kpCuspArray
    }

    private fun getKpPlanetArrayForNakshtraNadi(): DoubleArray {
        val kpPlanetDegreeArray = getKPPlanetDegreeArray()
        val kpPlanetArray = DoubleArray(9)
        for (i in 1..9) {
            kpPlanetArray[i - 1] = kpPlanetDegreeArray[i]
        }
        return kpPlanetArray
    }

    override fun getStarLord(d: Double): Int {
        var d = d
        var a: Int = (d / 120.0).toInt()
        d -= a * 120.0
        a = (d * 3.0 / 40.0).toInt()

        return KpConstants.PLANET_NAKSHTRA_LORD[a]
    }

    fun getSubLord(d: Double): Int {
        var d = d
        var a: Int
        var b: Int
        var i = 0
        a = (d / 120.0).toInt()
        d -= a * 120.0
        a = (d * 3.0 / 40.0).toInt()
        d -= a * 40.0 / 3.0
        d *= 9.0
        b = 0
        while (b < 9) {
            i = a + b
            if (i >= 9) i -= 9
            d -= if (KpConstants.y1.get(i) <= d) KpConstants.y1.get(i) else break
            b++
        }
        b = i

        return KpConstants.PLANET_NAKSHTRA_LORD.get(b)
    }

    fun getBhavOfPlanet(
        cusp2: Double, cusp1: Double,
        cuspIndex: Int, plntDegree: Double
    ): Int {
        var temp2 = cusp2
        var plantInBhava = -1
        if (temp2 - cusp1 < 0) temp2 += 360.00
        for (i in 0..11) {
            if (cusp1 < plntDegree && plntDegree < temp2) {
                plantInBhava = cuspIndex
            }
            if (cusp1 < plntDegree + 360.0 && plntDegree + 360.0 < temp2) {
                plantInBhava = cuspIndex
            }
        }
        return plantInBhava + 1
    }

    fun getHousesInPlanetRashi(plntNumber: Int): IntArray? {
        // val kpCuspDegree = getKpCuspArrayForNakshtraNadi()
        var cuspRashi = -1
        var plntNakLordInBhava = -1
        var returnHouseArray: IntArray? = null
        val cuspOwnedByPlanetItself = IntArray(4)
        try {
            for (k in cuspOwnedByPlanetItself.indices) cuspOwnedByPlanetItself[k] = 0
            val plntNakLordRashi = IntArray(2)
            plntNakLordRashi[0] = KpConstants.PLANET_RASHI[plntNumber][0]
            plntNakLordRashi[1] = KpConstants.PLANET_RASHI[plntNumber][1]

            for (ral in plntNakLordRashi.indices) {
                if (plntNakLordRashi[ral] > 0) {
                    for (cuspIndex in kpCuspDegree.indices) {
                        cuspRashi = (kpCuspDegree[cuspIndex] / 30 + 1).toInt()
                        if (plntNakLordRashi[ral] == cuspRashi) cuspOwnedByPlanetItself[++plntNakLordInBhava] =
                            cuspIndex + 1
                    }
                }
            }
            returnHouseArray = IntArray(plntNakLordInBhava + 1)
            var index = -1
            for (len in cuspOwnedByPlanetItself.indices) {
                if (cuspOwnedByPlanetItself[len] > 0) returnHouseArray[++index] =
                    cuspOwnedByPlanetItself[len]
            }
            // end
        } catch (e: Exception) {
        }
        return returnHouseArray
    }

    fun getPlanetStrength(): IntArray {
        val nakshtraLord = IntArray(9)
        val plntStrength = IntArray(9)
        for (i in 0..8) nakshtraLord[i] = getStarLord(kpPlanetDegree[i])
        for (i in 0..8) plntStrength[i] = 1
        for (i in 0..8) {
            for (j in 0..8) {
                if (i == j) {
                    continue
                } else if (KpConstants.PLANET_INDEX.get(i) === nakshtraLord[j]) {
                    plntStrength[i] = 0
                }
            }
        }
        return plntStrength
    }

    fun convertIntArrayIntoString(intArray: IntArray?): String {
        var str = ""
        if (intArray != null) {
            for (i in 0..intArray.size - 1) {
                str = str + intArray[i] + ", "
            }
            //str = str.substring(0, str.length -2)
        }
        return str
    }

    fun getSortedWithoutDuplicate(arr: IntArray?): IntArray {
        val nadiArray = Utility.assendingSort(arr)
        val arrSize = Utility.removeDuplicateElements(nadiArray, nadiArray.size)
        val arrayAfterRemoveDuplicate = IntArray(arrSize)
        for (i in 0 until arrSize) {
            arrayAfterRemoveDuplicate[i] = nadiArray[i]
        }
        return arrayAfterRemoveDuplicate
    }

    fun getFormattedStringForNakshNadi(context: Context, planet: Int, plaNadi: IntArray?): String {
        var planetName =
            context.resources.getStringArray(R.array.planet_and_lagna_name_list)[planet + 1]
        if (plaNadi != null) {
            for (i in plaNadi.indices) {
                planetName = planetName + plaNadi[i] + ","
            }
        }
        return planetName
    }

}