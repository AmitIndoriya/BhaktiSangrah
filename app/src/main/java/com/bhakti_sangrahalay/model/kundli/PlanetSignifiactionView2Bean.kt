package com.bhakti_sangrahalay.model.kundli

import java.io.Serializable

data class PlanetSignifiactionView2Bean(
    val plaNo: Int,
    val l1: Int,
    val l2: Int,
    val l3: IntArray?,
    val l4: IntArray?,
    val strength: Int
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlanetSignifiactionView2Bean

        if (plaNo != other.plaNo) return false
        if (l1 != other.l1) return false
        if (l2 != other.l2) return false
        if (l3 != null) {
            if (other.l3 == null) return false
            if (!l3.contentEquals(other.l3)) return false
        } else if (other.l3 != null) return false
        if (l4 != null) {
            if (other.l4 == null) return false
            if (!l4.contentEquals(other.l4)) return false
        } else if (other.l4 != null) return false
        if (strength != other.strength) return false

        return true
    }

    override fun hashCode(): Int {
        var result = plaNo
        result = 31 * result + l1
        result = 31 * result + l2
        result = 31 * result + (l3?.contentHashCode() ?: 0)
        result = 31 * result + (l4?.contentHashCode() ?: 0)
        result = 31 * result + strength
        return result
    }
}