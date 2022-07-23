package com.bhakti_sangrahalay.kundli.calculation

import android.util.Log
import com.bhakti_sangrahalay.model.KundliBean

object KpKundliCalculation {
    lateinit var arrayList: ArrayList<KundliBean>

    fun setData(arrayList: ArrayList<KundliBean>) {
        this.arrayList = arrayList
    }

    fun getPlanetDegreeArray(): DoubleArray {
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

    fun getKpDegreeArray(): DoubleArray {
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
}