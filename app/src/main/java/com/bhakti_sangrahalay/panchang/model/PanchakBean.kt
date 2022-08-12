package com.bhakti_sangrahalay.panchang.model

import java.io.Serializable


data class PanchakBean(val month: String, val year: String, val panchakTimeList: ArrayList<PanchakTimeBean>):
    Serializable