package com.bhakti_sangrahalay.kundli.calculation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.kundli.model.CharAntaraDashaBean
import com.bhakti_sangrahalay.kundli.model.CharDashaBean
import com.bhakti_sangrahalay.model.KundliBean
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object CharDashaCalculation {
    @SuppressLint("StaticFieldLeak")
    var context: Context = MyApp.applicationContext()
    lateinit var arrayList: ArrayList<KundliBean>
    private val charDasaSeqMap = HashMap<Int, Array<Int>>()
    private val planetRashiArray = ArrayList<Int>()
    private var rashiLordMap = HashMap<Int, String>()
    private val planetRashiMap = HashMap<String, Int>()
    private var orderOfCalculationMap = HashMap<Int, Int>()
    private val planetNameArray = arrayOf(
        "Sun",
        "Moon",
        "Mars",
        "Mercury",
        "Jupiter",
        "Venus",
        "Saturn",
        "Rahu",
        "Ketu",
        "Uranus",
        "Neptune",
        "Pluto"
    )

    //
    private val horoscopeName = arrayOf(
        getString(R.string.Aries),
        getString(R.string.Taurus),
        getString(R.string.Gemini),
        getString(R.string.Cancer),
        getString(R.string.Leo),
        getString(R.string.Virgo),
        getString(R.string.Libra),
        getString(R.string.Scorpio),
        getString(R.string.Sagittarius),
        getString(R.string.Capricorn),
        getString(R.string.Aquarius),
        getString(R.string.Pisces)
    )


    //private val charDasaSeqForGemini = arrayOf("Gemini", "Taurus", "Aries", "Pisces", "Aquarius", "Capricorn", "Sagittarius", "Scorpio", "Libra", "Virgo", "Leo", "Cancer")
    //private val charDasaSeqForCancer = arrayOf("Cancer", "Gemini", "Taurus", "Aries", "Pisces", "Aquarius", "Capricorn", "Sagittarius", "Scorpio", "Libra", "Virgo", "Leo")
    //private val charDasaSeqForLeo = arrayOf("Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo")
    //private val charDasaSeqForVirgo = arrayOf("Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer", "Leo")
    //private val charDasaSeqForLibra = arrayOf("Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo")
    //private val charDasaSeqForScorpio = arrayOf("Scorpio", "Libra", "Virgo", "Leo", "Cancer", "Gemini", "Taurus", "Aries", "Pisces", "Aquarius", "Capricorn", "Sagittarius")
    //private val charDasaSeqForSagittarius = arrayOf("Sagittarius", "Scorpio", "Libra", "Virgo", "Leo", "Cancer", "Gemini", "Taurus", "Aries", "Pisces", "Aquarius", "Capricorn")
    //private val charDasaSeqForCapricorn = arrayOf("Capricorn", "Sagittarius", "Scorpio", "Libra", "Virgo", "Leo", "Cancer", "Gemini", "Taurus", "Aries", "Pisces", "Aquarius")
    //private val charDasaSeqForAquarius = arrayOf("Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn")
    //private val charDasaSeqForPisces = arrayOf("Pisces", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius")


    //private val charDasaSeqForAquarius = arrayOf("Aquarius", "Pisces", "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn")


    private val charDasaSeqForAries = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private val charDasaSeqForTaurus = arrayOf(2, 1, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3)
    private val charDasaSeqForGemini = arrayOf(3, 2, 1, 12, 11, 10, 9, 8, 7, 6, 5, 4)
    private val charDasaSeqForCancer = arrayOf(4, 3, 2, 1, 12, 11, 10, 9, 8, 7, 6, 5)
    private val charDasaSeqForLeo = arrayOf(7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6)
    private val charDasaSeqForVirgo = arrayOf(6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5)
    private val charDasaSeqForLibra = arrayOf(6, 7, 8, 9, 11, 12, 1, 2, 3, 4, 5, 6)
    private val charDasaSeqForScorpio = arrayOf(8, 7, 6, 5, 4, 3, 2, 1, 12, 11, 10, 9)
    private val charDasaSeqForSagittarius = arrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 12, 11, 10)
    private val charDasaSeqForCapricorn = arrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 12, 11)
    private val charDasaSeqForAquarius = arrayOf(11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private val charDasaSeqForPisces = arrayOf(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)


    init {
        setRashiLord()
        setOrderOfCalculation()
        setCharDasaSeqMap()
    }

    private fun setCharDasaSeqMap() {
        charDasaSeqMap[1] = charDasaSeqForAries
        charDasaSeqMap[2] = charDasaSeqForTaurus
        charDasaSeqMap[3] = charDasaSeqForGemini
        charDasaSeqMap[4] = charDasaSeqForCancer
        charDasaSeqMap[5] = charDasaSeqForLeo
        charDasaSeqMap[6] = charDasaSeqForVirgo
        charDasaSeqMap[7] = charDasaSeqForLibra
        charDasaSeqMap[8] = charDasaSeqForScorpio
        charDasaSeqMap[9] = charDasaSeqForSagittarius
        charDasaSeqMap[10] = charDasaSeqForCapricorn
        charDasaSeqMap[11] = charDasaSeqForAquarius
        charDasaSeqMap[12] = charDasaSeqForPisces

    }

    private fun getPlanetDegreeArray(): DoubleArray {
        val planetDegree = arrayList[0].planetDegree.split(",".toRegex()).toTypedArray()
        val degree = DoubleArray(13)
        for (i in planetDegree.indices) {
            degree[i] = planetDegree[i].toDouble()
        }
        return degree
    }

    private fun setRashiLord() {
        rashiLordMap = HashMap()
        rashiLordMap[1] = "Mars"
        rashiLordMap[2] = "Venus"
        rashiLordMap[3] = "Mercury"
        rashiLordMap[4] = "Moon"
        rashiLordMap[5] = "Sun"
        rashiLordMap[6] = "Mercury"
        rashiLordMap[7] = "Venus"
        rashiLordMap[8] = "Ketu"//Mars
        rashiLordMap[9] = "Jupiter"
        rashiLordMap[10] = "Saturn"
        rashiLordMap[11] = "Saturn"
        rashiLordMap[12] = "Jupiter"
    }

    private fun setOrderOfCalculation() {
        orderOfCalculationMap = HashMap()
        orderOfCalculationMap[1] = 0
        orderOfCalculationMap[2] = 0
        orderOfCalculationMap[3] = 0
        orderOfCalculationMap[4] = 1
        orderOfCalculationMap[5] = 1
        orderOfCalculationMap[6] = 1
        orderOfCalculationMap[7] = 0
        orderOfCalculationMap[8] = 0
        orderOfCalculationMap[9] = 0
        orderOfCalculationMap[10] = 1
        orderOfCalculationMap[11] = 1
        orderOfCalculationMap[12] = 1
    }

    fun setData(arrayList: ArrayList<KundliBean>) {
        this.arrayList = arrayList
    }

    fun getCharDashaData(dob: String): ArrayList<CharDashaBean> {
        calculatePlanetRashi()
        return calculateCharDashaDuration(dob)
    }

    private fun calculatePlanetRashi() {
        val rashiInpla = arrayList[0].lagna.split(",".toRegex()).toTypedArray()
        for (i in planetNameArray.indices) {
            planetRashiMap[planetNameArray[i]] = rashiInpla[i + 1].toInt()
            planetRashiArray.add(rashiInpla[i + 1].toInt())
            Log.i(" planetRashi=", planetNameArray[i] + "=" + planetRashiMap[planetNameArray[i]])
        }
    }

    private fun calculateCharDashaDuration(dob: String): ArrayList<CharDashaBean> {
        var startDate = dob
        var endDate: String
        val arrayList = ArrayList<CharDashaBean>()
        val charDasaSeqArray = charDasaSeqMap[2]
        for (i in 0..11) {
            val rashi = charDasaSeqArray?.get(i)
            val calcOrder = orderOfCalculationMap[rashi]
            val rashiLord = rashiLordMap[rashi]
            var rashiOfLord = planetRashiMap[rashiLord]
            if (rashi == 8) {
                rashiOfLord = calculateRashiForScorpioLord()
            } else if (rashi == 11) {
                rashiOfLord = calculateRashiForAquariusLord()
            }
            var duration = 0
            Log.i(
                "calc-Detail",
                "- Rashi-$rashi- calcOrder-$calcOrder- rashiLord-$rashiLord- rashiOfLord-$rashiOfLord"
            )
            if (rashi == rashiOfLord) {
                duration = 12
            } else if (calcOrder == 0) {
                if (rashiOfLord != null) {
                    duration = rashi?.let { clockwiseMove(it, rashiOfLord) }!!
                }
            } else {
                if (rashiOfLord != null) {
                    duration = rashi?.let { antiClockwiseMove(it, rashiOfLord) }!!
                }
            }
            endDate = getEndDate(startDate, duration)
            val planet = charDasaSeqArray?.get(i)
            val planetName = horoscopeName[planet!! - 1]
            val charAntaraDashaList = getCharAntaraDasha(charDasaSeqArray[i], startDate, duration)
            arrayList.add(
                CharDashaBean(
                    planetName = planetName,
                    duration = duration,
                    startYear = startDate,
                    endYear = endDate,
                    charAntaraDashaList = charAntaraDashaList
                )
            )

            //Log.i("calc-Duration", "=$planetName,$duration, $startDate-$endDate")
            startDate = endDate

        }
        return arrayList
    }

    private fun calculateRashiForScorpioLord(): Int {
        var lordRashi = -1
        val rashiLord1 = "Ketu"
        val rashiLord2 = "Mars"
        val rashiOfLord1 = planetRashiMap[rashiLord1]
        val rashiOfLord2 = planetRashiMap[rashiLord2]
        if (rashiOfLord1 == 8 && rashiOfLord2 == 8) {
            lordRashi = rashiOfLord1
        } else if (rashiOfLord1 == 8) {
            if (rashiOfLord2 != null) {
                lordRashi = rashiOfLord2
            }
        } else if (rashiOfLord2 == 8) {
            if (rashiOfLord1 != null) {
                lordRashi = rashiOfLord1
            }
        } else {
            var noOfPlanetInKetuRahi = 0
            var noOfPlanetInMarsRahi = 0
            for (i in 0 until planetRashiArray.size) {
                if (planetRashiArray[i] == rashiOfLord1) {
                    noOfPlanetInKetuRahi++
                } else if (planetRashiArray[i] == rashiOfLord2) {
                    noOfPlanetInMarsRahi++
                }
            }
            if (noOfPlanetInKetuRahi > noOfPlanetInMarsRahi) {
                if (rashiOfLord1 != null) {
                    lordRashi = rashiOfLord1
                }
            } else if (noOfPlanetInKetuRahi < noOfPlanetInMarsRahi) {
                if (rashiOfLord2 != null) {
                    lordRashi = rashiOfLord2
                }
            } else {
                val degreeArray = getPlanetDegreeArray()
                if (degreeArray[2] > degreeArray[8]) {
                    if (rashiOfLord2 != null) {
                        lordRashi = rashiOfLord2
                    }
                } else {
                    if (rashiOfLord1 != null) {
                        lordRashi = rashiOfLord1
                    }
                }
            }

        }

        return lordRashi
    }

    private fun calculateRashiForAquariusLord(): Int {
        var lordRashi = -1
        val rashiLord1 = "Rahu"
        val rashiLord2 = "Saturn"
        val rashiOfLord1 = planetRashiMap[rashiLord1]
        val rashiOfLord2 = planetRashiMap[rashiLord2]
        if (rashiOfLord1 == 11 && rashiOfLord2 == 11) {
            lordRashi = rashiOfLord1
        } else if (rashiOfLord1 == 11) {
            if (rashiOfLord2 != null) {
                lordRashi = rashiOfLord2
            }
        } else if (rashiOfLord2 == 11) {
            if (rashiOfLord1 != null) {
                lordRashi = rashiOfLord1
            }
        } else {
            var noOfPlanetInRahuRashi = 0
            var noOfPlanetInSaturnRashi = 0
            for (i in 0 until planetRashiArray.size) {
                if (planetRashiArray[i] == rashiOfLord1) {
                    noOfPlanetInRahuRashi++
                } else if (planetRashiArray[i] == rashiOfLord2) {
                    noOfPlanetInSaturnRashi++
                }
            }
            if (noOfPlanetInRahuRashi > noOfPlanetInSaturnRashi) {
                if (rashiOfLord1 != null) {
                    lordRashi = rashiOfLord1
                }
            } else if (noOfPlanetInRahuRashi < noOfPlanetInSaturnRashi) {
                if (rashiOfLord2 != null) {
                    lordRashi = rashiOfLord2
                }
            } else {
                val degreeArray = getPlanetDegreeArray()
                if (degreeArray[6] > degreeArray[7]) {
                    if (rashiOfLord2 != null) {
                        lordRashi = rashiOfLord2
                    }
                } else {
                    if (rashiOfLord1 != null) {
                        lordRashi = rashiOfLord1
                    }
                }
            }

        }

        return lordRashi
    }

    private fun clockwiseMove(rashi: Int, lordRashi: Int): Int {
        var count = 0
        var boolVal = true
        var rashiIndex = rashi
        while (boolVal) {
            if (rashiIndex == 12) {
                rashiIndex = 1
            } else {
                rashiIndex++
            }
            if (lordRashi == rashiIndex) {
                boolVal = false
            }
            count++
        }
        return count
    }

    private fun antiClockwiseMove(rashi: Int, lordRashi: Int): Int {
        var count = 0
        var boolVal = true
        var rashiIndex = rashi
        while (boolVal) {
            if (rashiIndex == 1) {
                rashiIndex = 12
            } else {
                rashiIndex--
            }
            if (lordRashi == rashiIndex) {
                boolVal = false
            }
            count++
        }
        return count
    }

    fun getString(id: Int): String {
        return context.resources.getString(id)
    }

    private fun getEndDate(startDate: String, duration: Int): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = dateFormat.parse(startDate)
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.YEAR, duration)
        return dateFormat.format(calendar.time)
    }


    fun getCharAntaraDasha(pla: Int, sDate: String, duration: Int): ArrayList<CharAntaraDashaBean> {
        val arrayList = ArrayList<CharAntaraDashaBean>()
        val charDasaSeqArray = charDasaSeqMap[pla]
        var startDate = sDate
        var endDate = ""
        if (charDasaSeqArray != null) {
            for (i in 1 until charDasaSeqArray.size) {
                val planet = charDasaSeqArray[i]
                val planetName = horoscopeName[planet - 1]
                endDate = getEndDateForCharAntaraDasha(startDate, duration)
                arrayList.add(
                    CharAntaraDashaBean(
                        planetName = planetName,
                        startDate = startDate,
                        endDate = endDate,
                    )
                )
                Log.i("calc-Duration", "=$planetName,$duration, $startDate-$endDate")
                startDate = endDate
            }
        }
        val planet = charDasaSeqArray?.get(0)
        val planetName = horoscopeName[planet?.minus(1)!!]
        endDate = getEndDateForCharAntaraDasha(startDate, duration)
        arrayList.add(
            CharAntaraDashaBean(
                planetName = planetName,
                startDate = startDate,
                endDate = endDate,
            )
        )
        Log.i("calc-Duration", "=$planetName,$duration, $startDate-$endDate")
        return arrayList

    }

    fun getEndDateForCharAntaraDasha(startDate: String, duration: Int): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = dateFormat.parse(startDate)
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.MONTH, duration)
        return dateFormat.format(calendar.time)
    }
}