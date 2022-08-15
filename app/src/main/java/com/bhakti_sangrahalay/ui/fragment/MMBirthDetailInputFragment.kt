package com.bhakti_sangrahalay.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bhakti_sangrahalay.databinding.FragMmBirthDetailInputLayoutBinding
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.kundli.model.DateTimeBean
import com.bhakti_sangrahalay.kundli.model.PlaceBean
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.ui.activity.MatchMakingInputActivity
import com.bhakti_sangrahalay.ui.activity.MatchMakingResultActivity
import com.bhakti_sangrahalay.ui.dialogs.DatePickerDialog
import com.bhakti_sangrahalay.ui.dialogs.TimePickerDialog
import com.bhakti_sangrahalay.util.Utility
import java.util.*

class MMBirthDetailInputFragment : BirthDetailInputBaseFragment(), View.OnClickListener,
    AdapterView.OnItemSelectedListener {

    lateinit var binding: FragMmBirthDetailInputLayoutBinding
    private lateinit var boyBirthDetailBean: BirthDetailBean
    private lateinit var girlBirthDetailBean: BirthDetailBean
    private var isBoySelected = true

    companion object {
        fun getInstance(): MMBirthDetailInputFragment {
            return MMBirthDetailInputFragment()
        }
    }

    override fun setTypeface() {
        binding.boyDetailHeadingTv.typeface = (requireActivity() as BaseActivity).semiBoldTypeface
        binding.boyNameLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.boyNameEt.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.boyDateLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.boyDateValTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.boyTimeLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.boyTimeValTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.boyPlaceLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.boyPlaceValTv.typeface = (requireActivity() as BaseActivity).mediumTypeface

        binding.girlDetailHeadingTv.typeface = (requireActivity() as BaseActivity).semiBoldTypeface
        binding.girlNameLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.girlNameEt.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.girlDateLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.girlDateValTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.girlTimeLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.girlTimeValTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.girlPlaceLabelTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
        binding.girlPlaceValTv.typeface = (requireActivity() as BaseActivity).mediumTypeface
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragMmBirthDetailInputLayoutBinding.inflate(inflater, container, false)
        setListener()

        populateData()
        setTypeface()
        return binding.root
    }

    private fun populateData() {
        val calendar = Calendar.getInstance()
        boyBirthDetailBean = BirthDetailBean(
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
        girlBirthDetailBean = BirthDetailBean(
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
        val monthShortName =
            resources.getStringArray(com.bhakti_sangrahalay.R.array.month_short_name_en)

        binding.boyDateValTv.text =
            calendar[Calendar.DATE].toString() + " - " + monthShortName[calendar[Calendar.MONTH]] + " - " + calendar[Calendar.YEAR]
        binding.boyTimeValTv.text = Utility.getFormattedTime(
            calendar[Calendar.HOUR],
            calendar[Calendar.MINUTE],
            calendar[Calendar.AM_PM]
        )
        binding.boyPlaceValTv.text = "Jaipur, Rajasthan, India"

        binding.girlDateValTv.text =
            calendar[Calendar.DATE].toString() + " - " + monthShortName[calendar[Calendar.MONTH]] + " - " + calendar[Calendar.YEAR]
        binding.girlTimeValTv.text = Utility.getFormattedTime(
            calendar[Calendar.HOUR],
            calendar[Calendar.MINUTE],
            calendar[Calendar.AM_PM]
        )
        binding.girlPlaceValTv.text = "Jaipur, Rajasthan, India"
    }

    private fun setListener() {
        binding.calculateBtn.setOnClickListener(this)
        binding.boyDateValTv.setOnClickListener(this)
        binding.boyTimeValTv.setOnClickListener(this)
        binding.girlDateValTv.setOnClickListener(this)
        binding.girlTimeValTv.setOnClickListener(this)
    }

    override fun setDate(day: Int, month: Int, year: Int) {
        if (isBoySelected) {
            setBoyDate(day, month, year)
        } else {
            setGirlDate(day, month, year)
        }
    }

    private fun setBoyDate(day: Int, month: Int, year: Int) {
        val dateTimeBean = boyBirthDetailBean.dateTimeBean
        dateTimeBean.day = day.toString()
        dateTimeBean.month = (month + 1).toString()
        dateTimeBean.year = year.toString()
        boyBirthDetailBean.dateTimeBean = dateTimeBean
        val monthShortName =
            resources.getStringArray(com.bhakti_sangrahalay.R.array.month_short_name_en)
        binding.boyDateValTv.text = day.toString() + " - " + monthShortName[month] + " - " + year
    }

    private fun setGirlDate(day: Int, month: Int, year: Int) {
        val dateTimeBean = girlBirthDetailBean.dateTimeBean
        dateTimeBean.day = day.toString()
        dateTimeBean.month = (month + 1).toString()
        dateTimeBean.year = year.toString()
        girlBirthDetailBean.dateTimeBean = dateTimeBean
        val monthShortName =
            resources.getStringArray(com.bhakti_sangrahalay.R.array.month_short_name_en)
        binding.girlDateValTv.text = day.toString() + " - " + monthShortName[month] + " - " + year
    }

    override fun setTime(hour: Int, minute: Int, am_pm: Int) {
        if (isBoySelected) {
            setBoyTime(hour, minute, am_pm)
        } else {
            setGirlTime(hour, minute, am_pm)
        }
    }

    private fun setBoyTime(hour: Int, minute: Int, am_pm: Int) {
        val dateTimeBean = boyBirthDetailBean.dateTimeBean
        dateTimeBean.hrs = hour.toString()
        dateTimeBean.min = minute.toString()
        dateTimeBean.sec = "00"
        boyBirthDetailBean.dateTimeBean = dateTimeBean
        binding.boyTimeValTv.text = Utility.getFormattedTime(
            hour,
            minute,
            am_pm
        )
    }

    private fun setGirlTime(hour: Int, minute: Int, am_pm: Int) {
        val dateTimeBean = girlBirthDetailBean.dateTimeBean
        dateTimeBean.hrs = hour.toString()
        dateTimeBean.min = minute.toString()
        dateTimeBean.sec = "00"
        girlBirthDetailBean.dateTimeBean = dateTimeBean
        binding.girlTimeValTv.text = Utility.getFormattedTime(
            hour,
            minute,
            am_pm
        )
    }

    override fun setPlace() {

    }

    override fun onItemSelected(p0: AdapterView<*>, p1: View, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>) {
    }

    private fun saveBirthDetailInDB() {
        (requireActivity() as MatchMakingInputActivity).viewModel.insertBirthDetailInfo(
            boyBirthDetailBean,
            girlBirthDetailBean
        )
    }

    override fun onClick(v: View) {
        when (v.id) {
            com.bhakti_sangrahalay.R.id.calculate_btn -> {
                saveBirthDetailInDB()
                val bundle = Bundle()
                bundle.putSerializable("boy_detail", boyBirthDetailBean)
                bundle.putSerializable("girl_detail", girlBirthDetailBean)
                val intent = Intent(requireActivity(), MatchMakingResultActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
            com.bhakti_sangrahalay.R.id.boy_date_val_tv -> {
                isBoySelected = true
                val dateTimeBean = boyBirthDetailBean.dateTimeBean
                DatePickerDialog.showDatePicker(
                    requireActivity(),
                    this,
                    dateTimeBean.day.toInt(),
                    dateTimeBean.month.toInt(),
                    dateTimeBean.year.toInt()
                )
            }
            com.bhakti_sangrahalay.R.id.girl_date_val_tv -> {
                isBoySelected = false
                val dateTimeBean = girlBirthDetailBean.dateTimeBean
                DatePickerDialog.showDatePicker(
                    requireActivity(),
                    this,
                    dateTimeBean.day.toInt(),
                    dateTimeBean.month.toInt(),
                    dateTimeBean.year.toInt()
                )
            }
            com.bhakti_sangrahalay.R.id.boy_time_val_tv -> {
                isBoySelected = true
                val dateTimeBean = boyBirthDetailBean.dateTimeBean
                TimePickerDialog.showTimePicker(
                    requireActivity(),
                    this,
                    requireActivity().supportFragmentManager,
                    dateTimeBean.hrs.toInt(),
                    dateTimeBean.min.toInt()
                )
            }
            com.bhakti_sangrahalay.R.id.girl_time_val_tv -> {
                isBoySelected = false
                val dateTimeBean = girlBirthDetailBean.dateTimeBean
                TimePickerDialog.showTimePicker(
                    requireActivity(),
                    this,
                    requireActivity().supportFragmentManager,
                    dateTimeBean.hrs.toInt(),
                    dateTimeBean.min.toInt()
                )
            }
        }
    }
}