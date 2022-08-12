package com.bhakti_sangrahalay.panchang.model

import java.io.Serializable

data class BhadraBean(
        val startDay: String,
        val startMonth: String,
        val startDate: String,
        val startTime: String,
        val endDay: String,
        val endMonth: String,
        val endDate: String,
        val endTime: String,
): Serializable