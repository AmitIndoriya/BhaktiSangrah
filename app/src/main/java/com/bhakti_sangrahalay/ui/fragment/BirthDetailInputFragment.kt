package com.bhakti_sangrahalay.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bhakti_sangrahalay.databinding.FragBirthDetailInputLayoutBinding
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.kundli.model.DateTimeBean
import com.bhakti_sangrahalay.kundli.model.PlaceBean
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.ui.activity.BirthDetailInputActivityNew
import com.bhakti_sangrahalay.ui.dialogs.DatePickerDialog
import com.bhakti_sangrahalay.ui.dialogs.TimePickerDialog
import com.bhakti_sangrahalay.util.Utility
import java.util.*

class BirthDetailInputFragment : BaseFragment(), View.OnClickListener,
    AdapterView.OnItemSelectedListener {

    lateinit var binding: FragBirthDetailInputLayoutBinding
    private var isExpanded = false
    private lateinit var birthDetailBean: BirthDetailBean

    companion object {
        fun getInstance(): BirthDetailInputFragment {
            return BirthDetailInputFragment()
        }
    }

    override fun setTypeface() {
        binding.maleRb.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.femaleRb.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.nameLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.dateLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.timeLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.placeLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as BirthDetailInputActivityNew).viewModel.openDetailForUpdate.observe(
            this,
            { populateData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragBirthDetailInputLayoutBinding.inflate(inflater, container, false)
        setListener()
        setAyanamsaSpinner()
        setDstSpinner()
        populateData(getBirthDetailWithDefaultBirthDetail())
        (activity as BirthDetailInputActivityNew).collapse(binding.otherCalOptionLay)
        setTypeface()
        return binding.root
    }

    private fun populateData(birthDetailBean: BirthDetailBean) {
        val calendar = Calendar.getInstance()
        this.birthDetailBean = birthDetailBean
        val monthShortName =
            resources.getStringArray(com.bhakti_sangrahalay.R.array.month_short_name_en)

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
        binding.settingTv.setOnClickListener(this)
        binding.ayanmashaSpinner.onItemSelectedListener = this
        binding.dstSpinner.onItemSelectedListener = this

    }

    private fun setAyanamsaSpinner() {
        val ayanmasha = arrayOf("N.C. Lahiri", "KP New", "KP Old", "Raman", "KP Khullar", "Syan")
        val adapter =
            ArrayAdapter<Any?>(requireActivity(), android.R.layout.simple_spinner_item, ayanmasha)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.ayanmashaSpinner.adapter = adapter
    }

    private fun setDstSpinner() {
        val dst = arrayOf("0", "1", "2")
        val adapter =
            ArrayAdapter<Any?>(requireActivity(), android.R.layout.simple_spinner_item, dst)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.dstSpinner.adapter = adapter
    }

    fun setDate(day: Int, month: Int, year: Int) {
        val dateTimeBean = birthDetailBean.dateTimeBean
        dateTimeBean.day = day.toString()
        dateTimeBean.month = (month + 1).toString()
        dateTimeBean.year = year.toString()
        birthDetailBean.dateTimeBean = dateTimeBean
        val monthShortName =
            resources.getStringArray(com.bhakti_sangrahalay.R.array.month_short_name_en)
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

    private fun saveBirthDetailInDB() {
        (requireActivity() as BirthDetailInputActivityNew).viewModel.insertBirthDetailInfo(
            birthDetailBean
        )

    }

    override fun onClick(v: View) {
        when (v.id) {
            com.bhakti_sangrahalay.R.id.calculate_btn -> {
                saveBirthDetailInDB()
                (requireActivity() as BirthDetailInputActivityNew).startKundliOutputActivity(
                    birthDetailBean
                )
            }
            com.bhakti_sangrahalay.R.id.date_val_tv -> {
                val dateTimeBean = birthDetailBean.dateTimeBean
                DatePickerDialog.showDatePicker(
                    requireActivity(),
                    this,
                    dateTimeBean.day.toInt(),
                    dateTimeBean.month.toInt(),
                    dateTimeBean.year.toInt()
                )
            }
            com.bhakti_sangrahalay.R.id.time_val_tv -> {
                val dateTimeBean = birthDetailBean.dateTimeBean
                TimePickerDialog.showTimePicker(
                    requireActivity(),
                    this,
                    requireActivity().supportFragmentManager,
                    dateTimeBean.hrs.toInt(),
                    dateTimeBean.min.toInt()
                )
            }
            com.bhakti_sangrahalay.R.id.setting_tv -> {
                if (isExpanded) {
                    isExpanded = false
                    (activity as BirthDetailInputActivityNew).collapse(binding.otherCalOptionLay)
                } else {
                    isExpanded = true
                    (activity as BirthDetailInputActivityNew).expand(binding.otherCalOptionLay)
                }

            }

        }
    }

    private fun getBirthDetailWithDefaultBirthDetail(): BirthDetailBean {
        return BirthDetailBean(
            name = "Amit",
            sex = "M",
            dateTimeBean = DateTimeBean(
                day = "23", //calendar[Calendar.DATE].toString(),
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
    }
}