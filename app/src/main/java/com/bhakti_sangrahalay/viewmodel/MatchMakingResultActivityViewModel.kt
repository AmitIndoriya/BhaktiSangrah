package com.bhakti_sangrahalay.viewmodel

import androidx.lifecycle.MutableLiveData
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.matchmaking.calculation.MatchMakingCalculation
import com.bhakti_sangrahalay.matchmaking.model.MatchMakingResultBean
import javax.inject.Inject

class MatchMakingResultActivityViewModel @Inject constructor() : BaseViewModel() {
    val matchMakingResultLiveData = MutableLiveData<MatchMakingResultBean>()

    fun getMatchMakingResult(
        boyBirthDetailBean: BirthDetailBean,
        girlBirthDetailBean: BirthDetailBean
    ) {
        matchMakingResultLiveData.value = MatchMakingCalculation.getMatchMakingResult(
            boyBirthDetailBean,
            girlBirthDetailBean
        )
    }


}