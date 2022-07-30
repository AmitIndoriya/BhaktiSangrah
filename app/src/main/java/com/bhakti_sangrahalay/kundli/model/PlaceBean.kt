package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class PlaceBean(
    val place: String,
    val longDeg: String,
    val longMin: String,
    val longEW: String,
    val latDeg: String,
    val latMin: String,
    val latNS: String,
    val timeZone: String
) : Serializable
