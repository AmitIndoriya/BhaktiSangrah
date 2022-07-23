package com.bhakti_sangrahalay.model

import com.bhakti_sangrahalay.panchang.model.TodayPanchangModel
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class DainikCalendarModel(val calendar: Calendar,
                          val todayPanchangModel: TodayPanchangModelNew,
                          val monthFestivalModel: MonthFestivalModel,
                          val daysData: ArrayList<DailyCalendarDayModel>?) : Serializable
