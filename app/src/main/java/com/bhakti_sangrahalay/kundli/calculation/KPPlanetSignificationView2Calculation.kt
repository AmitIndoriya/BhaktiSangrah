package com.bhakti_sangrahalay.kundli.calculation

import com.bhakti_sangrahalay.contansts.KpConstants
import com.bhakti_sangrahalay.inteface.CalculationIntefaceNew
import com.bhakti_sangrahalay.kundli.model.PlanetSignificationView2Bean

object KPPlanetSignificationView2Calculation : CalculationIntefaceNew by BaseCalculation {

    private fun getPlanetSignificationLevel1(plnt: Int): Int {
        var plntNakLordInBhava = -1
        try {
            val plntNakLord = getStarLord(BaseCalculation.kpPlanetDegree[plnt])
            var j: Int
            for (i in 0..11) {
                j = i + 1
                if (j > 11) j = 0
                plntNakLordInBhava = BaseCalculation.getBhavOfPlanet(
                    BaseCalculation.kpCuspDegree[j],
                    BaseCalculation.kpCuspDegree[i],
                    i,
                    BaseCalculation.kpPlanetDegree[plntNakLord + 1]
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
                        BaseCalculation.kpCuspDegree[j],
                        BaseCalculation.kpCuspDegree[i],
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
            starLordInCusp = BaseCalculation.getHousesInPlanetRashi(plntNakLord)
        } catch (e: Exception) {
        }
        return starLordInCusp
    }

    private fun getPlanetSignificationLevel4(plntName: Int): IntArray? {
        var cuspOwnedByPlanetItself: IntArray? = null
        try {
            cuspOwnedByPlanetItself = BaseCalculation.getHousesInPlanetRashi(plntName)
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
                    l1 = getPlanetSignificationLevel1(KpConstants.PLANET_INDEX[i] + 1).toString(),
                    l2 = getPlanetSignificationLevel2(BaseCalculation.kpPlanetDegree[KpConstants.PLANET_INDEX[i] + 1]).toString(),
                    l3 = BaseCalculation.convertIntArrayIntoString(
                        BaseCalculation.getSortedWithoutDuplicate(
                            getPlanetSignificationLevel3(
                                BaseCalculation.kpPlanetDegree[KpConstants.PLANET_INDEX[i] + 1]
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