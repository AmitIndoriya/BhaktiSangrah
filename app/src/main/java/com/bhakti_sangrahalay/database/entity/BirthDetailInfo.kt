package com.bhakti_sangrahalay.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bhakti_sangrahalay.kundli.model.DateTimeBean
import com.bhakti_sangrahalay.kundli.model.PlaceBean

@Entity
data class BirthDetailInfo(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "sex") var sex: String,
    @ColumnInfo(name = "day") var day: String,
    @ColumnInfo(name = "month") var month: String,
    @ColumnInfo(name = "year") var year: String,
    @ColumnInfo(name = "hrs") var hrs: String,
    @ColumnInfo(name = "min") var min: String,
    @ColumnInfo(name = "sec") var sec: String,
    @ColumnInfo(name = "place") val place: String,
    @ColumnInfo(name = "longDeg") val longDeg: String,
    @ColumnInfo(name = "longMin") val longMin: String,
    @ColumnInfo(name = "longEW") val longEW: String,
    @ColumnInfo(name = "latDeg") val latDeg: String,
    @ColumnInfo(name = "latMin") val latMin: String,
    @ColumnInfo(name = "latNS") val latNS: String,
    @ColumnInfo(name = "timeZone") val timeZone: String,
    @ColumnInfo(name = "dst") var dst: String,
    @ColumnInfo(name = "ayanamsa") var ayanamsa: String,
    @ColumnInfo(name = "charting") var charting: String,
    @ColumnInfo(name = "kphn") var kphn: String,
    @ColumnInfo(name = "button1") var button1: String,
    @ColumnInfo(name = "languageCode") var languageCode: String
)
