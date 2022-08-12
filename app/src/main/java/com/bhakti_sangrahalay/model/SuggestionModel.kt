package com.bhakti_sangrahalay.model

import java.io.Serializable

data class SuggestionModel(val name: String, val phoneNumber: String, val emailAddress: String, val suggestion: String):
    Serializable