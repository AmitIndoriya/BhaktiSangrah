package com.bhakti_sangrahalay.panchang.model

import java.io.Serializable

data class DoGhatiBean(
        var planetName: String,
        var enterTime: String,
        var exitTime: String,
        var planetMeaning: String,
        var planetCurrentHoraMeaning: String,
        var doGhatiSecondMeaning: String,
        var doGhatiSecondMeaningWikipedia: String,
        var doGhatiMuhurat: String,
        var duration: String
): Serializable