package com.bhakti_sangrahalay.kundli.calculation

import com.bhakti_sangrahalay.contansts.KpConstants
import com.bhakti_sangrahalay.inteface.CalculationIntefaceNew
import com.bhakti_sangrahalay.kundli.model.PlanetSignificationView2Bean

object KPPlanetSignificationView2Calculation : CalculationIntefaceNew by BaseCalculation {
    private var kpPlanetDegree: DoubleArray
    private var kpCuspDegree: DoubleArray

    init {
        kpPlanetDegree = getKpPlanetDegreeForSigniVIew()
        kpCuspDegree = getKpCuspDegreeForSigniVIew()
    }

    private fun getKpPlanetDegreeForSigniVIew(): DoubleArray {
        val degree = DoubleArray(9)
        for (i in 1..9) {
            degree[i - 1] = BaseCalculation.kpPlanetDegree[i]
        }
        return degree
    }

    private fun getKpCuspDegreeForSigniVIew(): DoubleArray {
        val degree = DoubleArray(12)
        for (i in 0..11) {
            degree[i] = BaseCalculation.kpCuspDegree[i]
        }
        return degree
    }

    private fun getHousesInPlanetRashi(plntNumber: Int): IntArray? {
        var cuspRashi: Int
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

    private fun getPlanetSignificationLevel1(plnt: Int): Int {
        var plntNakLordInBhava = -1
        try {
            val plntNakLord = getStarLord(kpPlanetDegree[plnt])
            var j: Int
            for (i in 0..11) {
                j = i + 1
                if (j > 11) j = 0
                plntNakLordInBhava = BaseCalculation.getBhavOfPlanet(
                    kpCuspDegree[j],
                    kpCuspDegree[i],
                    i,
                    kpPlanetDegree[plntNakLord]
                )
                if (plntNakLordInBhava > 0) break
            }
        } catch (e: java.lang.Exception) {
        }
        return plntNakLordInBhava
    }

    private fun getPlanetSignificationLevel2(planetDegree: Double): Int {
        var plntNakLordInBhava = -1
        try {
            var j: Int
            for (i in 0..11) {
                j = i + 1
                if (j > 11) j = 0
                plntNakLordInBhava =
                    BaseCalculation.getBhavOfPlanet(
                        kpCuspDegree[j],
                        kpCuspDegree[i],
                        i,
                        planetDegree
                    )
                if (plntNakLordInBhava > 0) break
            }
        } catch (e: java.lang.Exception) {
        }
        return plntNakLordInBhava
    }

    private fun getPlanetSignificationLevel3(planetDegree: Double): IntArray? {
        var starLordInCusp: IntArray? = null
        try {
            val plntNakLord = getStarLord(planetDegree)
            starLordInCusp = getHousesInPlanetRashi(plntNakLord)
        } catch (e: Exception) {
        }
        return starLordInCusp
    }

    private fun getPlanetSignificationLevel4(plntName: Int): IntArray? {
        var cuspOwnedByPlanetItself: IntArray? = null
        try {
            cuspOwnedByPlanetItself = getHousesInPlanetRashi(plntName)
        } catch (e: Exception) {
        }
        return cuspOwnedByPlanetItself
    }

    fun getPlanetSignifiactionView2(): ArrayList<PlanetSignificationView2Bean> {
        val arrayList = ArrayList<PlanetSignificationView2Bean>()
        val plntStrength = BaseCalculation.getPlanetStrength()
        for (i in KpConstants.PLANET_INDEX.indices) {
            arrayList.add(
                PlanetSignificationView2Bean(
                    plaNo = (i + 1).toString(),
                    l1 = getPlanetSignificationLevel1(KpConstants.PLANET_INDEX[i]).toString(),
                    l2 = getPlanetSignificationLevel2(kpPlanetDegree[KpConstants.PLANET_INDEX[i]]).toString(),
                    l3 = BaseCalculation.convertIntArrayIntoString(
                        BaseCalculation.getSortedWithoutDuplicate(
                            getPlanetSignificationLevel3(
                                kpPlanetDegree[KpConstants.PLANET_INDEX[i]]
                            )
                        )
                    ),
                    l4 = BaseCalculation.convertIntArrayIntoString(
                        BaseCalculation.getSortedWithoutDuplicate(
                            getPlanetSignificationLevel4(
                                KpConstants.PLANET_INDEX[i]
                            )
                        )
                    ),
                    strength = plntStrength[0].toString()
                )
            )
        }
        return arrayList
    }

}