package com.bhakti_sangrahalay.panchang.model

import java.io.Serializable

data class ChogdiyaBean(var planetName: String,
                        var enterTime: String,
                        var exitTime: String,
                        var planetMeaning: String,
                        val duration: String): Serializable