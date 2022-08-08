package com.bhakti_sangrahalay.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bhakti_sangrahalay.database.db.AppDatabase
import com.bhakti_sangrahalay.database.entity.BirthDetailInfo
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.kundli.model.DateTimeBean
import com.bhakti_sangrahalay.kundli.model.PlaceBean
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class BirthDetaiInputActivityViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var database: AppDatabase
    val birthDetailBeanListLiveData = MutableLiveData<ArrayList<BirthDetailBean>>()
    val isNewBirthDetailInfoAdded = MutableLiveData<Boolean>()

    @OptIn(DelicateCoroutinesApi::class)
    fun insertBirthDetailInfo(birthDetailBean: BirthDetailBean) {
        GlobalScope.launch(Dispatchers.IO) {
            val list = ArrayList<BirthDetailInfo>()
            list.add(
                getBirthDetailInfoFromBirthDetailBean(birthDetailBean)
            )
            database.BirthDetailInfoDao().insertAll(list)
            isNewBirthDetailInfoAdded.postValue(true)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getBirthDetailInfoList() {
        GlobalScope.launch(Dispatchers.IO) {
            val list = database.BirthDetailInfoDao().getAll()
            val birthDetailBeanList = ArrayList<BirthDetailBean>()
            for (element in list) {
                birthDetailBeanList.add(getBirthDetailBeanFromBirthDetailInfo(element))
            }
            birthDetailBeanListLiveData.postValue(birthDetailBeanList)
            Log.i("list>>", "" + list.size)
        }
    }

    private fun getBirthDetailBeanFromBirthDetailInfo(birthDetailInfo: BirthDetailInfo): BirthDetailBean {
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

    private fun getBirthDetailInfoFromBirthDetailBean(birthDetailBean: BirthDetailBean): BirthDetailInfo {
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
}