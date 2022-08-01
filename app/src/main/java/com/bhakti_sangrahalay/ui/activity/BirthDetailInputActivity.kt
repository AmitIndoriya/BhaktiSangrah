package com.bhakti_sangrahalay.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.databinding.ActBirthdetailInputLayoutBinding
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.kundli.model.DateTimeBean
import com.bhakti_sangrahalay.kundli.model.PlaceBean
import com.bhakti_sangrahalay.ui.dialogs.DatePickerDialog
import com.bhakti_sangrahalay.ui.dialogs.TimePickerDialog
import com.bhakti_sangrahalay.util.Utility
import com.bhakti_sangrahalay.viewmodel.BirthDetaiInputActivityViewModel
import java.util.*


class BirthDetailInputActivity : BaseActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {
    private lateinit var birthDetailBean: BirthDetailBean
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
        populateData()

    }

    private fun populateData() {
        val calendar = Calendar.getInstance()
        birthDetailBean = BirthDetailBean(
            name = "Amit",
            sex = "M",
            dateTimeBean = DateTimeBean(
                day ="23", //calendar[Calendar.DATE].toString(),
                month = "11",//calendar[Calendar.MONTH].toString(),
                year = "2020",//calendar[Calendar.YEAR].toString(),
                hrs = "18",//calendar[Calendar.HOUR].toString(),
                min = "30",//calendar[Calendar.MINUTE].toString(),
                sec = "00",//calendar[Calendar.SECOND].toString(),
            ),
            placeBean = PlaceBean(
                place = "Agra",
                longDeg = "078",
                longMin = "00",
                longEW = "E",
                latDeg = "027",
                latMin = "09",
                latNS = "N",
                timeZone = "5.5"
            ),
            dst = "0",
            ayanamsa = "0",
            charting = "0",
            kphn = "0",
            button1 = "Get+Kundali",
            languageCode = "0",
        )
        val monthShortName = resources.getStringArray(R.array.month_short_name_en)

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
        val dateTimeBean = birthDetailBean.dateTimeBean
        dateTimeBean.day = day.toString()
        dateTimeBean.month = (month+1).toString()
        dateTimeBean.year = year.toString()
        birthDetailBean.dateTimeBean = dateTimeBean
        val monthShortName = resources.getStringArray(R.array.month_short_name_en)
        binding.dateValTv.text = day.toString() + " - " + monthShortName[month] + " - " + year
    }

    fun setTime(hour: Int, minute: Int, am_pm: Int) {
        val dateTimeBean = birthDetailBean.dateTimeBean
        dateTimeBean.hrs = hour.toString()
        dateTimeBean.min = minute.toString()
        dateTimeBean.sec = "00"
        birthDetailBean.dateTimeBean = dateTimeBean
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
                val intent = Intent(this, KundliOutputActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("BirthDetail", birthDetailBean)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            R.id.date_val_tv -> {
                val dateTimeBean = birthDetailBean.dateTimeBean
                DatePickerDialog.showDatePicker(
                    this,
                    dateTimeBean.day.toInt(),
                    dateTimeBean.month.toInt(),
                    dateTimeBean.year.toInt()
                )
            }
            R.id.time_val_tv -> {
                val dateTimeBean = birthDetailBean.dateTimeBean
                TimePickerDialog.showTimePicker(
                    this,
                    supportFragmentManager,
                    dateTimeBean.hrs.toInt(),
                    dateTimeBean.min.toInt()
                )
            }


        }
    }

}