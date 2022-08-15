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

class BirthDetaiInputActivityViewModel @Inject constructor() : BirthDetailInputBaseViewModel() {



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

}