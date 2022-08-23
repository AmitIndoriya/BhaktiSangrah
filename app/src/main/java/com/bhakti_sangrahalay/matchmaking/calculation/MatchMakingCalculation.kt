package com.bhakti_sangrahalay.matchmaking.calculation

import android.util.Log
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.kundli.model.DateTimeBean
import com.bhakti_sangrahalay.kundli.model.PlaceBean
import com.bhakti_sangrahalay.matchmaking.model.GunaListBean
import com.bhakti_sangrahalay.matchmaking.model.MatchMakingResultBean
import com.indoriya.horolib.matchmilan.MatchMaking
import com.indoriya.horolib.util.Constants
import java.io.InputStream

object MatchMakingCalculation {

    fun getMatchMakingResult(
        boyBirthDetailBean: BirthDetailBean,
        girlBirthDetailBean: BirthDetailBean
    ): MatchMakingResultBean {
        val inputStream: InputStream =
            MyApp.applicationContext().assets.open("Properties/ConstantsEnglish.properties")
        val objConst = Constants()
        objConst.setInputStream(inputStream)
        val boyDesktopHoro = MMBaseCalculation.gerDesktopHoro(boyBirthDetailBean)
        val girlDesktopHoro = MMBaseCalculation.gerDesktopHoro(girlBirthDetailBean)
        boyDesktopHoro.initialize()
        girlDesktopHoro.initialize()
        inputStream.close()
        val matchMakingGunaList =
            MyApp.applicationContext().resources.getStringArray(R.array.match_making_guna_list)
        val areaOfLife = MyApp.applicationContext().resources.getStringArray(R.array.area_of_life)
        val matchMaking = MatchMaking()
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
            matchMaking.matchGrahaMitraGuna(boyDesktopHoro.rasi, girlDesktopHoro.rasi)
        )
        recievedList.add(
            matchMaking.matchGanaGuna(boyDesktopHoro.moon, girlDesktopHoro.moon).toDouble()
        )
        recievedList.add(
            matchMaking.matchBhakutGuna(boyDesktopHoro.rasi, girlDesktopHoro.rasi).toDouble()
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