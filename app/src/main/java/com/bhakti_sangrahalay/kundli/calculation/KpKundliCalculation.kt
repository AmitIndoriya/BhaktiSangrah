package com.bhakti_sangrahalay.kundli.calculation

import android.content.Context
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.model.HouseSignificatorsBean
import com.bhakti_sangrahalay.model.KundliBean
import com.bhakti_sangrahalay.model.PlanetSignificationBean

object KpKundliCalculation {
    lateinit var arrayList: ArrayList<KundliBean>

    fun setData(arrayList: ArrayList<KundliBean>) {
        this.arrayList = arrayList
    }

    /* fun getPlanetDegreeArray(): DoubleArray {
         val planetDegree: Array<String> = arrayList[0].getPlanetDegree().split(",").toTypedArray()
         val degree = DoubleArray(13)
         var str = ""
         for (i in planetDegree.indices) {
             degree[i] = planetDegree[i].toDouble()
             str = str + degree[i] + ", "
         }
         // Log.i("KpDegreeArray", str)
         return degree
     }

     fun getKpCuspDegreeArray(): DoubleArray {
         val planetDegree = arrayList[0].kpCusp.split(",".toRegex()).toTypedArray()
         val degree = DoubleArray(13)
         for (i in planetDegree.indices) {
             degree[i] = planetDegree[i].toDouble()
         }
         return degree
     }

     fun getKPPlanetDegreeArray(): DoubleArray {
         val planetDegree = getPlanetDegreeArray()
         val degree = DoubleArray(13)
         var tempCalculation = 0.0
         val ayanDiff = arrayList[0].ayan.toDouble() - arrayList[0].kpayan.toDouble()
         var str = ""
         for (i in 1..planetDegree.size - 1) {
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

     fun getKpChartArray(): IntArray {
         var lagna = 0
         val planetInRashi = IntArray(13)
         val cuspsDegreeArray: DoubleArray = getKpCuspDegreeArray()
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

     private fun hasInHouse(cusp2: Double, cusp1: Double, plntDegree: Double): Boolean {
         var temp2 = cusp2
         if (temp2 - cusp1 < 0) temp2 += 360.00
         if (cusp1 < plntDegree + 360.0 && plntDegree + 360.0 < temp2) {
             return true
         }
         return cusp1 < plntDegree && plntDegree < temp2
     }*/
    //Kp system
    private fun getPlanetDegreeArray(): DoubleArray {
        val planetDegree = arrayList[0].planetDegree.split(",").toTypedArray()
        val degree = DoubleArray(13)
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
        }
        return degree
    }


    private fun hasInHouse(cusp2: Double, cusp1: Double, plntDegree: Double): Boolean {
        var temp2 = cusp2
        if (temp2 - cusp1 < 0) temp2 += 360.00
        if (cusp1 < plntDegree + 360.0 && plntDegree + 360.0 < temp2) {
            return true
        }
        return cusp1 < plntDegree && plntDegree < temp2
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

    fun getKPPlanetSignificationData(context: Context): java.util.ArrayList<PlanetSignificationBean> {
        val plaSigList: java.util.ArrayList<PlanetSignificationBean> =
            java.util.ArrayList<PlanetSignificationBean>()
        val planetName = context.resources.getStringArray(R.array.planet_name)
        plaSigList.add(
            PlanetSignificationBean(
                planetName[0],
                arrayList[0].planetSignification1
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[1],
                arrayList[0].planetSignification2
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[2],
                arrayList[0].planetSignification3
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[3],
                arrayList[0].planetSignification4
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[4],
                arrayList[0].planetSignification5
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[5],
                arrayList[0].planetSignification6
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[6],
                arrayList[0].planetSignification7
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[7],
                arrayList[0].planetSignification8
            )
        )
        plaSigList.add(
            PlanetSignificationBean(
                planetName[8],
                arrayList[0].planetSignification9
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
}