package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class DateTimeBean(
    val day: String,
    val month: String,
    val year: String,
    val hrs: String,
    val min: String,
    val sec: String
): Serializable
