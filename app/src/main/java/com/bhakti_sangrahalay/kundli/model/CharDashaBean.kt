package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable
import java.time.Duration

data class CharDashaBean(
    val planetName: String,
    val duration: Int,
    val startYear: String,
    val endYear: String,
    val charAntaraDashaList: ArrayList<CharAntaraDashaBean>
) : Serializable
