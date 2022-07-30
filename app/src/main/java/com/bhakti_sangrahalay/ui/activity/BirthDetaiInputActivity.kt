package com.bhakti_sangrahalay.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.databinding.ActBirthdetailInputLayoutBinding
import com.bhakti_sangrahalay.ui.dialogs.DatePickerDialog
import com.bhakti_sangrahalay.ui.dialogs.TimePickerDialog
import com.bhakti_sangrahalay.util.Utility
import com.bhakti_sangrahalay.viewmodel.BirthDetaiInputActivityViewModel
import java.util.*


class BirthDetaiInputActivity : BaseActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {
    lateinit var viewModel: BirthDetaiInputActivityViewModel
    private lateinit var binding: ActBirthdetailInputLayoutBinding
    override fun attachViewModel() {
        val viewModelProvider =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
        viewModel = viewModelProvider[BirthDetaiInputActivityViewModel::class.java]
    }

    override fun setTypeface() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActBirthdetailInputLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(resources.getString(R.string.birth_detail))
        setListener()
        setAyanamsaSpinner()
        setDstSpinner()
        popuateData()

    }

    private fun popuateData() {
        val monthShortName = resources.getStringArray(R.array.month_short_name_en)
        val calendar = Calendar.getInstance()
        binding.dateValTv.text =
            calendar[Calendar.DATE].toString() + " - " + monthShortName[calendar[Calendar.MONTH]] + " - " + calendar[Calendar.YEAR]
        binding.timeValTv.text = Utility.getFormattedTime(
            calendar[Calendar.HOUR],
            calendar[Calendar.MINUTE],
            calendar[Calendar.AM_PM]
        )
        binding.placeValTv.text = "Jaipur, Rajasthan, India"
    }

    private fun setListener() {
        binding.calculateBtn.setOnClickListener(this)
        binding.dateValTv.setOnClickListener(this)
        binding.timeValTv.setOnClickListener(this)
        binding.ayanmashaSpinner.onItemSelectedListener = this
        binding.dstSpinner.onItemSelectedListener = this
    }

    private fun setAyanamsaSpinner() {
        val ayanmasha = arrayOf("N.C. Lahiri", "KP New", "KP Old", "Raman", "KP Khullar", "Syan")
        val adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, ayanmasha)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.ayanmashaSpinner.adapter = adapter
    }

    private fun setDstSpinner() {
        val dst = arrayOf("0", "1", "2")
        val adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, dst)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.dstSpinner.adapter = adapter
    }

    fun setDate(day: Int, month: Int, year: Int) {
        val monthShortName = resources.getStringArray(R.array.month_short_name_en)
        binding.dateValTv.text = day.toString() + " - " + monthShortName[month] + " - " + year
    }

    fun setTime(hour: Int, minute: Int, am_pm: Int) {
        binding.timeValTv.text = Utility.getFormattedTime(
            hour,
            minute,
            am_pm
        )
    }

    fun setPlace() {

    }

    override fun onItemSelected(p0: AdapterView<*>, p1: View, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>) {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.calculate_btn -> {
                startActivity(Intent(this, KundliOutputActivity::class.java))
            }
            R.id.date_val_tv -> {
                DatePickerDialog.showDatePicker(this)
            }
            R.id.time_val_tv -> {
                TimePickerDialog.showTimePicker(this, supportFragmentManager)
            }


        }
    }

}