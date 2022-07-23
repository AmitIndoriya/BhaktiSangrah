package com.bhakti_sangrahalay.model

import java.io.Serializable
import java.util.*

class TodayPanchangModelNew(val tithiInt: Int,
                            val tithi: String,
                            val hindiMonth: String,
                            val samvat: String,
                            val vara: String,
                            val paksh: String,
                            val calendar: Calendar):Serializable