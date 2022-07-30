package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class DateTimeBean(
    var day: String,
    var month: String,
    var year: String,
    var hrs: String,
    var min: String,
    var sec: String
): Serializable
