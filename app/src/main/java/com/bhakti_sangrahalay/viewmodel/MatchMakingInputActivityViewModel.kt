package com.bhakti_sangrahalay.viewmodel

import android.util.Log
import com.bhakti_sangrahalay.database.entity.BirthDetailInfo
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MatchMakingInputActivityViewModel @Inject constructor() : BirthDetailInputBaseViewModel() {
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

    @OptIn(DelicateCoroutinesApi::class)
    fun insertBirthDetailInfo(
        boyBirthDetailBean: BirthDetailBean,
        girlBirthDetailBean: BirthDetailBean
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            val list = ArrayList<BirthDetailInfo>()
            list.add(
                getBirthDetailInfoFromBirthDetailBean(boyBirthDetailBean)
            )
            list.add(
                getBirthDetailInfoFromBirthDetailBean(girlBirthDetailBean)
            )
            database.BirthDetailInfoDao().insertAll(list)
            isNewBirthDetailInfoAdded.postValue(true)
        }
    }
}