package com.bhakti_sangrahalay.kundli.calculation

import com.bhakti_sangrahalay.contansts.KpConstants
import com.bhakti_sangrahalay.kundli.model.KPCilSubBean
import com.bhakti_sangrahalay.model.KundliBean
import com.bhakti_sangrahalay.util.Utility

object KpCilSubCalculation {
    lateinit var arrayList: ArrayList<KundliBean>
    private lateinit var planetDegree: DoubleArray
    private lateinit var kpCuspDegree: DoubleArray
    fun setData(arrayList: ArrayList<KundliBean>) {
        this.arrayList = arrayList
        planetDegree = getKpPlanetArrayForNakshtraNadi()
        kpCuspDegree = getKpCuspArrayForNakshtraNadi()
    }

    private fun getPlanetDegreeArray(): DoubleArray {
        val planetDegree: Array<String> =
            KpKundliCalculation.arrayList[0].planetDegree.split(",").toTypedArray()
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
            KpKundliCalculation.arrayList[0].kpCusp.split(",".toRegex()).toTypedArray()
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
        val ayanDiff =
            KpKundliCalculation.arrayList[0].ayan.toDouble() - KpKundliCalculation.arrayList[0].kpayan.toDouble()
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

    private fun getStarLord(deg: Double): Int {
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
            d -= if (KpConstants.y1[i] <= d) KpConstants.y1[i] else break
            b++
        }
        b = i
        d = d / KpConstants.y1[b] * (40.0 / 3.0)
        d *= 9.0
        var c = 0
        while (c < 9) {
            i = b + c
            if (i >= 9) i -= 9
            d -= if (KpConstants.y1[i] <= d) KpConstants.y1[i] else break
            c++
        }

        return KpConstants.PLANET_NAKSHTRA_LORD[a]
    }

    private fun getSubLord(deg: Double): Int {
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
            d -= if (KpConstants.y1[i] <= d) KpConstants.y1[i] else break
            b++
        }
        b = i
        d = d / KpConstants.y1[b] * (40.0 / 3.0)
        d *= 9.0
        var c = 0
        while (c < 9) {
            i = b + c
            if (i >= 9) i -= 9
            d -= if (KpConstants.y1[i] <= d) KpConstants.y1[i] else break
            c++
        }

