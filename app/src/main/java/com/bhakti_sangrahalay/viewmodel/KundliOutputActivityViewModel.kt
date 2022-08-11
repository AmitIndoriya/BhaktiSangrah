package com.bhakti_sangrahalay.viewmodel

import android.content.Context
import android.content.res.AssetManager
import com.bhakti_sangrahalay.kundli.calculation.*
import com.bhakti_sangrahalay.kundli.model.*
import com.bhakti_sangrahalay.model.HouseSignificatorsBean
import com.bhakti_sangrahalay.model.PlanetSignificationBean
import com.bhakti_sangrahalay.panchang.generator.GenerateKundliData
import com.bhakti_sangrahalay.util.Parser
import javax.inject.Inject

class KundliOutputActivityViewModel @Inject constructor() : BaseViewModel() {


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
        BaseCalculation.setData(arrayList)
        KpCilSubSubcalculation.setData(arrayList)
        KpCilSubCalculation.setData(arrayList)
        DashaCalculation.setData(arrayList)

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

    fun getKPPlanetsData(context: Context): ArrayList<BasicKundliPlanetSubData> {
        return KpKundliCalculation.getKPPlanetsData(context)
    }

    fun getKPCuspData(context: Context): ArrayList<BasicKundliPlanetSubData> {
        return KpKundliCalculation.getKPCuspData(context)
    }

    fun getKPPlanetSignificationData(context: Context): ArrayList<PlanetSignificationBean> {
        return KpKundliCalculation.getKPPlanetSignificationData(context)
    }

    fun getKPHouseSignificatorsData(context: Context): ArrayList<HouseSignificatorsBean> {
        return KpKundliCalculation.getKPHouseSignificatorsData(context)
    }

    fun getPlanetSignifiactionView2Data(): ArrayList<PlanetSignificationView2Bean> {
        return KPPlanetSignificationView2Calculation.getPlanetSignifiactionView2()
    }

    fun getNakshtraNadiData(context: Context): ArrayList<KPNakshatraNadiBean> {
        return KpNakshtraNadiCalculation.getNakshtraNadi(context)
    }

    fun getCilSubSubData(context: Context): ArrayList<KPCilSubSubBean> {
        return KpCilSubSubcalculation.getCilSubSubData(context)
    }

    fun getCilSubData(context: Context): ArrayList<KPCilSubBean> {
        return KpCilSubCalculation.getCilSubData()
    }

}