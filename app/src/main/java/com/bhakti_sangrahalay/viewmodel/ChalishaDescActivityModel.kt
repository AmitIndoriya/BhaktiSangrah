package com.bhakti_sangrahalay.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.contansts.Constants
import com.bhakti_sangrahalay.util.Parser
import com.bhakti_sangrahalay.util.Utility
import java.util.ArrayList
import javax.inject.Inject

class ChalishaDescActivityModel @Inject constructor() : ViewModel() {
    var resources: Resources? = null
    var utility: Utility = Utility()
    var parser: Parser = Parser()
    var url: String = Utility.getStoragePath()

    fun readResourceFile() {
        val aartiBeanArrayList =
            parser.aartiListParserNew(Utility.readFromFile(resources, R.raw.chalisa_list))
        MyApp.applicationContext().dataHoler.setAartiArrayList(
            aartiBeanArrayList,
            Constants.CHALISHA_TYPE
        )
    }

    fun getImages(): ArrayList<Int> {
        val intArr = Utility.getIntArray(resources, R.array.chalish_icon_list)
        val intList = ArrayList<Int>(intArr.size)
        for (i in intArr) {
            intList.add(i)
        }
        return intList
    }

    fun getIdList(): IntArray? {
        return resources?.getIntArray(R.array.chalisha_ids)
    }
}