package com.bhakti_sangrahalay.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.matchmaking.calculation.MatchMakingCalculation
import com.bhakti_sangrahalay.matchmaking.model.MatchMakingResultBean
import com.bhakti_sangrahalay.util.Parser
import com.bhakti_sangrahalay.util.Utility
import javax.inject.Inject

class MatchMakingResultActivityViewModel @Inject constructor() : BaseViewModel() {
    val matchMakingResultLiveData = MutableLiveData<MatchMakingResultBean>()
    val interpretationLiveData = MutableLiveData<ArrayList<String>>()


    fun getMatchMakingResult(
        boyBirthDetailBean: BirthDetailBean,
        girlBirthDetailBean: BirthDetailBean
    ) {
        matchMakingResultLiveData.value = MatchMakingCalculation.getMatchMakingResult(
            boyBirthDetailBean,
            girlBirthDetailBean
        )

    }

    fun getInterpretationData(context: Context) {
        val arrayList = ArrayList<String>()
        arrayList.add(getVranaInterpretation(context))
        arrayList.add(getVasyaInterpretation(context))
        arrayList.add(getYoniInterpretation(context))
        arrayList.add(getGanaInterpretation(context))
        arrayList.add(getNadiInterpretation(context))

        interpretationLiveData.value = arrayList
    }

    private fun getVranaInterpretation(context: Context): String {
        val array = MatchMakingCalculation.getVarna()
        val boyVarna = array[0]
        val girlVarna = array[1]
        var intepretation = ""
        val list =
            Parser.parseInterpretation(Utility.readFromFile(context.resources, R.raw.mm_varna))
        for (i in 0 until list.size) {
            val boy = list[i].boy
            val girl = list[i].girl
            val bool =
                ((boy == boyVarna) || (girl == boyVarna)) && ((boy == girlVarna) || (girl == girlVarna))
            if (bool) {
                intepretation = list[i].interpretation
            }
        }
        Log.i("interpretation", intepretation)
        return intepretation
    }

    private fun getVasyaInterpretation(context: Context): String {
        val array = MatchMakingCalculation.getVasya()
        val boyVasya = array[0]
        val girlVasya = array[1]

        var intepretation = ""
        val list =
            Parser.parseInterpretation(Utility.readFromFile(context.resources, R.raw.mm_vasya))
        for (i in 0 until list.size) {
            val boy = list[i].boy
            val girl = list[i].girl
            val bool =
                ((boy == boyVasya) || (girl == boyVasya)) && ((boy == girlVasya) || (girl == girlVasya))
            if (bool) {
                intepretation = list[i].interpretation
                break
            }
        }
        Log.i("interpretation", intepretation)
        return intepretation
    }

    private fun getYoniInterpretation(context: Context): String {
        val array = MatchMakingCalculation.getYoni()
        val boyYoni = array[0]
        val girlYoni = array[1]
        var intepretation = ""
        val list =
            Parser.parseInterpretation(Utility.readFromFile(context.resources, R.raw.mm_yoni))
        for (i in 0 until list.size) {
            val boy = list[i].boy
            val girl = list[i].girl
            val bool =
                ((boy == boyYoni) || (girl == boyYoni)) && ((boy == girlYoni) || (girl == girlYoni))
            if (bool) {
                intepretation = list[i].interpretation
                break
            }
        }
        Log.i("interpretation", intepretation)
        return intepretation
    }

    private fun getGanaInterpretation(context: Context): String {
        val array = MatchMakingCalculation.getGana()
        val boyGana = array[0]
        val girlGana = array[1]
        var intepretation = ""
        val list =
            Parser.parseInterpretation(Utility.readFromFile(context.resources, R.raw.mm_gana))
        for (i in 0 until list.size) {
            val boy = list[i].boy
            val girl = list[i].girl
            val bool =
                ((boy == boyGana) || (girl == boyGana)) && ((boy == girlGana) || (girl == girlGana))
            if (bool) {
                intepretation = list[i].interpretation
                break
            }
        }
        Log.i("interpretation", intepretation)
        return intepretation
    }

    private fun getNadiInterpretation(context: Context): String {
        val array = MatchMakingCalculation.getNadi()
        val boyNadi = array[0]
        val girlNadi = array[1]
        Log.i("","=>"+boyNadi+", "+girlNadi)
        var intepretation = ""
        val list =
            Parser.parseInterpretation(Utility.readFromFile(context.resources, R.raw.mm_nadi))
        for (i in 0 until list.size) {
            val boy = list[i].boy
            val girl = list[i].girl
            if ((boy == boyNadi) && (girl == girlNadi)) {
                intepretation = list[i].interpretation
                break
            }
        }
        return intepretation
    }

}