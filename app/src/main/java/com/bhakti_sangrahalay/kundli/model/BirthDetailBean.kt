package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class BirthDetailBean(
    var name: String,
    var sex: String,
    var dateTimeBean: DateTimeBean,
    var placeBean: PlaceBean,
    var dst: String,
    var ayanamsa: String,
    var charting: String,
    var kphn: String,
    var button1: String,
    var languageCode: String
) : Serializable