        return KpConstants.PLANET_NAKSHTRA_LORD[b]
    }

    private fun getSubSubLord(deg: Double): Int {
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
            d -= if (KpConstants.y1[i] <= d) KpConstants.y1[i] else break
            b++
        }
        b = i
        d = d / KpConstants.y1[b] * (40.0 / 3.0)
        d *= 9.0
        var c = 0
        while (c < 9) {
            i = b + c
            if (i >= 9) i -= 9
            d -= if (KpConstants.y1[i] <= d) KpConstants.y1[i] else break
            c++
        }
        c = i

        return KpConstants.PLANET_NAKSHTRA_LORD[c]
    }

    private fun hasNumberInIntegerArray(arr: IntArray, number: Int): Boolean {
        for (i in arr.indices) if (arr[i] == number) return true
        return false
    }

    private fun getPlanetInBhav(
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

    private fun getHouseOccupied(planetDegree: Double): Int {
        var plntNakLordInBhava = -1
        try {
            var j: Int
            for (i in 0..11) {
                j = i + 1
                if (j > 11) j = 0
                plntNakLordInBhava = getPlanetInBhav(
                    kpCuspDegree[j],
                    kpCuspDegree[i], i, planetDegree
                )
                if (plntNakLordInBhava > 0) break
            }
        } catch (e: Exception) {
        }
        return plntNakLordInBhava
    }

    private fun getKPCilType1(cuspNumber: Int): IntArray {
        val type1 = IntArray(12)

        //STEP-1:GET CUSP SUB LORD(PLANET)
        val cuspSubLord = getSubLord(kpCuspDegree[cuspNumber])


        //STEP-2:GET  STAR LORD OF THE PLANET THAT COMES IN STEP 1
        val plntStarLord = getStarLord(planetDegree[cuspSubLord])

        //STEP-3 IDENTIFY THE CUSP THAT HAVE THE SAME SUB LORD PLANET AS PLANET  CAME IN STEP 2
        var index = -1
        val arrayCuspSubLord = IntArray(12)
        for (j in 0..11)
            arrayCuspSubLord[j] = getSubLord(kpCuspDegree[j])
        for (i in 0..11) {
            if (arrayCuspSubLord[i] == plntStarLord)
                type1[++index] = i + 1
        }


        //STEP-4:GET THE SUB LORD OF THE PLANET THAT HAVE COME IN STEP 1
        val plntSubLord = getSubLord(planetDegree[cuspSubLord])

        //STEP-5:IDENTIFY THE CUSP THAT HAVE THE SAME SUB LORD PLANET AS PLANET  CAME IN STEP 4

        for (k in 0..11)
            if (arrayCuspSubLord[k] == plntSubLord)
                if (!hasNumberInIntegerArray(type1, k + 1))
                    type1[++index] = k + 1


        return Utility.removeValueFromIntArray(0, type1)
    }

    private fun getKPCilType2(cuspNumber: Int): IntArray? {
        val type2 = IntArray(13)

        //STEP-1:FIND THE SUB LORD OF THE CUSP
        val cuspSubLord = getSubLord(this.kpCuspDegree[cuspNumber])
        var index = -1
        val arrayCuspSubLord = IntArray(12)
        for (j in 0..11) arrayCuspSubLord[j] = getSubLord(this.kpCuspDegree[j])
        for (i in this.kpCuspDegree.indices) {
            if (i != cuspNumber) if (arrayCuspSubLord[i] == cuspSubLord) type2[++index] = i + 1
        }
        //STEP:2
        val plntStarLord = getStarLord(planetDegree[cuspSubLord])
        for (i in this.kpCuspDegree.indices) {
            if (i != cuspNumber) if (arrayCuspSubLord[i] == plntStarLord) type2[++index] = i + 1
        }
        val arrayCuspSubSubLord = IntArray(12)
        for (j in 0..11) arrayCuspSubSubLord[j] = getSubSubLord(this.kpCuspDegree[j])
        for (i in this.kpCuspDegree.indices) {
            if (i != cuspNumber) if (arrayCuspSubSubLord[i] == plntStarLord) type2[++index] = i + 1
        }
        return Utility.removeValueFromIntArray(0, type2)
    }

    private fun getKPCilType3(cuspNumber: Int): IntArray? {

        //STEP-1:GET THE SUB LORD OF THE CUSP
        val cuspSubLord = getSubLord(kpCuspDegree[cuspNumber])

        //STEP-2:GET THE PLANETS WHICH HAS THE SAME STAR LORD AS PLANET FIND IN STEP-1
        var tempStep2Index = -1
        val tempStep2 = IntArray(12)

        //INITIALIZE THE ARRAY WITH VALUE 0
        for (fill in tempStep2.indices) tempStep2[fill] = 0
        for (i in planetDegree.indices) if (getStarLord(planetDegree[i]) == cuspSubLord) tempStep2[++tempStep2Index] =
            i
        if (tempStep2Index == -1) return null
        val planetsInStep2 = IntArray(tempStep2Index + 1)
        tempStep2Index = -1
        for (j in tempStep2.indices) if (tempStep2[j] > 0) planetsInStep2[++tempStep2Index] =
            tempStep2[j]
        //STEP-3:FIND THE CUSP WHICH HAVE THE SAME SUB LORD AS THE PLANETS FOUND IN STEP-3
        var tempStep3Index = -1
        val tempStep3 = IntArray(12)
        for (k in planetsInStep2.indices) {
            for (l in kpCuspDegree.indices) if (getSubLord(kpCuspDegree[l]) == planetsInStep2[k]) tempStep3[++tempStep3Index] =
                l + 1
        }
        //THIS CODE IS FOR REMOVING 0 FROM THE ARRAY
        val retType3 = IntArray(tempStep3Index + 1)
        var retType3Index = -1
        for (m in tempStep3.indices) {
            if (tempStep3[m] > 0) retType3[++retType3Index] = tempStep3[m]
        }
        return Utility.removeValueFromIntArray(0, retType3)
    }

    private fun getKPCilType4(cuspNumber: Int): Int {
        //STEP-1-:GET THE SUB LORD OF THE CUSP
        val cuspLord = getSubLord(kpCuspDegree[cuspNumber])
        //STEP-2-:GET THE STAR LORD OF THE PLANET FIND IN STEP-1
        val starLord = getStarLord(planetDegree[cuspLord])
        //FIND THE CUSP IN WHICH THE PLANET FOUND IN STEP-2, IS PLACED.
        return getHouseOccupied(planetDegree[starLord])
    }

    fun getCilSubData(): ArrayList<KPCilSubBean> {
        val arrayList = ArrayList<KPCilSubBean>()
        for (i in 0..11) {
            val type1 = getSortedWithoutDuplicate(getKPCilType1(i))
            val type2 = getKPCilType2(i)?.let { getSortedWithoutDuplicate(it) }
            val type3 = getKPCilType3(i)?.let { getSortedWithoutDuplicate(it) }
            val type4 = getKPCilType4(i)
            arrayList.add(
                KPCilSubBean(
                    houseNo = i + 1,
                    t1 = getFormattedStringForNakshNadi(type1),
                    t2 = getFormattedStringForNakshNadi(type2),
                    t3 = getFormattedStringForNakshNadi(type3),
                    t4 = type4.toString()
                )
            )
        }
        return arrayList
    }

    private fun getSortedWithoutDuplicate(arr: IntArray): IntArray {
        val nadiArray = Utility.assendingSort(arr)
        val arrSize = Utility.removeDuplicateElements(nadiArray, nadiArray.size)
        val arrayAfterRemoveDuplicate = IntArray(arrSize)
        for (i in 0 until arrSize) {
            arrayAfterRemoveDuplicate[i] = nadiArray[i]
        }
        return arrayAfterRemoveDuplicate
    }

    private fun getFormattedStringForNakshNadi(plaNadi: IntArray?): String {
        var planetName = ""
        if (plaNadi != null) {
            for (i in plaNadi.indices) {
                planetName = planetName + plaNadi[i] + ","
            }
        }
        if (planetName.length > 1) {
            planetName = planetName.substring(0, planetName.length - 1)
        }
        return planetName
    }
}