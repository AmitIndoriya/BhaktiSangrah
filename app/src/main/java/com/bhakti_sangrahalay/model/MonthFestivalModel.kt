package com.bhakti_sangrahalay.model

import java.io.Serializable

data class MonthFestivalModel(val month: String, val festivalList: ArrayList<FestivalModel>) : Serializable