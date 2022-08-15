package com.bhakti_sangrahalay.viewmodel

import androidx.lifecycle.MutableLiveData
import com.bhakti_sangrahalay.database.entity.BirthDetailInfo
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.kundli.model.DateTimeBean
import com.bhakti_sangrahalay.kundli.model.PlaceBean

open class BirthDetailInputBaseViewModel : BaseViewModel() {
    val isNewBirthDetailInfoAdded = MutableLiveData<Boolean>()
    val birthDetailBeanListLiveData = MutableLiveData<ArrayList<BirthDetailBean>>()

    fun getBirthDetailInfoFromBirthDetailBean(birthDetailBean: BirthDetailBean): BirthDetailInfo {
        return BirthDetailInfo(
            name = birthDetailBean.name,
            sex = birthDetailBean.sex,
            day = birthDetailBean.dateTimeBean.day,
            month = birthDetailBean.dateTimeBean.month,
            year = birthDetailBean.dateTimeBean.year,
            hrs = birthDetailBean.dateTimeBean.hrs,
            min = birthDetailBean.dateTimeBean.min,
            sec = birthDetailBean.dateTimeBean.sec,
            place = birthDetailBean.placeBean.place,
            longDeg = birthDetailBean.placeBean.longDeg,
            longMin = birthDetailBean.placeBean.longMin,
            longEW = birthDetailBean.placeBean.longEW,
            latDeg = birthDetailBean.placeBean.latDeg,
            latMin = birthDetailBean.placeBean.latMin,
            latNS = birthDetailBean.placeBean.latNS,
            timeZone = birthDetailBean.placeBean.timeZone,
            dst = birthDetailBean.dst,
            ayanamsa = birthDetailBean.ayanamsa,
            charting = birthDetailBean.charting,
            kphn = birthDetailBean.kphn,
            button1 = birthDetailBean.button1,
            languageCode = birthDetailBean.languageCode
        )
    }

    fun getBirthDetailBeanFromBirthDetailInfo(birthDetailInfo: BirthDetailInfo): BirthDetailBean {
        val dateTimeBean = DateTimeBean(
            day = birthDetailInfo.day,
            month = birthDetailInfo.month,
            year = birthDetailInfo.year,
            hrs = birthDetailInfo.hrs,
            min = birthDetailInfo.min,
            sec = birthDetailInfo.sec
        )
        val placeBean = PlaceBean(
            place = birthDetailInfo.place,
            latDeg = birthDetailInfo.latDeg,
            latMin = birthDetailInfo.latMin,
            latNS = birthDetailInfo.latNS,
            longDeg = birthDetailInfo.longDeg,
            longMin = birthDetailInfo.longMin,
            longEW = birthDetailInfo.longEW,
            timeZone = birthDetailInfo.timeZone
        )
        return BirthDetailBean(
            name = birthDetailInfo.name,
            sex = birthDetailInfo.sex,
            dst = birthDetailInfo.dst,
            ayanamsa = birthDetailInfo.ayanamsa,
            kphn = birthDetailInfo.kphn,
            button1 = birthDetailInfo.button1,
            charting = birthDetailInfo.charting,
            languageCode = birthDetailInfo.languageCode,
            placeBean = placeBean,
            dateTimeBean = dateTimeBean
        )
    }
}