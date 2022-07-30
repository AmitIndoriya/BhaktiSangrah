package com.bhakti_sangrahalay.ui.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.DatePicker
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.ui.activity.BirthDetaiInputActivity
import java.util.*

object DatePickerDialog {
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    fun showDatePicker(context: Context) {
        this.context = context
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            context,
            R.style.date_picker_style,
            DateSetListener(),
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DATE]
        )
        datePicker.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePicker.setCancelable(false)
        datePicker.setTitle("Select the date")
        datePicker.show()
    }

    class DateSetListener : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(p0: DatePicker?, year: Int, month: Int, date: Int) {
            (context as BirthDetaiInputActivity).setDate(date, month, year)

        }

    }
}