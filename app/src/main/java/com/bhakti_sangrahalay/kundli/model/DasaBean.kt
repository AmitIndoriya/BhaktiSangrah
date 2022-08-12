package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class DasaBean(var planetNo: Int = 0,
                    var planetName: String = "",
                    val day: Int = 0,
                    val month: Int = 0,
                    val year: Int = 0,
                    var dasaTime: Double = 0.0,
                    var dasaTimeStr: String = ""): Serializable