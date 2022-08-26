package com.bhakti_sangrahalay.matchmaking.calculation

import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.kundli.model.DateTimeBean
import com.bhakti_sangrahalay.kundli.model.PlaceBean
import com.bhakti_sangrahalay.matchmaking.model.GunaListBean
import com.bhakti_sangrahalay.matchmaking.model.MatchMakingResultBean
import com.indoriya.horolib.dhoro.DesktopHoro
import com.indoriya.horolib.matchmilan.MatchMaking
import com.indoriya.horolib.util.Constants
import java.io.InputStream

object MatchMakingCalculation {
    lateinit var boyDesktopHoro: DesktopHoro
    lateinit var girlDesktopHoro: DesktopHoro
    lateinit var matchMaking: MatchMaking
    fun getMatchMakingResult(
        boyBirthDetailBean: BirthDetailBean,
        girlBirthDetailBean: BirthDetailBean
    ): MatchMakingResultBean {
        val inputStream: InputStream =
            MyApp.applicationContext().assets.open("Properties/ConstantsEnglish.properties")
        val objConst = Constants()
        objConst.setInputStream(inputStream)
        boyDesktopHoro = MMBaseCalculation.gerDesktopHoro(boyBirthDetailBean)
        girlDesktopHoro = MMBaseCalculation.gerDesktopHoro(girlBirthDetailBean)
        boyDesktopHoro.initialize()
        girlDesktopHoro.initialize()
        inputStream.close()
        val matchMakingGunaList =
            MyApp.applicationContext().resources.getStringArray(R.array.match_making_guna_list)
        val areaOfLife = MyApp.applicationContext().resources.getStringArray(R.array.area_of_life)
        matchMaking = MatchMaking()
        val recievedList = ArrayList<Double>()
        recievedList.add(
            matchMaking.matchVarnaGuna(boyDesktopHoro.rasi, girlDesktopHoro.rasi).toDouble()
        )
        recievedList.add(
            matchMaking.matchVashyaGuna(boyDesktopHoro.moon, girlDesktopHoro.moon)
        )
        recievedList.add(
            matchMaking.matchTaraGuna(boyDesktopHoro.moon, girlDesktopHoro.moon)
        )
        recievedList.add(
            matchMaking.matchYoniGuna(boyDesktopHoro.moon, girlDesktopHoro.moon).toDouble()
        )
        recievedList.add(
            matchMaking.matchGrahaMitraGuna(boyDesktopHoro.rasi + 1, girlDesktopHoro.rasi + 1)
        )
        recievedList.add(
            matchMaking.matchGanaGuna(boyDesktopHoro.moon, girlDesktopHoro.moon).toDouble()
        )
        recievedList.add(
            matchMaking.matchBhakutGuna(boyDesktopHoro.rasi + 1, girlDesktopHoro.rasi + 1)
                .toDouble()
        )
        recievedList.add(
            matchMaking.matchNadiGuna(boyDesktopHoro.moon, girlDesktopHoro.moon).toDouble()
        )
        val maxList = ArrayList<Int>()
        maxList.add(matchMaking.maximumVarna)
        maxList.add(matchMaking.maximumVashya)
        maxList.add(matchMaking.maximumTara)
        maxList.add(matchMaking.maximumYoni)
        maxList.add(matchMaking.maximumGrahaMaitri)
        maxList.add(matchMaking.maximumGana)
        maxList.add(matchMaking.maximumBhakoot)
        maxList.add(matchMaking.maximumNadi)

        val total = matchMaking.total
        val gunaList = ArrayList<GunaListBean>()

        for (i in 0 until maxList.size) {
            gunaList.add(
                GunaListBean(
                    gunName = matchMakingGunaList[i],
                    maxVal = maxList[i],
                    received = recievedList[i],
                    areaOfLife = areaOfLife[i]
                )
            )
        }

        return MatchMakingResultBean(
            total = total,
            boyHasMangalDosh = boyDesktopHoro.CalcMangalDosh(),
            girlHasMangalDosh = girlDesktopHoro.CalcMangalDosh(),
            gunaList = gunaList
        )

    }


    fun getVarna(): IntArray {
        val array = IntArray(2)
        val varna = intArrayOf(1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0)
        array[0] = varna[boyDesktopHoro.rasi]
        array[1] = varna[girlDesktopHoro.rasi]
        return array
    }

    fun getVasya(): IntArray {
        val array = IntArray(2)
        array[0] = matchMaking.calculateVashya(boyDesktopHoro.moon)
        array[1] = matchMaking.calculateVashya(girlDesktopHoro.moon)
        return array
    }

    fun getYoni(): IntArray {
        val array = IntArray(2)
        array[0] = matchMaking.calculateYoni(boyDesktopHoro.moon)
        array[1] = matchMaking.calculateYoni(girlDesktopHoro.moon)
        return array
    }

    fun getGana(): IntArray {
        val array = IntArray(2)
        array[0] = matchMaking.calculateGana(boyDesktopHoro.moon)
        array[1] = matchMaking.calculateGana(girlDesktopHoro.moon)
        return array
    }

    fun getNadi(): IntArray {
        val array = IntArray(2)
        array[0] = matchMaking.calculateNadi(boyDesktopHoro.moon)
        array[1] = matchMaking.calculateNadi(girlDesktopHoro.moon)
        return array
    }

    private fun getBoyBirthDetail(): BirthDetailBean {
        return BirthDetailBean(
            name = "Amit",
            sex = "M",
            dateTimeBean = DateTimeBean(
                day = "13",
                month = "8",
                year = "2004",
                hrs = "18",
                min = "30",
                sec = "00",
            ),
            placeBean = PlaceBean(
                place = "Agra",
                longDeg = "078",
                longMin = "00",
                longEW = "E",
                latDeg = "027",
                latMin = "09",
                latNS = "N",
                timeZone = "5.5"
            ),
            dst = "0",
            ayanamsa = "0",
            charting = "0",
            kphn = "0",
            button1 = "Get+Kundali",
            languageCode = "0",
        )

    }

    private fun getGirlBirthDetail(): BirthDetailBean {
        return BirthDetailBean(
            name = "Amit",
            sex = "M",
            dateTimeBean = DateTimeBean(
                day = "13",
                month = "8",
                year = "1994",
                hrs = "18",
                min = "30",
                sec = "00",
            ),
            placeBean = PlaceBean(
                place = "Agra",
                longDeg = "078",
                longMin = "00",
                longEW = "E",
                latDeg = "027",
                latMin = "09",
                latNS = "N",
                timeZone = "5.5"
            ),
            dst = "0",
            ayanamsa = "0",
            charting = "0",
            kphn = "0",
            button1 = "Get+Kundali",
            languageCode = "0",
        )
    }
}