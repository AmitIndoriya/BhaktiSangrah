package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class BasicKundliPanchangBean(
    val paksha: String,
    val tithi: String,
    val nakshatra: String,
    val hinduDay: String,
    val engDay: String,
    val yoga: String,
    val karan: String,
    val sunRiseTime: String,
    val sunSetTime: String
):Serializable
