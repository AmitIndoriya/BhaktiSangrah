package com.bhakti_sangrahalay.panchang.model

import java.io.Serializable

class TodayPanchangModel(
        val tithiInt: Int,
        val tithi: String,
        val hindiMonth: String,
        val samvat: String,
        val vara: String,
        val paksh: String
): Serializable