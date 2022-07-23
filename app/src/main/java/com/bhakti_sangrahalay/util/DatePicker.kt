package com.bhakti_sangrahalay.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.bhakti_sangrahalay.ui.activity.PanchangActivity
import com.google.android.material.datepicker.MaterialDatePicker


class DatePicker {
    @SuppressLint("ShowToast")
    fun showDatePicker(context: Context): MaterialDatePicker<*> {
        val materialDateBuilder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        materialDateBuilder.setTitleText("SELECT A DATE")
        val materialDatePicker = materialDateBuilder.build()
        materialDatePicker.addOnPositiveButtonClickListener {
            if (context is PanchangActivity) {
                (context as PanchangActivity).setDateFromDatePicker(it as Long)
                Log.i("val>>", "" + it);
            }
        }
        return materialDatePicker
    }


}