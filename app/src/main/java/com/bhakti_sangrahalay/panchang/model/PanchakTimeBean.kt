package com.bhakti_sangrahalay.panchang.model

import java.io.Serializable

class PanchakTimeBean(val startDate: String,
                      val endDate: String,
                      val startDay: String,
                      val endDay: String,
                      val startTime: String,
                      val endTime: String,
                      val startMonth: String,
                      val endMonth: String): Serializable
