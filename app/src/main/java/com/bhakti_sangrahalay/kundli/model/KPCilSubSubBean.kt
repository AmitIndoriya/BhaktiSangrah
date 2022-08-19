package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class KPCilSubSubBean(
    val planetName: String,
    val star: String,
    val sub: String,
    val subSub: String,
    val posStatus: String
) : Serializable