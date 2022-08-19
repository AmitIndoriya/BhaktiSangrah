package com.bhakti_sangrahalay.kundli.calculation

import android.content.Context
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.contansts.KpConstants
import com.bhakti_sangrahalay.inteface.CalculationIntefaceNew
import com.bhakti_sangrahalay.kundli.model.KPNakshatraNadiBean
import com.bhakti_sangrahalay.util.Utility

object KpNakshtraNadiCalculation : CalculationIntefaceNew by BaseCalculation {
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

    fun getNakshtraNadi(context: Context): ArrayList<KPNakshatraNadiBean> {
        val nakshtraNadiList = ArrayList<KPNakshatraNadiBean>()
        val hashMap = HashMap<Int, IntArray>()
        var nadiArrayAfterRemoveDuplicate: IntArray
        for (i in 0..8) {
            val nadiArray = Utility.assendingSort(getPlanetNadi(KpConstants.PLANET_INDEX[i]))
            val arrSize = Utility.removeDuplicateElements(nadiArray, nadiArray.size)
            nadiArrayAfterRemoveDuplicate = IntArray(arrSize)
            for (j in 0 until arrSize) {
                nadiArrayAfterRemoveDuplicate[j] = nadiArray[j]
            }
            hashMap[i] = nadiArrayAfterRemoveDuplicate
        }
        for (i in 0 until hashMap.size) {
            val starLord = getPlanetStarLordNadi(KpConstants.PLANET_INDEX[i])
            val subLord = getPlanetSubLordNadi(KpConstants.PLANET_INDEX[i])
            nakshtraNadiList.add(
                KPNakshatraNadiBean(
                    planet = getFormattedStringForNakshNadi(
                        context,
                        KpConstants.PLANET_INDEX[i],
                        hashMap[i]
                    ),
                    starLord = getFormattedStringForNakshNadi(
                        context,
                        starLord,
                        hashMap[starLord]
                    ),
                    subLord = getFormattedStringForNakshNadi(
                        context,
                        subLord,
                        hashMap[subLord]
                    )
                )
            )
        }

        return nakshtraNadiList
    }

    private fun getPlanetNadi(plntNumber: Int): IntArray {
        //  val planetDegree = getKpPlanetArrayForNakshtraNadi()
        var planetNadiLength = 0
        var index = 0
        /*in which house planet is*/
        val houseOccupiedByPlanet: Int =
            getHouseOccupied(kpPlanetDegree[plntNumber])
        /* in which house,the planet rashi are*/
        val planetRashioccupiedHouse: IntArray? =
            if (plntNumber == KpConstants.RAHU || plntNumber == KpConstants.KETU) {
                return getRahuOrKetuNadi(plntNumber)
            } else {
                getHousesInPlanetRashi(plntNumber)
            }

        //GET THE LENGTH OF ARRAY
        for (i in planetRashioccupiedHouse!!.indices) if (planetRashioccupiedHouse[i] > 0) ++planetNadiLength
        val planetNadi = IntArray(planetNadiLength + 1)
        planetNadi[0] = houseOccupiedByPlanet
        for (i in planetRashioccupiedHouse.indices) if (planetRashioccupiedHouse[i] > 0) planetNadi[++index] =
            planetRashioccupiedHouse[i]

        return planetNadi
    }

    private fun getHouseOccupied(planetDegree: Double): Int {
        var plntNakLordInBhava = -1
        try {
            var j: Int
            for (i in 0..11) {
                j = i + 1
                if (j > 11) j = 0
                plntNakLordInBhava = BaseCalculation.getBhavOfPlanet(
                    kpCuspDegree[j],
                    kpCuspDegree[i], i, planetDegree
                )
                if (plntNakLordInBhava > 0) break
            }
        } catch (e: java.lang.Exception) {
        }
        return plntNakLordInBhava
    }

    private fun getRahuOrKetuNadi(plntNumber: Int): IntArray {
        //val planetDegree = getKpPlanetArrayForNakshtraNadi()
        val collection = IntArray(20)
        var index = -1
        val retVal: IntArray?
        for (i in collection.indices) collection[i] = -1
        val plntInWhichRashi: Int =
            getRashiLordOfThePlanetWhereItPlaced(kpPlanetDegree[plntNumber])
        val nadi1 = getPlanetNadi(plntInWhichRashi)
        for (i in nadi1.indices) if (nadi1[i] > 0) collection[++index] = nadi1[i]

        //STEP:2=CONJUCTION
        var arrayTemp: IntArray?
        val plntCon = getConjuction(plntNumber)
        if (plntCon != null) {
            for (i in plntCon.indices) {
                arrayTemp = getPlanetNadi(plntCon[i])
                for (j in arrayTemp.indices) if (arrayTemp[j] > 0) collection[++index] =
                    arrayTemp[j]
            }
        }
        //STEP:3 -ASPECT
        var arrayTempAspect: IntArray?
        val plntAspect =
            getPlanetsHaveAspectOnRahuKetu(kpPlanetDegree[plntNumber])
        if (plntAspect != null) {
            for (i in plntAspect.indices) {
                arrayTempAspect = getPlanetNadi(plntAspect[i])
                for (j in arrayTempAspect.indices) if (arrayTempAspect[j] > 0) collection[++index] =
                    arrayTempAspect[j]
            }
        }
        val houseOccupied = getHouseOccupied(kpPlanetDegree[plntNumber])
        retVal = IntArray(index + 2)
        var indexTemp = -1
        for (i in collection.indices) {
            if (collection[i] > 0) retVal[++indexTemp] = collection[i]
        }
        retVal[retVal.size - 1] = houseOccupied
        return retVal
    }

