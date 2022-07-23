package com.bhakti_sangrahalay.kundli.calculation

import android.content.Context
import android.util.Log
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.BasicKundliPanchangBean
import com.bhakti_sangrahalay.kundli.model.BasicKundliPlanetData
import com.bhakti_sangrahalay.kundli.model.BasicKundliPlanetSubData
import com.bhakti_sangrahalay.model.KundliBean

object BasicKundliCalculation {
    lateinit var arrayList: ArrayList<KundliBean>

    fun setData(arrayList: ArrayList<KundliBean>) {
        this.arrayList = arrayList
    }

    fun getKpDegreeArray(): DoubleArray {
        val planetDegree = arrayList[0].kpCusp.split(",").toTypedArray()
        val degree = DoubleArray(13)
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
        }
        return degree
    }

    private fun getPlanetDegreeArray(): DoubleArray {
        val planetDegree: Array<String> = arrayList[0].getPlanetDegree().split(",").toTypedArray()
        val degree = DoubleArray(13)
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
        }
        return degree
    }

    fun getCuspsDegreeArray(): DoubleArray {
        var cuspDegree = DoubleArray(12)
        val tempDegree = DoubleArray(12)
        var diff2 = 0.0
        var temp1 = 0.0
        cuspDegree = getCuspsMidDegreeArrayForChalit()

        // CLACULATE CUSP DEGREE
        for (i in 0..11) {
            if (i == 0) {
                diff2 = cuspDegree[0] - cuspDegree[11]
                if (diff2 < 0) {
                    diff2 = 360.0 - cuspDegree[11]
                    diff2 = diff2 + cuspDegree[0]
                }
            } else {
                diff2 = cuspDegree[i] - cuspDegree[i - 1]
                if (diff2 < 0) {
                    diff2 = 360.0 - cuspDegree[i - 1]
                    diff2 = diff2 + cuspDegree[i]
                }
            }
            diff2 /= 2.0
            temp1 = cuspDegree[i] - diff2
            if (temp1 < 0.0) temp1 += 360.0
            tempDegree[i] = temp1
        }
        return tempDegree
    }

    fun getCuspsMidDegreeArrayForChalit(): DoubleArray {
        val cuspDegree = DoubleArray(12)
        var ayaDiff = 0.0
        var diff = 0.0
        ayaDiff = arrayList[0].kpayan.toDouble() - arrayList[0].ayan.toDouble()


        //CUSP -1
        cuspDegree[0] = getKpDegreeArray().get(0) + ayaDiff
        cuspDegree[0] = checkDegree(cuspDegree[0])

        //CUSP -10
        cuspDegree[9] = getKpDegreeArray().get(9) + ayaDiff
        cuspDegree[9] = checkDegree(cuspDegree[9])


        //CUSP -7
        cuspDegree[6] = cuspDegree[0] + 180
        cuspDegree[6] = checkDegree(cuspDegree[6])

        //CUSP -4
        cuspDegree[3] = cuspDegree[9] - 180
        cuspDegree[3] = checkDegree(cuspDegree[3])

        //CUSP -2,3
        diff = cuspDegree[3] - cuspDegree[0]
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

    private fun checkDegree(deg: Double): Double {
        var temp = deg
        if (temp < 0) temp += 360.00
        if (temp > 360.0) temp -= 360.00
        return temp
    }

    fun getPlanetRashi(plaDeg: Double): Int {
        return (plaDeg / 30.00) as Int + 1
    }

    fun FormatDMSInStringWithSign(
        _fDeg: Double,
        DegSign: String,
        MinSign: String,
        SecSign: String
    ): String {
        var strFormattedDeg: String? = null
        var sDeg: String? = null
        var sMin: String? = null
        var sSec: String? = null
        var _min = 0
        var _sec = 0
        var temp = 0.0
        // fDeg =(float) (fDeg%30.00);
        sDeg = _fDeg.toInt().toString()
        strFormattedDeg = when (sDeg.trim { it <= ' ' }.length) {
            0 -> "000$sDeg"
            1 -> "00$sDeg"
            2 -> "0$sDeg"
            else -> sDeg
        }

        // strFormattedDeg=strFormattedDeg+getDegreeSign();
        strFormattedDeg = strFormattedDeg + DegSign
        temp = _fDeg - _fDeg.toInt().toDouble()
        _min = (temp * 60).toInt()
        sMin = _min.toString()

        // fDeg=fDeg-(int)fDeg;
        // fDeg *=60.00;
        // sMin=String.valueOf((int)(fDeg));
        strFormattedDeg =
            if (sMin.trim { it <= ' ' }.length < 2) strFormattedDeg + "0" + sMin else strFormattedDeg + sMin

        // strFormattedDeg=strFormattedDeg+"'";
        // strFormattedDeg=strFormattedDeg+CGlobalVariables.MINUTE_SIGN;
        strFormattedDeg = strFormattedDeg + MinSign
        temp = temp * 60
        temp = temp - temp.toInt().toDouble()
        _sec = (temp * 60).toInt()
        // fDeg=fDeg-(int)fDeg;
        // fDeg *=60.00;
        sSec = _sec.toString()
        strFormattedDeg =
            if (sSec.trim { it <= ' ' }.length < 2) strFormattedDeg + "0" + sSec else strFormattedDeg + sSec

        // strFormattedDeg=strFormattedDeg+"''";
        // strFormattedDeg=strFormattedDeg+CGlobalVariables.SECOND_SIGN;
        strFormattedDeg = strFormattedDeg + SecSign
        return strFormattedDeg.trim { it <= ' ' }
    }

    fun getNakshatraNumber(_deg: Double): Int {
        return (_deg * 0.075).toInt()
    }

    fun getRasiNumber(_deg: Double): Int {
        return (_deg / 30).toInt()
    }

    fun getPlntCharan(pl: Double): Int {
        val aa: Double
        //val a: Double
        //val b: Double
        val charan: Int
        aa = pl
        //a = aa / 30
        //b = aa * 3 / 40
        charan = (4 * fract(aa * 3.0 / 40)).toInt() + 1
        return charan
    }

    fun fract(x: Double): Double {
        val i: Long
        val y: Double
        i = x.toLong()
        y = x - i
        return y
    }

    fun getRasiNakSubSub(
        d: Double, RasiLord: Array<String>,
        NakLord: Array<String>
    ): String {
        val y1 = IntArray(12)
        y1.set(0, 7)
        y1.set(1, 20)
        y1.set(2, 6)
        y1.set(3, 10)
        y1.set(4, 7)
        y1.set(5, 18)
        y1.set(6, 16)
        y1.set(7, 19)
        y1.set(8, 17)

        var d = d
        val sb = StringBuilder()
        var a: Int
        var b: Int
        var c: Int
        val f: Int
        var i = 0
        /*
         * if(d<0) d *=-1;
         */f = (d / 30.0).toInt()
        a = (d / 120.0).toInt()
        d -= a * 120.0
        a = (d * 3.0 / 40.0).toInt()
        d -= a * 40.0 / 3.0
        d *= 9.0
        b = 0
        while (b < 9) {
            i = a + b
            if (i >= 9) i -= 9
            d -= if (y1.get(i) <= d) y1.get(i) else break
            b++
        }
        b = i
        d = d / y1.get(b) * (40.0 / 3.0)
        d *= 9.0
        c = 0
        while (c < 9) {
            i = b + c
            if (i >= 9) i -= 9
            d -= if (y1.get(i) <= d) y1.get(i) else break
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

    private fun hasInHouse(cusp2: Double, cusp1: Double, plntDegree: Double): Boolean {
        var temp2 = cusp2
        val s = ""
        if (temp2 - cusp1 < 0) temp2 += 360.00
        if (cusp1 < plntDegree + 360.0 && plntDegree + 360.0 < temp2) {
            return true
        }
        return cusp1 < plntDegree && plntDegree < temp2
    }

    private fun getIntArrayFromString(planetPositionStr: String, lagnaPos: Int): IntArray {
        val rashiInpla = planetPositionStr.split(",").toTypedArray()
        val intArray = IntArray(13)
        val lagna = rashiInpla[lagnaPos].toInt()
        for (i in 0 until rashiInpla.size - 1) {
            intArray[i] = rashiInpla[i + 1].toInt()
        }
        intArray[rashiInpla.size - 1] = lagna
        return intArray
    }

    fun getLaganKundliArray(): IntArray {
        return getIntArrayFromString(arrayList[0].lagna, 0)
    }

    fun getNavmanshKundliArray(): IntArray {
        return getIntArrayFromString(arrayList[0].navmansh, 0)
    }

    fun getChandraKundliArray(): IntArray {
        return getIntArrayFromString(arrayList[0].lagna, 2)
    }

    fun getChalitChartArray(): IntArray {
        var lagna = 0
        val planetInRashi = IntArray(13)
        val cuspsDegreeArray: DoubleArray = getCuspsDegreeArray()
        val planetDegreeArray = getPlanetDegreeArray()
        val lagnaDegree = planetDegreeArray[0]
        var lagnaVal = cuspsDegreeArray[0] + 1.00
        if (lagnaVal > 360.00) lagnaVal -= 360.00
        var count: Int
        count = 0
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

    fun getPlanetsData(context: Context): ArrayList<BasicKundliPlanetData> {
        val plaDeg = getPlanetDegreeArray()
        val degSign = context.getResources().getString(R.string.degree_sign)
        val minSign = context.getResources().getString(R.string.minute_sign)
        val secSign = context.getResources().getString(R.string.second_sign)
        val plaName = context.getResources().getStringArray(R.array.planet_and_lagna_name_list)
        val sign = context.getResources().getStringArray(R.array.rasi_short_name_list)
        val naksh = context.getResources().getStringArray(R.array.nakshatra_short_name_list)
        val arrayList = ArrayList<BasicKundliPlanetData>()
        for (i in 0..12) {
            arrayList.add(
                BasicKundliPlanetData(
                    plaName = plaName[i],
                    sign = sign[getRasiNumber(plaDeg[i])],
                    degree = FormatDMSInStringWithSign(plaDeg[i] % 30, degSign, minSign, secSign),
                    naks = naksh[getNakshatraNumber(plaDeg[i])],
                    plaCharan = "(" + getPlntCharan(plaDeg[i]) + ")"
                )
            )
            Log.i(
                "Planet Data",
                plaName[i] + "-" + sign[getRasiNumber(plaDeg[i])] + "-" + FormatDMSInStringWithSign(
                    plaDeg[i] % 30,
                    degSign,
                    minSign,
                    secSign
                ) + "-" + naksh[getNakshatraNumber(plaDeg[i])] + "(" + getPlntCharan(plaDeg[i]) + ")"
            );
        }
        return arrayList;
    }

    fun getPlanetsSubData(context: Context): ArrayList<BasicKundliPlanetSubData> {
        val plaDeg = getPlanetDegreeArray()
        val degSign = context.getResources().getString(R.string.degree_sign)
        val minSign = context.getResources().getString(R.string.minute_sign)
        val secSign = context.getResources().getString(R.string.second_sign)
        val plaName = context.getResources().getStringArray(R.array.planet_and_lagna_name_list)
        val rashiLord = context.getResources().getStringArray(R.array.rasi_lord_short_name_list)
        val nakshLord = context.getResources().getStringArray(R.array.nak_lord_short_name_list)

        val arrayList = ArrayList<BasicKundliPlanetSubData>()
        for (i in 0..12) {
            val rasiNakSubSub = getRasiNakSubSub(plaDeg[i], rashiLord, nakshLord)
            val arr = rasiNakSubSub.split("-")
            val rashi = arr[0]
            val naks = arr[1]
            val sub = arr[2]
            val subsub = arr[3]
            arrayList.add(
                BasicKundliPlanetSubData(
                    plaName = plaName[i],
                    plaDeg = FormatDMSInStringWithSign(plaDeg[i], degSign, minSign, secSign),
                    signLord = rashi,
                    nakshLord = naks,
                    subLord = sub,
                    subSubLord = subsub
                )
            )

            Log.i(
                "Planet Data",
                plaName[i] + "-" + FormatDMSInStringWithSign(
                    plaDeg[i],
                    degSign,
                    minSign,
                    secSign
                ) + "-" + getRasiNakSubSub(plaDeg[i], rashiLord, nakshLord)
            )
        }
        return arrayList;
    }

    fun getPanchangData(): BasicKundliPanchangBean {
        return BasicKundliPanchangBean(
            paksha = arrayList[0].paksha,
            tithi = arrayList[0].tithi,
            nakshatra = arrayList[0].nakshatra,
            hinduDay = arrayList[0].hinduWeekDay,
            engDay = arrayList[0].englishWeekDay,
            yoga = arrayList[0].yoga,
            karan = arrayList[0].karan,
            sunRiseTime = arrayList[0].sunRiseTime,
            sunSetTime = arrayList[0].sunSetTime
        )
    }

    fun getAshtakVargaData(): ArrayList<String> {
        val arrayList = ArrayList<String>()
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar1)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar2)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar3)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar4)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar5)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar6)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar7)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar8)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar9)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar10)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar11)
        arrayList.add(BasicKundliCalculation.arrayList[0].ashtakvargar12)
        return arrayList
    }

    fun getKarakamshaKundliArray() {

    }
}