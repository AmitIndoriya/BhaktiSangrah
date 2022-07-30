package com.bhakti_sangrahalay.ui.dialogs

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.bhakti_sangrahalay.ui.activity.BirthDetaiInputActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

object TimePickerDialog {
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    fun showTimePicker(context: Context, fragmentManager: FragmentManager) {
        this.context = context
        val calendar = Calendar.getInstance()
        val materialTimePicker: MaterialTimePicker = MaterialTimePicker.Builder()
            .setTitleText("SELECT YOUR TIMING")
            .setHour(calendar[Calendar.HOUR])
            .setMinute(calendar[Calendar.MINUTE])
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .build()
        materialTimePicker.show(fragmentManager, "MainActivity")
        materialTimePicker.addOnPositiveButtonClickListener {

            val pickedHour: Int = materialTimePicker.hour
            val pickedMinute: Int = materialTimePicker.minute
            var pickedAmPm: Int = 0

            when {
                pickedHour > 12 -> {
                    pickedAmPm = 1
                }
                pickedHour == 12 -> {
                    pickedAmPm = 1
                }
                pickedHour == 0 -> {
                    pickedAmPm = 0
                }
                else -> {
                    pickedAmPm = 0
                }
            }
            (context as BirthDetaiInputActivity).setTime(pickedHour, pickedMinute, pickedAmPm)
        }
    }
}