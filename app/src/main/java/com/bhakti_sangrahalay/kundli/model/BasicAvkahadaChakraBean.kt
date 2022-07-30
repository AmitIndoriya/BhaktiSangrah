package com.bhakti_sangrahalay.kundli.model

import java.io.Serializable

data class BasicAvkahadaChakraBean(
    val paya: String,
    val varna: String,
    val yoni: String,
    val gana: String,
    val vasya: String,
    val nadi: String,
    val balanceOfdasha: String,
    val lagna: String,
    val lagnaLord: String,
    val rasi: String,
    val rasiLord: String,
    val NakshatraPada: String,
    val nakshatraLord: String,
    val julianDay: String,
    val sunSignIndian: String,
    val sunSign: String,
    val ayanamsa: String,
    val ayanamsaName: String,
    val obliquity: String,
    val sideralTime: String
):Serializable
