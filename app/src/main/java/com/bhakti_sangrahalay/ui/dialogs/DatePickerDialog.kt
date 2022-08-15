package com.bhakti_sangrahalay.ui.dialogs

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.ui.activity.BirthDetailInputActivity
import com.bhakti_sangrahalay.ui.activity.BirthDetailInputActivityNew
import com.bhakti_sangrahalay.ui.fragment.BirthDetailInputBaseFragment
import com.bhakti_sangrahalay.ui.fragment.BirthDetailInputFragment

object DatePickerDialog {
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    lateinit var fragment: Fragment
    fun showDatePicker(context: Context, fragment: Fragment, day: Int, month: Int, year: Int) {
        this.context = context
        this.fragment = fragment
        val datePicker = DatePickerDialog(
            context,
            R.style.date_picker_style,
            DateSetListener(),
            year,
            month,
            day

        )
        datePicker.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePicker.setCancelable(false)
        datePicker.setTitle("Select the date")
        datePicker.show()
    }

    class DateSetListener : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(p0: DatePicker?, year: Int, month: Int, date: Int) {
            (fragment as BirthDetailInputBaseFragment).setDate(date, month, year)
        }

    }
}