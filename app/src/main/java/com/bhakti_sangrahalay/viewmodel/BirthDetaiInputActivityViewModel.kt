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

}