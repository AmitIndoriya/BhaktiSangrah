package com.bhakti_sangrahalay.ui.dialogs

import androidx.fragment.app.FragmentManager
import com.bhakti_sangrahalay.R
import com.google.android.material.datepicker.MaterialDatePicker


object MaterialDatePickerDialog {

    fun showDatePicker(fragmentManager: FragmentManager) {
        val materialDateBuilder = MaterialDatePicker.Builder.datePicker()
        materialDateBuilder.setTitleText("SELECT A DATE")
        //materialDateBuilder.setTheme(R.style.MyDatePicker)
        val materialDatePicker = materialDateBuilder.build()
        materialDatePicker.show(fragmentManager, "MATERIAL_DATE_PICKER")
        materialDatePicker.addOnPositiveButtonClickListener {

            //mShowSelectedDateText.setText("Selected Date is : " + materialDatePicker.headerText)

        }
    }
}