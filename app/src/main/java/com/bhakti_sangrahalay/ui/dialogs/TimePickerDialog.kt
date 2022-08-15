package com.bhakti_sangrahalay.ui.dialogs

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bhakti_sangrahalay.ui.activity.BirthDetailInputActivity
import com.bhakti_sangrahalay.ui.fragment.BirthDetailInputBaseFragment
import com.bhakti_sangrahalay.ui.fragment.BirthDetailInputFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

object TimePickerDialog {
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    lateinit var fragment: Fragment
    fun showTimePicker(
        context: Context,
        fragment: Fragment,
        fragmentManager: FragmentManager,
        hour: Int,
        minute: Int
    ) {
        this.context = context
        this.fragment = fragment
        val materialTimePicker: MaterialTimePicker = MaterialTimePicker.Builder()
            .setTitleText("SELECT YOUR TIMING")
            .setHour(hour)
            .setMinute(minute)
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .build()
        materialTimePicker.show(fragmentManager, "MainActivity")
        materialTimePicker.addOnPositiveButtonClickListener {

            val pickedHour: Int = materialTimePicker.hour
            val pickedMinute: Int = materialTimePicker.minute

            val pickedAmPm = when {
                pickedHour > 12 -> {
                    1
                }
                pickedHour == 12 -> {
                    1
                }
                pickedHour == 0 -> {
                    0
                }
                else -> {
                    0
                }
            }
            (fragment as BirthDetailInputBaseFragment).setTime(pickedHour, pickedMinute, pickedAmPm)
        }
    }
}