package com.bhakti_sangrahalay.kundli.calculation

import android.content.Context
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.KundliChalitTableBean
import com.bhakti_sangrahalay.model.KundliBean

object BasicChalitTableCalculation {
    lateinit var arrayList: ArrayList<KundliBean>

    fun setData(arrayList: ArrayList<KundliBean>) {
        this.arrayList = arrayList
    }

    private fun getKpDegreeArray(): DoubleArray {
        val planetDegree = arrayList[0].kpCusp.split(",".toRegex()).toTypedArray()
        val degree = DoubleArray(13)
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
        }
        return degree
    }

    private fun checkDegree(deg: Double): Double {
        var temp = deg
        if (temp < 0) temp += 360.00
        if (temp > 360.0) temp -= 360.00
        return temp
    }

    private fun getCuspsDegreeArray(): DoubleArray {
        val tempDegree = DoubleArray(12)
        var diff2: Double
        var temp1: Double
        val cuspDegree: DoubleArray = getCuspsMidDegreeArrayForChalit()

        // CLACULATE CUSP DEGREE
        for (i in 0..11) {
            if (i == 0) {
                diff2 = cuspDegree[0] - cuspDegree[11]
                if (diff2 < 0) {
                    diff2 = 360.0 - cuspDegree[11]
                    diff2 += cuspDegree[0]
                }
            } else {
                diff2 = cuspDegree[i] - cuspDegree[i - 1]
                if (diff2 < 0) {
                    diff2 = 360.0 - cuspDegree[i - 1]
                    diff2 += cuspDegree[i]
                }
            }
            diff2 /= 2.0
            temp1 = cuspDegree[i] - diff2
            if (temp1 < 0.0) temp1 += 360.0
            tempDegree[i] = temp1
        }
        return tempDegree
    }

    private fun getCuspsMidDegreeArrayForChalit(): DoubleArray {
        val cuspDegree = DoubleArray(12)
        val ayaDiff: Double = arrayList[0].kpayan.toDouble() - arrayList[0].ayan.toDouble()


        //CUSP -1
        cuspDegree[0] = getKpDegreeArray()[0] + ayaDiff
        cuspDegree[0] = checkDegree(cuspDegree[0])

        //CUSP -10
        cuspDegree[9] = getKpDegreeArray()[9] + ayaDiff
        cuspDegree[9] = checkDegree(cuspDegree[9])


        //CUSP -7
        cuspDegree[6] = cuspDegree[0] + 180
        cuspDegree[6] = checkDegree(cuspDegree[6])

        //CUSP -4
        cuspDegree[3] = cuspDegree[9] - 180
        cuspDegree[3] = checkDegree(cuspDegree[3])

        //CUSP -2,3
        var diff: Double = cuspDegree[3] - cuspDegree[0]
        if (diff < 0) diff += 360.0
        cuspDegree[1] = cuspDegree[0] + diff / 3
        cuspDegree[1] = checkDegree(cuspDegree[1])
        cuspDegree[2] = cuspDegree[1] + diff / 3
        cuspDegree[2] = checkDegree(cuspDegree[2])

        //CUSP -5,6
        diff = cuspDegree[6] - cuspDegree[3]
        if (diff < 0) diff += 360.0
        cuspDegree[4] = cuspDegree[3] + diff / 3
        cuspDegree[4] = checkDegree(cuspDegree[4])
        cuspDegree[5] = cuspDegree[4] + diff / 3
        cuspDegree[5] = checkDegree(cuspDegree[5])


        //CUSP -8,9
        diff = cuspDegree[9] - cuspDegree[6]
        if (diff < 0) diff += 360.0
        cuspDegree[7] = cuspDegree[6] + diff / 3
        cuspDegree[7] = checkDegree(cuspDegree[7])
        cuspDegree[8] = cuspDegree[7] + diff / 3
        cuspDegree[8] = checkDegree(cuspDegree[8])


        //CUSP -11,12
        diff = cuspDegree[0] - cuspDegree[9]
        if (diff < 0) diff += 360.0
        cuspDegree[10] = cuspDegree[9] + diff / 3
        cuspDegree[10] = checkDegree(cuspDegree[10])
        cuspDegree[11] = cuspDegree[10] + diff / 3
        cuspDegree[11] = checkDegree(cuspDegree[11])
        return cuspDegree
    }

    private fun formatDMSInStringWithSign(
        _fDeg: Double,
        DegSign: String,
        MinSign: String,
        SecSign: String
    ): String {
        var strFormattedDeg: String?
        val sDeg: String?
        val sMin: String?
        val sSec: String?
        val min: Int
        val sec: Int

        sDeg = _fDeg.toInt().toString()
        strFormattedDeg = when (sDeg.trim { it <= ' ' }.length) {
            0 -> "000$sDeg"
            1 -> "00$sDeg"
            2 -> "0$sDeg"
            else -> sDeg
        }

        strFormattedDeg += DegSign
        var temp: Double = _fDeg - _fDeg.toInt().toDouble()
        min = (temp * 60).toInt()
        sMin = min.toString()


        strFormattedDeg =
            if (sMin.trim { it <= ' ' }.length < 2) strFormattedDeg + "0" + sMin else strFormattedDeg + sMin


        strFormattedDeg += MinSign
        temp *= 60
        temp -= temp.toInt().toDouble()
        sec = (temp * 60).toInt()

        sSec = sec.toString()
        strFormattedDeg =
            if (sSec.trim { it <= ' ' }.length < 2) strFormattedDeg + "0" + sSec else strFormattedDeg + sSec


        strFormattedDeg += SecSign
        return strFormattedDeg.trim { it <= ' ' }
    }

    fun getChalitTableData(context: Context): ArrayList<KundliChalitTableBean> {
        val arrayList = ArrayList<KundliChalitTableBean>()
        val cuspDegArrayList = getCuspsDegreeArray()
        val midDegArrayList = getCuspsMidDegreeArrayForChalit()
        val degSign = context.resources.getString(R.string.degree_sign)
        val minSign = context.resources.getString(R.string.minute_sign)
        val secSign = context.resources.getString(R.string.second_sign)
        val rashiShortNameArr = context.resources.getStringArray(R.array.rasi_short_name_list)
        for (i in 0..11) {
            val bhBegRashi = (cuspDegArrayList[i] / 30.0).toInt()
            val midBhRashi = (midDegArrayList[i] / 30.0).toInt()
            val bhBegRem = cuspDegArrayList[i] % 30.0
            val midBhRem = midDegArrayList[i] % 30.0
            arrayList.add(
                KundliChalitTableBean(
                    bhav = i + 1,
                    bhBegSign = rashiShortNameArr[bhBegRashi],
                    bhBegDeg = formatDMSInStringWithSign(bhBegRem, degSign, minSign, secSign),
                    bhMidSign = rashiShortNameArr[midBhRashi],
                    bhMidDeg = formatDMSInStringWithSign(midBhRem, degSign, minSign, secSign),
                )
            )
        }

        return arrayList
    }
}