    private fun getPlanetsHaveAspectOnRahuKetu(degRahuKetu: Double): IntArray? {
        // val planetDegree = getKpPlanetArrayForNakshtraNadi()
        var aspectedPlanets: IntArray? = null
        val tempArray = IntArray(8)
        var index = -1
        var houseNumber: Int
        //LOOP FROM SUN -SAT
        for (i in tempArray.indices) tempArray[i] = -1
        for (pn in 0..6) {
            houseNumber = distanceOfHousePlanet2PositedFromPlanet1(
                kpPlanetDegree[pn] /*PLANET -1*/,
                degRahuKetu /*PLANET -2*/
            )
            when (pn) {
                KpConstants.SUN -> if (houseNumber == 7) {
                    ++index
                    tempArray[index] = pn
                }
                KpConstants.MOON -> if (houseNumber == 7) {
                    ++index
                    tempArray[index] = pn
                }
                KpConstants.MARS -> if (houseNumber == 4 || houseNumber == 7 || houseNumber == 8) {
                    ++index
                    tempArray[index] = pn
                }
                KpConstants.MERCURY -> if (houseNumber == 7) {
                    ++index
                    tempArray[index] = pn
                }
                KpConstants.JUPITER -> if (houseNumber == 5 || houseNumber == 7 || houseNumber == 9) {
                    ++index
                    tempArray[index] = pn
                }
                KpConstants.VENUS -> if (houseNumber == 7) {
                    ++index
                    tempArray[index] = pn
                }
                KpConstants.SAT -> if (houseNumber == 3 || houseNumber == 7 || houseNumber == 10) {
                    ++index
                    tempArray[index] = pn
                }
            }
        }
        if (index > -1) {
            var indexN = 0
            aspectedPlanets = IntArray(index + 1)
            for (i in aspectedPlanets.indices) {
                if (tempArray[i] > -1) {
                    aspectedPlanets[indexN] = tempArray[i]
                    indexN++
                }
            }
        }
        return aspectedPlanets
    }

    private fun distanceOfHousePlanet2PositedFromPlanet1(planet1: Double, planet2: Double): Int {
        var distance: Int = getRashiInPlanetPlaced(planet2) - getRashiInPlanetPlaced(planet1)
        if (distance < 0) distance += 12
        return distance + 1
    }

    private fun getRashiInPlanetPlaced(value: Double): Int {
        return (value / 30.00 + 1).toInt()
    }

    private fun getConjuction(plntNu: Int): IntArray? {
        val con = IntArray(10)
        var retValu: IntArray? = null
        var tempRashi: Int
        var index = -1
        for (j in con.indices) con[j] = -1

        val plntRashi = (kpPlanetDegree[plntNu] / 30 + 1).toInt()
        for (i in kpPlanetDegree.indices) {
            if (plntNu != i) {
                tempRashi = (kpPlanetDegree[i] / 30 + 1).toInt()
                if (plntRashi == tempRashi) {
                    ++index
                    con[index] = i
                }
            }
        }
        if (index > -1) {
            var indexN = 0
            retValu = IntArray(index + 1)
            for (j in retValu.indices) {
                if (con[j] > -1) {
                    retValu[indexN] = con[j]
                    indexN++
                }
            }
        }
        return retValu
    }

    private fun getRashiLordOfThePlanetWhereItPlaced(plntDeg: Double): Int {
        val rashi = (plntDeg / 30 + 1).toInt()
        return KpConstants.RASHI_TO_PLANET[rashi]
    }

    private fun getPlanetStarLordNadi(plntNumber: Int): Int {
        return getStarLord(kpPlanetDegree[plntNumber])
    }

    private fun getPlanetSubLordNadi(plntNumber: Int): Int {
        return BaseCalculation.getSubLord(kpPlanetDegree[plntNumber])
    }

    fun getFormattedStringForNakshNadi(context: Context, planet: Int, plaNadi: IntArray?): String {
        var planetName =
            context.resources.getStringArray(R.array.planet_short_name_list)[planet + 1]+"-"
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