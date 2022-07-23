package com.bhakti_sangrahalay.model

import java.io.Serializable

class PlaceModel(val id: Int, val city: String, val state: String, val latitude: Double, val longitude: Double, val country: String, val timezone: String) :Serializable