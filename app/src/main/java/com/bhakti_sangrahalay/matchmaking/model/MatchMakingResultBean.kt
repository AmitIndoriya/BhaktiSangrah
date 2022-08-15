package com.bhakti_sangrahalay.matchmaking.model

data class MatchMakingResultBean(
    val total: Double,
    val isBoyHasMangalDosh: Boolean,
    val isGirlHasMangalDosh: Boolean,
    val gunaList: ArrayList<GunaListBean>
)
