package com.bhakti_sangrahalay.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.model.PlaceModel
import com.bhakti_sangrahalay.util.Parser
import com.bhakti_sangrahalay.util.Utility

class PlaceViewModel : ViewModel() {
    var parser: Parser = Parser()

    fun getPlaceList(resources: Resources): ArrayList<PlaceModel> {
        return parser.getPlaceList(Utility.readFromFile(resources, R.raw.place_list))
    }

}