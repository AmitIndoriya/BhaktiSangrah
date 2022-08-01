package com.bhakti_sangrahalay.viewmodel

import android.content.Context
import android.content.res.AssetManager
import com.bhakti_sangrahalay.kundli.calculation.BasicKundliCalculation
import com.bhakti_sangrahalay.kundli.calculation.KpKundliCalculation
import com.bhakti_sangrahalay.kundli.model.*
import com.bhakti_sangrahalay.panchang.generator.GenerateKundliData
import com.bhakti_sangrahalay.util.Parser

class KundliOutputActivityViewModel : BaseViewModel() {


    // arrayList = parser.parseKundliData(GenerateKundliData.getPlanets(this));


    fun getKundliDataList(assetManager: AssetManager, birthDetailBean: BirthDetailBean) {
        val parser = Parser()
        val arrayList = parser.parseKundliData(
            GenerateKundliData.getPlanets(
                assetManager, birthDetailBean
            )
        )
        BasicKundliCalculation.setData(arrayList)
        KpKundliCalculation.setData(arrayList)
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

    fun getDrekkanaArray(): IntArray {
        return BasicKundliCalculation.getDrekkanaArray()
    }

    fun getChaturthamanshArray(): IntArray {
        return BasicKundliCalculation.getChaturthamanshArray()
    }

    fun getSaptamamshaArray(): IntArray {
        return BasicKundliCalculation.getSaptamamshaArray()
    }

    fun getShashtiamshaArray(): IntArray {
        return BasicKundliCalculation.getShashtiamshaArray()
    }

    fun getNavmanshArray(): IntArray {
        return BasicKundliCalculation.getNavmanshArray()
    }

    fun getDashamamshaArray(): IntArray {
        return BasicKundliCalculation.getDashamamshaArray()
    }

    fun getShodashamshaArray(): IntArray {
        return BasicKundliCalculation.getShodashamshaArray()
    }

    fun getVimshamshaArray(): IntArray {
        return BasicKundliCalculation.getVimshamshaArray()
    }

    fun getSaptavimshamshaArray(): IntArray {
        return BasicKundliCalculation.getSaptavimshamshaArray()
    }

    fun getChaturvimshamshaArray(): IntArray {
        return BasicKundliCalculation.getChaturvimshamshaArray()
    }

    fun getTrimshamshaArray(): IntArray {
        return BasicKundliCalculation.getTrimshamshaArray()
    }

    fun getKhavedamshaArray(): IntArray {
        return BasicKundliCalculation.getKhavedamshaArray()
    }

    fun getAkshvedamshaArray(): IntArray {
        return BasicKundliCalculation.getAkshvedamshaArray()
    }

    //Kp system
    fun getKPDegreeArray(): DoubleArray {
        return KpKundliCalculation.getKpDegreeArray()
    }

    fun getKPKundliPlanetRashiArray(): IntArray {
        return KpKundliCalculation.getKpChartArray()
    }

    fun getKPLagnaRashiKundliPlanetsRashiArray(): IntArray {
        return KpKundliCalculation.getKPLagnaRashiKundliPlanetsRashiArray()
    }
}