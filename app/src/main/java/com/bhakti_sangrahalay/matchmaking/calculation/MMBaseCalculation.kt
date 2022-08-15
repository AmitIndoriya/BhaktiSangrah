package com.bhakti_sangrahalay.matchmaking.calculation

import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.indoriya.horolib.dhoro.DesktopHoro

object MMBaseCalculation {
    fun gerDesktopHoro(birthDetailBean: BirthDetailBean): DesktopHoro {
        val placeBean = birthDetailBean.placeBean
        val dateTimeBean = birthDetailBean.dateTimeBean
        val desktopHoro = DesktopHoro()
        desktopHoro.name = birthDetailBean.name

        desktopHoro.maleFemale = birthDetailBean.sex
        desktopHoro.secondOfBirth = dateTimeBean.sec
        desktopHoro.minuteOfBirth = dateTimeBean.min
        desktopHoro.hourOfBirth = dateTimeBean.hrs
        desktopHoro.setDayOfBirth(dateTimeBean.day)
        desktopHoro.setMonthOfBirth(dateTimeBean.month)
        desktopHoro.setYearOfBirth(dateTimeBean.year)

        desktopHoro.place = placeBean.place
        desktopHoro.timeZone = placeBean.timeZone

        desktopHoro.degreeOfLattitude = placeBean.latDeg
        desktopHoro.minuteOfLattitude = placeBean.latMin
        desktopHoro.secondOfLattitude = "00"
        desktopHoro.northSouth = placeBean.latNS

        desktopHoro.degreeOfLongitude = placeBean.longDeg
        desktopHoro.minuteOfLongitude = placeBean.longMin
        desktopHoro.secondOfLongitude = "00"
        desktopHoro.eastWest = placeBean.longEW

        desktopHoro.languageCode = birthDetailBean.languageCode
        desktopHoro.dst = birthDetailBean.dst
        desktopHoro.ayanamsaType = 0
        return desktopHoro
    }
}