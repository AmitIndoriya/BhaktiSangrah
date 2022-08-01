package com.bhakti_sangrahalay.inteface

interface CalculationInterface {
    fun formatDMSInStringWithSign(
        fDeg: Double,
        DegSign: String,
        MinSign: String,
        SecSign: String
    ): String

    fun getRashiNakSubSub(
        d: Double,
        RasiLord: Array<String>,
        NakLord: Array<String>
    ): String

    fun hasInHouse(
        cusp2: Double,
        cusp1: Double,
        plntDegree: Double
    ): Boolean
}