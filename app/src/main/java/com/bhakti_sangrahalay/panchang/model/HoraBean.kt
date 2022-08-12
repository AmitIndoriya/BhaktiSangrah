package com.bhakti_sangrahalay.panchang.model

import java.io.Serializable

data class HoraBean(
        var planetName: String,
        var enterTime: String,
        var exitTime: String,
        var planetMeaning: String,
        var planetCurrentHoraMeaning: String,
        var duration: String,
        /* var doghatiSecondMeaning: String,
         var doghatiSecondMeaningwikipedia: String,
         var doghatimuhurat: String*/

): Serializable