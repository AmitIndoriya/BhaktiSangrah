package com.bhakti_sangrahalay.kundli.calculation

import android.content.Context
import android.util.Log
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.contansts.KpConstants
import com.bhakti_sangrahalay.kundli.model.KPCilSubSubBean
import com.bhakti_sangrahalay.model.KundliBean
import com.bhakti_sangrahalay.util.Utility

object KpCilSubSubcalculation {
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
        var a: Int = (d / 120.0).toInt()
        d -= a * 120.0
        a = (d * 3.0 / 40.0).toInt()
        return a
    }

    private fun getSubLord(deg: Double): Int {
        var d = deg
        var i = 0
        val a = getStarLord(d)
        d -= (d / 120.0).toInt() * 120.0
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
        return b
    }

    private fun getSubSubLord(deg: Double): Int {
        var d = deg
        var i = 0
        val a = getStarLord(d)
        val b = getSubLord(d)
        d -= (d / 120.0).toInt() * 120.0
        d -= a * 40.0 / 3.0
        d *= 9.0
        var f = 0
        while (b < 9) {
            i = a + f
            if (i >= 9) i -= 9
            d -= if (KpConstants.y1[i] <= d) KpConstants.y1[i] else break
            f++
        }
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

        // RETURN SUB SUB LORAD
        return c
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

    private fun getRashiLordOfThePlanetWhereItPlaced(plntDeg: Double): Int {
        val rashi = (plntDeg / 30 + 1).toInt()
        return KpConstants.RASHI_TO_PLANET[rashi]
    }

    private fun checkPlanetIsStarLordOfOtherPlanet(plntNumber: Int): Boolean {
        var isLord = false
        for (i in 0..8) if (i != plntNumber) if (plntNumber == KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(
                planetDegree[i]
            )]
        ) isLord = true
        return isLord
    }

    private fun isPlanetInItsOwnStar(plntNumber: Int): Boolean {
        var isPlanetInOwnStar = false
        val plntStarLord = KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(planetDegree[plntNumber])]
        if (plntNumber == plntStarLord) isPlanetInOwnStar = true
        return isPlanetInOwnStar
    }

    private fun getKCILType1(plntNumber: Int): IntArray {
        val kCil = IntArray(12)
        val cuspStarLord = IntArray(12)
        val cuspSubLord = IntArray(12)
        val cuspSubSubLord = IntArray(12)
        var index = -1
        val plntStarlord: Int
        val rahuKetuRashiLord: Int


        for (ind in kCil.indices) {
            kCil[ind] = -1
        }
        for (i in 0..11) {
            cuspStarLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(kpCuspDegree[i])]
            cuspSubLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getSubLord(kpCuspDegree[i])]
            cuspSubSubLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getSubSubLord(kpCuspDegree[i])]
        }
        if (plntNumber == KpConstants.RAHU || plntNumber == KpConstants.KETU) {
            plntStarlord = KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(planetDegree[plntNumber])]
            for (i in 0..11) if (
                plntNumber == cuspStarLord[i]
                || plntNumber == cuspSubLord[i]
                || plntNumber == cuspSubSubLord[i]
            ) kCil[++index] = i + 1
            val cuspNum: Int = getHouseOccupied(planetDegree[plntNumber])
            if (!hasNumberInIntegerArray(kCil, cuspNum)) kCil[++index] = cuspNum
            rahuKetuRashiLord = getRashiLordOfThePlanetWhereItPlaced(planetDegree[plntNumber])
            val slofRashiLord: Int =
                KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(planetDegree[rahuKetuRashiLord])]


            if ((plntNumber == KpConstants.RAHU || plntNumber == KpConstants.KETU)
                && (plntStarlord == KpConstants.RAHU || plntStarlord == KpConstants.KETU)
            ) {
                for (i in 0..11) {
                    if (rahuKetuRashiLord == cuspStarLord[i]
                        || rahuKetuRashiLord == cuspSubLord[i]
                        || rahuKetuRashiLord == cuspSubSubLord[i]
                    ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
                }
            } else {
                for (i in 0..11) {
                    if (slofRashiLord == cuspStarLord[i]
                        || slofRashiLord == cuspSubLord[i]
                        || slofRashiLord == cuspSubSubLord[i]
                    ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
                }
            }
            for (i in 0..11) {
                if (plntStarlord == cuspStarLord[i]
                    || plntStarlord == cuspSubLord[i]
                    || plntStarlord == cuspSubSubLord[i]
                ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
            }
        } else {
            plntStarlord = KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(planetDegree[plntNumber])]
            for (i in 0..11) {
                if (plntStarlord == cuspStarLord[i]
                    || plntStarlord == cuspSubLord[i]
                    || plntStarlord == cuspSubSubLord[i]
                ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
            }
            if (plntStarlord == KpConstants.RAHU || plntStarlord == KpConstants.KETU) {
                val cuspNum: Int = getHouseOccupied(planetDegree[plntStarlord])
                if (!hasNumberInIntegerArray(kCil, cuspNum)) kCil[++index] = cuspNum

                rahuKetuRashiLord =
                    getRashiLordOfThePlanetWhereItPlaced(planetDegree[plntStarlord]) //RASHI LORD OF RAHU/KETU
                for (i in 0..11) {
                    if (rahuKetuRashiLord == cuspStarLord[i]
                        || rahuKetuRashiLord == cuspSubLord[i]
                        || rahuKetuRashiLord == cuspSubSubLord[i]
                    ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
                }
                val slofRahuKetu: Int =
                    KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(planetDegree[plntStarlord])] //STAR LORD OF RAHU/KETU
                for (i in 0..11) {
                    if (slofRahuKetu == cuspStarLord[i]
                        || slofRahuKetu == cuspSubLord[i]
                        || slofRahuKetu == cuspSubSubLord[i]
                    ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
                }
            }
        }
        return Utility.removeValueFromIntArray(-1, kCil)
    }

    private fun getKCILType2(plntNumber: Int): IntArray {
        val kCil = IntArray(12)
        val cuspStarLord = IntArray(12)
        val cuspSubLord = IntArray(12)
        val cuspSubSubLord = IntArray(12)
        var index = -1
        val rahuKetuRashiLord: Int

        for (ind in kCil.indices) kCil[ind] = -1
        for (i in 0..11) {
            cuspStarLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(kpCuspDegree[i])]
            cuspSubLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getSubLord(kpCuspDegree[i])]
            cuspSubSubLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getSubSubLord(kpCuspDegree[i])]
        }

        val plntSublord: Int =
            KpConstants.PLANET_NAKSHTRA_LORD[getSubLord(planetDegree[plntNumber])]
        for (i in 0..11) {
            if (plntSublord == cuspStarLord[i]
                || plntSublord == cuspSubLord[i]
                || plntSublord == cuspSubSubLord[i]
            ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
        }
        if (plntSublord == KpConstants.RAHU || plntSublord == KpConstants.KETU) {
            val cuspNum: Int = getHouseOccupied(planetDegree[plntSublord])
            if (!hasNumberInIntegerArray(kCil, cuspNum)) kCil[++index] = cuspNum
            rahuKetuRashiLord = getRashiLordOfThePlanetWhereItPlaced(planetDegree[plntSublord])
            for (i in 0..11) {
                if (rahuKetuRashiLord == cuspStarLord[i]
                    || rahuKetuRashiLord == cuspSubLord[i]
                    || rahuKetuRashiLord == cuspSubSubLord[i]
                ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
            }
            val sublofRahuKetu =
                KpConstants.PLANET_NAKSHTRA_LORD[getSubLord(planetDegree[plntSublord])]
            for (i in 0..11) {
                if (sublofRahuKetu == cuspStarLord[i]
                    || sublofRahuKetu == cuspSubLord[i]
                    || sublofRahuKetu == cuspSubSubLord[i]
                ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
            }
        }
        return Utility.removeValueFromIntArray(-1, kCil)
    }

    private fun getKCILType3(plntNumber: Int): IntArray {
        val kCil = IntArray(12)
        val cuspStarLord = IntArray(12)
        val cuspSubLord = IntArray(12)
        val cuspSubSubLord = IntArray(12)
        var index = -1


        for (ind in kCil.indices) kCil[ind] = -1
        for (i in 0..11) {
            cuspStarLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(kpCuspDegree[i])]
            cuspSubLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getSubLord(kpCuspDegree[i])]
            cuspSubSubLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getSubSubLord(kpCuspDegree[i])]
        }

        val plntSSublord = KpConstants.PLANET_NAKSHTRA_LORD[getSubSubLord(planetDegree[plntNumber])]
        for (i in 0..11) {
            if (plntSSublord == cuspStarLord[i]
                || plntSSublord == cuspSubLord[i]
                || plntSSublord == cuspSubSubLord[i]
            ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
        }

        if (plntSSublord == KpConstants.RAHU || plntSSublord == KpConstants.KETU) {
            val slOfRahuKetu =
                KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(planetDegree[plntSSublord])]
            val cuspNum = getHouseOccupied(planetDegree[plntSSublord])
            if (!hasNumberInIntegerArray(kCil, cuspNum)) kCil[++index] = cuspNum
            val sslOfRahuKetu =
                KpConstants.PLANET_NAKSHTRA_LORD[getSubSubLord(planetDegree[plntSSublord])]
            if (plntNumber != KpConstants.RAHU && plntNumber != KpConstants.KETU) for (i in 0..11) {
                if (sslOfRahuKetu == cuspStarLord[i]
                    || sslOfRahuKetu == cuspSubLord[i]
                    || sslOfRahuKetu == cuspSubSubLord[i]
                ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
            }
            for (i in 0..11) {
                if (slOfRahuKetu == cuspStarLord[i]
                    || slOfRahuKetu == cuspSubLord[i]
                    || slOfRahuKetu == cuspSubSubLord[i]
                ) if (!hasNumberInIntegerArray(kCil, i + 1)) kCil[++index] = i + 1
            }
        }
        return Utility.removeValueFromIntArray(-1, kCil)
    }

    private fun getKCILType4(plntNumbere: Int): IntArray? {
        val kCil = IntArray(12)
        val cuspStarLord = IntArray(12)
        val cuspSubLord = IntArray(12)
        val cuspSubSubLord = IntArray(12)
        var indexType4 = -1
        var hasPositionalStutas = false
        val nSL: Boolean = checkPlanetIsStarLordOfOtherPlanet(plntNumbere)
        val ows: Boolean = isPlanetInItsOwnStar(plntNumbere)
        if (ows) {
            hasPositionalStutas = true
        } else if (!nSL) {
            hasPositionalStutas = true
        }
        return if (!hasPositionalStutas) null else {
            for (ind in kCil.indices) kCil[ind] = -1
            for (i in 0..11) {
                cuspStarLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getStarLord(kpCuspDegree[i])]
                cuspSubLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getSubLord(kpCuspDegree[i])]
                cuspSubSubLord[i] = KpConstants.PLANET_NAKSHTRA_LORD[getSubSubLord(kpCuspDegree[i])]
            }
            for (i in 0..11) {
                if (plntNumbere == cuspStarLord[i]
                    || plntNumbere == cuspSubLord[i]
                    || plntNumbere == cuspSubSubLord[i]
                ) kCil[++indexType4] = i + 1
            }
            if (plntNumbere == KpConstants.RAHU || plntNumbere == KpConstants.KETU) {
                val cuspNum: Int = getHouseOccupied(planetDegree[plntNumbere])
                if (!hasNumberInIntegerArray(kCil, cuspNum)) kCil[++indexType4] = cuspNum
            }
            Utility.removeValueFromIntArray(-1, kCil)
        }
    }

    fun getCilSubSubData(context: Context) {
        val nakshtraNadiList = ArrayList<KPCilSubSubBean>()
        val hashMap = HashMap<Int, IntArray>()
        for (i in 0..8) {
            val starLord = getKCILType2(KpConstants.PLANET_INDEX[i])
            hashMap[i] = starLord
        }
        for (i in 0 until hashMap.size) {
            nakshtraNadiList.add(
                KPCilSubSubBean(
                    planetName = i,
                    star = getFormattedStringForNakshNadi(
                        context,
                        i,
                        getKCILType1(KpConstants.PLANET_INDEX[i])
                    ),
                    sub = getFormattedStringForNakshNadi(
                        context,
                        i,
                        getKCILType2(KpConstants.PLANET_INDEX[i])
                    ),
                    subSub = getFormattedStringForNakshNadi(
                        context,
                        i,
                        getKCILType3(KpConstants.PLANET_INDEX[i])
                    ),
                    posStatus = getFormattedStringForNakshNadi(
                        context,
                        i,
                        getKCILType4(KpConstants.PLANET_INDEX[i])
                    )
                )
            )
        }
    }


    private fun getFormattedStringForNakshNadi(
        context: Context,
        planet: Int,
        plaNadi: IntArray?
    ): String {
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