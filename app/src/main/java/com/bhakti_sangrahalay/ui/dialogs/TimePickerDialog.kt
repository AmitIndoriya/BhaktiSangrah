package com.bhakti_sangrahalay.ui.dialogs

import android.annotation.SuppressLint
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.bhakti_sangrahalay.ui.activity.BirthDetailInputActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

object TimePickerDialog {
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    fun showTimePicker(
        context: Context,
        fragmentManager: FragmentManager,
        hour: Int,
        minute: Int
    ) {
        this.context = context
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
            (context as BirthDetailInputActivity).setTime(pickedHour, pickedMinute, pickedAmPm)
        }
    }
}