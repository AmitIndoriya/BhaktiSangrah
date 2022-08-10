package com.bhakti_sangrahalay.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.contansts.Constants
import com.bhakti_sangrahalay.util.Parser
import com.bhakti_sangrahalay.util.Utility
import java.util.*
import javax.inject.Inject

class AartiDescActivityViewModel  @Inject constructor(): ViewModel() {
    public var resources: Resources? = null
    var utility: Utility = Utility()
    var parser: Parser = Parser()
    var url: String = Utility.getStoragePath()

    override fun onCleared() {
        super.onCleared()
    }

    fun readResourceFile() {
        var aartiBeanArrayList = parser.aartiListParserNew(utility.readFromFile(resources, R.raw.aarti_list))
        MyApp.applicationContext().dataHoler.setAartiArrayList(aartiBeanArrayList, Constants.AARTI_TYPE)
    }

    public fun getImages(): ArrayList<Int> {
        val intArr = Utility.getIntArray(resources, R.array.god_icon_list)
        val intList = ArrayList<Int>(intArr.size)
        for (i in intArr) {
            intList.add(i)
        }
        return intList
    }

    fun getIdList(): IntArray? {
        return resources?.getIntArray(R.array.aarti_ids)
    }
}