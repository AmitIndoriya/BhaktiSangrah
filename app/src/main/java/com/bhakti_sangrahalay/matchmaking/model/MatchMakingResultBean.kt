package com.bhakti_sangrahalay.matchmaking.model

data class MatchMakingResultBean(
    val total: Double,
    val boyHasMangalDosh: Int,
    val girlHasMangalDosh: Int,
    val gunaList: ArrayList<GunaListBean>
)
