package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class PlaceBean(
    val place: String,
    val LongDeg: String,
    val LongMin: String,
    val LongEW: String,
    val LatDeg: String,
    val LatMin: String,
    val LatNS: String,
    val timeZone: String
):Serializable
