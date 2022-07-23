package com.bhakti_sangrahalay.panchang.model

import java.io.Serializable

class PlaceModel : Serializable {
    public val serialVersionUID = 1L

    public val countryID = 91
    public val cityID = 1423
    public val timeZoneID = 32
    public val timeZoneValue = 5.5f
    public val countryName = "India"
    public val stateName = "Rajsathan"
    public val cityName = "Jaipur"
    public val lat: String? = null
    public val latDeg = "27"
    public val latMin = "09"
    public val latSec = "00"
    public val latDir = "N"

    public val lng: String? = null
    public val longDeg = "78"
    public val longMin = "00"
    public val longSec = "00"
    public val longDir = "E"

    public val timeZoneName = "+5.30 IST"
    public val timeZone: String? = null
    public val timeZoneString: String? = null
}