package com.bhakti_sangrahalay.viewmodel

import android.content.Context
import android.content.res.AssetManager
import com.bhakti_sangrahalay.kundli.calculation.BasicKundliCalculation
import com.bhakti_sangrahalay.kundli.model.*
import com.bhakti_sangrahalay.panchang.generator.GenerateKundliData
import com.bhakti_sangrahalay.util.Parser

class KundliOutputActivityViewModel : BaseViewModel() {


    // arrayList = parser.parseKundliData(GenerateKundliData.getPlanets(this));


    fun getKundliDataList(assetManager: AssetManager) {
        val parser = Parser()
        BasicKundliCalculation.setData(
            parser.parseKundliData(
                GenerateKundliData.getPlanets(
                    assetManager
                )
            )
        )
    }

    fun getLagnaKundliPlanetRashiArray(): IntArray {
        return BasicKundliCalculation.getLaganKundliArray()
    }

    fun getNavmanshKundliPlanetRashiArray(): IntArray {
        return BasicKundliCalculation.getNavmanshKundliArray()
    }

    fun getChandraKundliPlanetRashiArray(): IntArray {
        return BasicKundliCalculation.getChandraKundliArray()
    }

    fun getChalitKundliPlanetRashiArray(): IntArray {
        return BasicKundliCalculation.getChalitChartArray()
    }

    fun getKpDegreeArray(): DoubleArray {
        return BasicKundliCalculation.getKpDegreeArray()
    }

    fun getPlanetsData(context: Context): ArrayList<BasicKundliPlanetData> {
        return BasicKundliCalculation.getPlanetsData(context)
    }

    fun getPlanetsSubData(context: Context): ArrayList<BasicKundliPlanetSubData> {
        return BasicKundliCalculation.getPlanetsSubData(context)
    }

    fun getPanchangData(): BasicKundliPanchangBean {
        return BasicKundliCalculation.getPanchangData()
    }

    fun getAshtakvargaData(): ArrayList<String> {
        return BasicKundliCalculation.getAshtakVargaData()
    }

    fun getKarakanshLagna(): Int {
        return BasicKundliCalculation.getKarakanshLagna()
    }

    fun getPrastharashtakvargaData(): ArrayList<PrastharashtakvargaBean> {
        return BasicKundliCalculation.getPrastharashtakvargaData()
    }

    fun getAvkahadaChakraData(): BasicAvkahadaChakraBean {
        return BasicKundliCalculation.getAvkahadaChakraData()
    }
}