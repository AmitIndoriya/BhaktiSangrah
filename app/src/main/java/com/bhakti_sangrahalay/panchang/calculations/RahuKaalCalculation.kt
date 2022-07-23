package com.bhakti_sangrahalay.panchang.calculations

import android.util.Log
import com.bhakti_sangrahalay.panchang.model.RahuKaalBean
import com.bhakti_sangrahalay.panchang.util.PanchangUtility
import com.indoriya.panchang.Place
import java.util.*

object RahuKaalCalculation {

    fun getRahuKaalData(calendar: Calendar, place: Place): ArrayList<RahuKaalBean> {
        val arrayList = ArrayList<RahuKaalBean>()
        try {
            val cal = Calendar.getInstance()
            cal.time = calendar.time
            for (i in 0..7) {
                val date = PanchangUtility.getDateShowRahuKaal(cal.time)
                val rahuKaalTime = PanchangCalculation.getRahuKaalVela(cal, place)
                arrayList.add(RahuKaalBean(date, rahuKaalTime[0], rahuKaalTime[1]))
                cal.add(Calendar.DATE, 1)
            }


        } catch (ex: Exception) {
            Log.i("e", "" + ex.message)
        }
        return arrayList
    }

    fun getCurrentRahuKaal( place: Place): RahuKaalBean {
        val calendar = Calendar.getInstance()
        val duration = PanchangCalculation.getRahuKaalVela(calendar, place)
        return RahuKaalBean(PanchangUtility.getDateShowRahuKaal(calendar.time), duration[0], duration[1])

    }
}