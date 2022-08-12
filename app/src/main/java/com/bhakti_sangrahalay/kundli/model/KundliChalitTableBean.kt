package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class KundliChalitTableBean(val bhav: Int,
                                 val bhBegSign: String,
                                 val bhBegDeg: String,
                                 val bhMidSign: String,
                                 val bhMidDeg: String) : Serializable