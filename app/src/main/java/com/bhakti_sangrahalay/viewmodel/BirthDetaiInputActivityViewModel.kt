package com.bhakti_sangrahalay.viewmodel

import android.util.Log
import com.bhakti_sangrahalay.database.db.AppDatabase
import com.bhakti_sangrahalay.database.entity.BirthDetailInfo
import javax.inject.Inject

class BirthDetaiInputActivityViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var database: AppDatabase
    fun insertBirthDetailInfo() {
        val list = ArrayList<BirthDetailInfo>()
        list.add(
            BirthDetailInfo(
                id = 1,
                name = "rh",
                sex = "thrth",
                day = "gfh",
                month = "gh",
                year = "fgh",
                hrs = "fgh",
                min = "fh",
                sec = "f",
                place = "gfh",
                longDeg = "g",
                longMin = "gfh",
                longEW = "fgh",
                latDeg = "gfh",
                latMin = "fgh",
                latNS = "fgh",
                timeZone = "fgh",
                dst = "gfh",
                ayanamsa = "fgh",
                charting = "gfh",
                kphn = "fh",
                button1 = "fgh",
                languageCode = "fgh"
            )
        )
        database.BirthDetailInfoDao().insertAll(list)
        Log.i("", "")
    }

    fun getBirthDetailInfoList() {
        val list = database.BirthDetailInfoDao().getAll()
        Log.i("", "")
    }
}