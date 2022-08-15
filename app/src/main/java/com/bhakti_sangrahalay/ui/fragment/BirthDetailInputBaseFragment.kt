package com.bhakti_sangrahalay.ui.fragment

abstract class BirthDetailInputBaseFragment : BaseFragment() {
    abstract fun setDate(day: Int, month: Int, year: Int)
    abstract fun setTime(hour: Int, minute: Int, am_pm: Int)
    abstract fun setPlace()
}