package com.bhakti_sangrahalay.kundli.calculation

import com.bhakti_sangrahalay.inteface.CalculationInterface

object KundliCalculationBaseObject : CalculationInterface {

    override fun formatDMSInStringWithSign(
        fDeg: Double,
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
        sDeg = fDeg.toInt().toString()
        strFormattedDeg = when (sDeg.trim { it <= ' ' }.length) {
            0 -> "000$sDeg"
            1 -> "00$sDeg"
            2 -> "0$sDeg"
            else -> sDeg
        }

        strFormattedDeg += DegSign
        var temp: Double = fDeg - fDeg.toInt().toDouble()
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

    override fun getRashiNakSubSub(
        d: Double, RasiLord: Array<String>,
        NakLord: Array<String>
    ): String {
        val y1 = IntArray(12)
        y1[0] = 7
        y1[1] = 20
        y1[2] = 6
        y1[3] = 10
        y1[4] = 7
        y1[5] = 18
        y1[6] = 16
        y1[7] = 19
        y1[8] = 17

        var d = d
        val sb = StringBuilder()
        var i = 0
        /*
         * if(d<0) d *=-1;
         */
        val f: Int = (d / 30.0).toInt()
        var a: Int = (d / 120.0).toInt()
        d -= a * 120.0
        a = (d * 3.0 / 40.0).toInt()
        d -= a * 40.0 / 3.0
        d *= 9.0
        var b = 0
        while (b < 9) {
            i = a + b
            if (i >= 9) i -= 9
            d -= if (y1[i] <= d) y1[i] else break
            b++
        }
        b = i
        d = d / y1[b] * (40.0 / 3.0)
        d *= 9.0
        var c = 0
        while (c < 9) {
            i = b + c
            if (i >= 9) i -= 9
            d -= if (y1[i] <= d) y1[i] else break
            c++
        }
        c = i
        /*
         * sb.append(CGlobalVariables.rasiLord[f] + "-");
         * sb.append(CGlobalVariables.nakLord[a] + "-");
         * sb.append(CGlobalVariables.nakLord[b] + "-");
         * sb.append(CGlobalVariables.nakLord[c]);
         */sb.append(RasiLord[f] + "-")
        sb.append(NakLord[a] + "-")
        sb.append(NakLord[b] + "-")
        sb.append(NakLord[c])
        return sb.toString().trim { it <= ' ' }
    }
    override fun hasInHouse(cusp2: Double, cusp1: Double, plntDegree: Double): Boolean {
        var temp2 = cusp2
        if (temp2 - cusp1 < 0) temp2 += 360.00
        if (cusp1 < plntDegree + 360.0 && plntDegree + 360.0 < temp2) {
            return true
        }
        return cusp1 < plntDegree && plntDegree < temp2
    }
}