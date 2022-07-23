package com.bhakti_sangrahalay.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.RahuKaalListAdapter
import com.bhakti_sangrahalay.panchang.model.RahuKaalBean
import com.bhakti_sangrahalay.ui.activity.PanchangActivity
import com.bhakti_sangrahalay.util.DatePicker
import com.bhakti_sangrahalay.util.Utility
import java.util.*

class RahuKaalFragment : Fragment(), View.OnClickListener {
    var activity: PanchangActivity? = null
    private lateinit var rahukaalRV: RecyclerView

    private lateinit var currentRahukaalHeadingTV: TextView
    private lateinit var currentRahukaalFromTimeTV: TextView
    private lateinit var currentRahukaalToTimeTV: TextView

    private lateinit var prevDateBtn: Button
    private lateinit var nextDateBtn: Button
    private lateinit var datePickerTV: TextView
    private lateinit var todayTV: TextView
    private lateinit var tableHeaderTV: TextView


    companion object {
        fun getInstance() = RahuKaalFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as PanchangActivity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val place = Utility.getPlaceForPanchang()

        (activity as PanchangActivity).viewModel.getCurrentRahuKaal(place)
        (activity as PanchangActivity).viewModel.getWeeklyRahuKaalList((activity as PanchangActivity).calendar, place)

        (activity as PanchangActivity).viewModel.rahuKaalLiveData.observe(this, { setRahukaalList(it) })
        (activity as PanchangActivity).viewModel.currentRahuKaalLiveData.observe(this, { setCurrentRahukaal(it) })

        (activity as PanchangActivity).viewModel.dateLiveData.observe(this, { setPanchangDataAfterSelectDateFromPicker(it) })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_rahu_kaal_layout, container, false)
        initView(view)
        setTypeface()
        setClickListener()
        setDate((activity as PanchangActivity).calendar)
        return view
    }

    private fun setClickListener() {
        prevDateBtn.setOnClickListener(this)
        nextDateBtn.setOnClickListener(this)
        todayTV.setOnClickListener(this)
        datePickerTV.setOnClickListener(this)
    }

    @SuppressLint("CutPasteId")
    private fun initView(view: View) {
        rahukaalRV = view.findViewById(R.id.day_rahu_kaal_rv)

        currentRahukaalHeadingTV = view.findViewById(R.id.current_rahu_kaal_tv)
        currentRahukaalFromTimeTV = view.findViewById(R.id.current_from_tv)
        currentRahukaalToTimeTV = view.findViewById(R.id.current_to_tv)
        tableHeaderTV = view.findViewById(R.id.day_rahuKaal_tv)

        prevDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.previous_date_btn)
        nextDateBtn = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.next_date_btn)
        datePickerTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.date_picker_tv)
        todayTV = (view.findViewById(R.id.date_picker) as View).findViewById(R.id.current_date_btn)


    }

    private fun setTypeface() {
        currentRahukaalHeadingTV.typeface = Utility.getSemiBoldTypeface(context)
        currentRahukaalFromTimeTV.typeface = Utility.getSemiBoldTypeface(context)
        currentRahukaalToTimeTV.typeface = Utility.getSemiBoldTypeface(context)

        datePickerTV.typeface = Utility.getSemiBoldTypeface(context)
        todayTV.typeface = Utility.getSemiBoldTypeface(context)
        tableHeaderTV.typeface = Utility.getSemiBoldTypeface(context)

    }

    private fun setPanchangDataAfterSelectDateFromPicker(calendar: Calendar) {
        (activity as PanchangActivity).viewModel.getDayHoraData(resources, calendar)
        (activity as PanchangActivity).viewModel.getNightHoraData(resources, calendar)
        setDate(calendar)
    }

    private fun setDate(calendar: Calendar) {
        datePickerTV.text = Utility.getDateForPanchangHeading(calendar.time)
    }


    private fun setRahukaalList(rahuKaalList: ArrayList<RahuKaalBean>) {
        rahukaalRV.isNestedScrollingEnabled = false
        val horaListAdapter = activity?.let { RahuKaalListAdapter(requireActivity(), rahuKaalList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rahukaalRV.layoutManager = mLayoutManager
        rahukaalRV.itemAnimator = DefaultItemAnimator()
        rahukaalRV.adapter = horaListAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentRahukaal(rahuKaalBean: RahuKaalBean) {
        currentRahukaalFromTimeTV.text = rahuKaalBean.from + " " + resources.getString(R.string.from) + " "
        currentRahukaalToTimeTV.text = rahuKaalBean.to
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.previous_date_btn -> {
                Toast.makeText(activity, "pre", Toast.LENGTH_SHORT).show()
                (activity as PanchangActivity).calendar.add(Calendar.DATE, -1)
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getWeeklyRahuKaalList((activity as PanchangActivity).calendar, Utility.getPlaceForPanchang())
            }
            R.id.next_date_btn -> {
                (activity as PanchangActivity).calendar.add(Calendar.DATE, 1)
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getWeeklyRahuKaalList((activity as PanchangActivity).calendar, Utility.getPlaceForPanchang())
            }
            R.id.date_picker_tv -> {
                activity?.let { DatePicker().showDatePicker(it).show(requireActivity().supportFragmentManager, "MATERIAL_DATE_PICKER") }
            }
            R.id.current_date_btn -> {
                (activity as PanchangActivity).calendar = Calendar.getInstance()
                setDate((activity as PanchangActivity).calendar)
                (activity as PanchangActivity).viewModel.getWeeklyRahuKaalList((activity as PanchangActivity).calendar, Utility.getPlaceForPanchang())
            }
        }
    }
}