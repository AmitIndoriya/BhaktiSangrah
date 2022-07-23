package com.bhakti_sangrahalay.panchang.util

import android.content.res.Resources
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.panchang.calculations.PanchangCalculation
import com.bhakti_sangrahalay.panchang.model.PanchangBean
import com.bhakti_sangrahalay.panchang.model.PanchangModel
import com.bhakti_sangrahalay.util.Utility
import java.util.*

object PanchagObject {
    private lateinit var name: Array<String>
    lateinit var value: Array<String>
    lateinit var time: Array<String?>
    private val panchangModel: PanchangModel = PanchangCalculation.getPanchangModel()
    private var todayPanchangModel: PanchangModel

    init {
        todayPanchangModel = panchangModel
    }

    fun preparePanchangData(resources: Resources): ArrayList<PanchangBean> {
        val arrayList: ArrayList<PanchangBean> = ArrayList<PanchangBean>()
        name = arrayOf(resources.getString(R.string.tithi),
                resources.getString(R.string.nakshatra),
                resources.getString(R.string.karana),
                resources.getString(R.string.paksha),
                resources.getString(R.string.yoga),
                resources.getString(R.string.day))
        value = arrayOf<String>(panchangModel.tithiValue,
                panchangModel.nakshatraValue,
                panchangModel.karanaValue,
                panchangModel.pakshaName,
                panchangModel.yogaValue,
                panchangModel.vaara)
        val karanTimeArr: List<String> = panchangModel.getKaranaTime().split("\n")
        val karanTime: String = Utility.convertTimeToAmPm(karanTimeArr[0]).toString() + ",\n" + Utility.convertTimeToAmPm(karanTimeArr[1]).replace("+", "Tomorrow\n")
        time = arrayOf(Utility.convertTimeToAmPm(panchangModel.getTithiTime()),
                Utility.convertTimeToAmPm(panchangModel.getNakshatraTime()),
                Utility.convertTimeToAmPm(karanTime),
                "",
                Utility.convertTimeToAmPm(panchangModel.getYogaTime()),
                "")
        var panchangBean: PanchangBean
        for (i in name.indices) {
            panchangBean = PanchangBean(name.get(i), value.get(i), time.get(i))
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun prepareSunAndMoonData(resources: Resources): ArrayList<PanchangBean> {
        val arrayList = ArrayList<PanchangBean>()
        name = arrayOf(resources.getString(R.string.sun_rises),
                resources.getString(R.string.moon_rises),
                resources.getString(R.string.moon_sign),
                resources.getString(R.string.sun_set),
                resources.getString(R.string.moon_set),
                resources.getString(R.string.ritu))
        value = arrayOf<String>(Utility.convertTimeToAmPm(panchangModel.getSunRise()),
                Utility.convertTimeToAmPm(panchangModel.getMoonRise()),
                panchangModel.getMoonSignValue(),
                Utility.convertTimeToAmPm(panchangModel.getSunSet()),
                Utility.convertTimeToAmPm(panchangModel.getMoonSet()),
                panchangModel.getRitu())
        time = arrayOf("",
                "",
                panchangModel.getMoonSignTime(),
                "",
                "",
                "")
        var panchangBean: PanchangBean
        for (i in name.indices) {
            panchangBean = PanchangBean(name[i], value.get(i), time.get(i))
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun prepareHinduMonthAndYearData(resources: Resources): ArrayList<PanchangBean>? {
        val arrayList = ArrayList<PanchangBean>()
        name = arrayOf(resources.getString(R.string.shaka_samvat),
                resources.getString(R.string.kali_samvat),
                resources.getString(R.string.day_duration),
                resources.getString(R.string.vikram_samvat),
                resources.getString(R.string.month_amanta),
                resources.getString(R.string.month_purnimanta))

        //Toast.makeText(this, model.getShakaSamvat(), Toast.LENGTH_SHORT).show();
        value = arrayOf(panchangModel.getShakaSamvatYear(),
                panchangModel.getKaliSamvat(),
                panchangModel.getDayDuration(),
                panchangModel.getVikramSamvat(),
                panchangModel.getMonthAmanta(),
                panchangModel.getMonthPurnimanta())
        time = arrayOf(panchangModel.getShakaSamvatName(),
                "",
                "",
                "",
                "",
                "")
        var panchangBean: PanchangBean
        for (i in name.indices) {
            panchangBean = PanchangBean(name[i], value[i], time[i])
            arrayList.add(panchangBean)
        }
        return arrayList
    }
    fun prepareAuspiciousData(resources: Resources): ArrayList<PanchangBean>? {
        val arrayList = ArrayList<PanchangBean>()
        name = arrayOf(resources.getString(R.string.abhijit))
        value = arrayOf(Utility.convertTimeToAmPm(panchangModel.getAbhijitFrom()))
        time = arrayOf(Utility.convertTimeToAmPm(panchangModel.getAbhijitTo()))
        var panchangBean: PanchangBean
        for (i in name.indices) {
            panchangBean = PanchangBean(name[i], value[i], time[i])
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun prepareInAuspiciousData(resources: Resources): ArrayList<PanchangBean>? {
        val arrayList = ArrayList<PanchangBean>()
        name = arrayOf(resources.getString(R.string.dushta_muhurtas),
                resources.getString(R.string.kantaka),
                resources.getString(R.string.yamaghanta),
                resources.getString(R.string.rahu_kaal),
                resources.getString(R.string.kulika),
                resources.getString(R.string.kalavela),
                resources.getString(R.string.yamaganda),
                resources.getString(R.string.gulika_kaal))
        value = arrayOf(Utility.convertTimeToAmPm(panchangModel.getDushtaMuhurtasFrom()),
                Utility.convertTimeToAmPm(panchangModel.getKantaka_MrityuFrom()),
                Utility.convertTimeToAmPm(panchangModel.getYamaghantaFrom()),
                Utility.convertTimeToAmPm(panchangModel.rahuKaalVelaFrom),
                Utility.convertTimeToAmPm(panchangModel.getKulikaFrom()),
                Utility.convertTimeToAmPm(panchangModel.getKalavela_ArdhayaamFrom()),
                Utility.convertTimeToAmPm(panchangModel.getYamagandaVelaFrom()),
                Utility.convertTimeToAmPm(panchangModel.getGulikaKaalVelaFrom()))
        time = arrayOf(Utility.convertTimeToAmPm(panchangModel.getDushtaMuhurtasTo()),
                Utility.convertTimeToAmPm(panchangModel.getKantaka_MrityuTo()),
                Utility.convertTimeToAmPm(panchangModel.getYamaghantaTo()),
                Utility.convertTimeToAmPm(panchangModel.getRahuKaalVelaTo()),
                Utility.convertTimeToAmPm(panchangModel.getKulikaTo()),
                Utility.convertTimeToAmPm(panchangModel.getKalavela_ArdhayaamTo()),
                Utility.convertTimeToAmPm(panchangModel.getYamagandaVelaTo()),
                Utility.convertTimeToAmPm(panchangModel.getGulikaKaalVelaTo()))
        var panchangBean: PanchangBean
        for (i in name.indices) {
            panchangBean = PanchangBean(name[i], value[i], time[i])
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun prepareDishaShoolData(resources: Resources): ArrayList<PanchangBean>? {
        val arrayList = ArrayList<PanchangBean>()
        val name = arrayOf(resources.getString(R.string.disha_shoola))
        val value = arrayOf<String>(panchangModel.getDishaShoola())
        time = arrayOf(panchangModel.getAbhijitTo())
        var panchangBean: PanchangBean
        for (i in name.indices) {
            panchangBean = PanchangBean(name[i], value[i], "")
            arrayList.add(panchangBean)
        }
        return arrayList
    }

    fun prepareTaraBalaData(): String? {
        return panchangModel.getTaraBala()
    }

    fun prepareChandraBalaData(): String? {
        return panchangModel.getChandraBala()
    }

}