package com.bhakti_sangrahalay.panchang.calculations

import com.bhakti_sangrahalay.panchang.model.BhadraBean
import com.bhakti_sangrahalay.util.Utility
import com.indoriya.panchang.Place
import java.util.*

object BhardraCalculation {

    fun getBhadraData(calendar: Calendar, place: Place): ArrayList<BhadraBean> {
        val bhadraTimelist = ArrayList<BhadraBean>()
        val days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1..days) {
            if (PanchangCalculation.isVishtiKaran(calendar, place)) {
                val bhadraList = PanchangCalculation.getBhadraData(calendar, place)
                val karanStartDate = bhadraList[2].toDouble()
                if (karanStartDate == -1.0) {
                    calendar.add(Calendar.DATE, -1)
                }
                val startDate = "" + calendar.get(Calendar.DATE)
                val startMonth = Utility.getMonthList()[calendar.get(Calendar.MONTH)]
                val startDay = Utility.getWeekList()[calendar.get(Calendar.DAY_OF_WEEK) - 1]
                val startTime = bhadraList[0]
                if (karanStartDate == -1.0) {
                    calendar.add(Calendar.DATE, 1)
                }
                var endDate: String
                var endMonth: String
                var endDay: String
                var endTime: String

                if (bhadraList[1].contains("+")) {
                    calendar.add(Calendar.DATE, 1)
                    endDate = ("" + calendar.get(Calendar.DATE))
                    endMonth = Utility.getMonthList()[calendar.get(Calendar.MONTH)]
                    endDay = Utility.getWeekList()[calendar.get(Calendar.DAY_OF_WEEK) - 1]
                    endTime = bhadraList[1].replace("+", "")
                    calendar.add(Calendar.DATE, -1)
                } else {
                    endDate = "" + calendar.get(Calendar.DATE)
                    endMonth = Utility.getMonthList()[calendar.get(Calendar.MONTH)]
                    endDay = Utility.getWeekList()[calendar.get(Calendar.DAY_OF_WEEK) - 1]
                    endTime = bhadraList[1]
                }




                bhadraTimelist.add(BhadraBean(startDay = startDay,
                        startMonth = startMonth,
                        startDate = startDate,
                        startTime = startTime,
                        endDay = endDay,
                        endMonth = endMonth,
                        endDate = endDate,
                        endTime = endTime))
            }
            calendar.add(Calendar.DATE, 1)
        }
        return bhadraTimelist
    }
}