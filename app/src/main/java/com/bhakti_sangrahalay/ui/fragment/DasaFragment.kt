package com.bhakti_sangrahalay.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.DashaListAdapter
import com.bhakti_sangrahalay.kundli.calculation.DashaCalculation.getAntaraDasa
import com.bhakti_sangrahalay.kundli.calculation.DashaCalculation.getVimPratyantraDasa
import com.bhakti_sangrahalay.kundli.calculation.DashaCalculation.getVimshttariMahaDasa
import com.bhakti_sangrahalay.kundli.model.DasaBean
import com.bhakti_sangrahalay.util.Utility

class DasaFragment : BaseFragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var titleTV: TextView
    private lateinit var noteTV: TextView


    private var selectedVimPlanet = 0
    private var selectedAntPlanet = 0
    private var selectedPraPlanet = 0
    private var selectedSookPlanet = 0
    private var clickekCount = 0
    private lateinit var arrVimDasa: Array<String>

    private lateinit var mahaDasaArr: Array<DasaBean>
    private lateinit var antaraDasaArr: Array<DasaBean>
    private lateinit var pratyantraDasaArr: Array<DasaBean>
    private lateinit var sookshamDasaArr: Array<DasaBean>
    private lateinit var pranaDasaArr: Array<DasaBean>
    private lateinit var titles: Array<String>

    companion object {
        fun getInstance() = DasaFragment()
    }

    override fun setTypeface() {
        titleTV.typeface = Utility.getSemiBoldTypeface(requireActivity())
        noteTV.typeface = Utility.getMediumTypeface(requireActivity())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        titles = resources.getStringArray(R.array.dasha_titles)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (clickekCount > 0) {
                        changeDasaOnBackPress()
                    } else {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_dasha_layout, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        titleTV = view.findViewById(R.id.textview)
        noteTV = view.findViewById(R.id.note_tv)
        arrVimDasa = resources.getStringArray(R.array.Vimshottari_dasa_planets_list)
        setDayHoraList(getVimDasaFormmattedData())
        titleTV.text = titles[0]
        setTypeface()
        return view
    }

    private fun setDayHoraList(horaDataList: ArrayList<DasaBean>) {
        recyclerView.isNestedScrollingEnabled = false
        val dashaListAdapter = activity?.let { DashaListAdapter(it, this, horaDataList) }
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = dashaListAdapter

    }

    fun changeDasaOnclick(pos: Int) {

        when (clickekCount) {
            0 -> {
                selectedVimPlanet = pos
                setDayHoraList(getAntaraDasaFormmattedData(pos))
            }
            1 -> {
                selectedAntPlanet = pos
                setDayHoraList(getPratyantraDasaFormmattedData(pos))
            }
            2 -> {
                selectedPraPlanet = pos
                setDayHoraList(getSookshmaDasaFormmattedData(pos))
            }
            3 -> {
                selectedSookPlanet = pos
                setDayHoraList(getPranaDasaFormmattedData(pos))
            }
        }

        if (clickekCount < 4) {
            clickekCount++
            titleTV.text = titles[clickekCount]
        }

    }

    fun changeDasaOnBackPress() {
        clickekCount--
        when (clickekCount) {
            0 -> {
                setDayHoraList(getVimDasaFormmattedData())
            }
            1 -> {
                setDayHoraList(getAntaraDasaFormmattedData(selectedAntPlanet))
            }
            2 -> {
                setDayHoraList(getPratyantraDasaFormmattedData(selectedPraPlanet))
            }
            3 -> {
                setDayHoraList(getSookshmaDasaFormmattedData(selectedSookPlanet))
            }
        }
        titleTV.text = titles[clickekCount]
    }

    private fun getVimDasaFormmattedData(): ArrayList<DasaBean> {
        val arrayList = ArrayList<DasaBean>()
        mahaDasaArr = getVimshttariMahaDasa()
        for (element in mahaDasaArr) {
            element.planetName = arrVimDasa[element.planetNo]
            element.dasaTimeStr = Utility.doubleToStringDateDDMMYY(element.dasaTime, "/")
            arrayList.add(element)
        }
        return arrayList
    }

    private fun getAntaraDasaFormmattedData(pos: Int): ArrayList<DasaBean> {
        val arrayList = ArrayList<DasaBean>()
        val plaNo = mahaDasaArr[selectedVimPlanet].planetNo
        antaraDasaArr = getAntaraDasa(pos, mahaDasaArr)
        for (element in antaraDasaArr) {
            element.planetName = arrVimDasa[plaNo] + "/" + arrVimDasa[element.planetNo]
            element.dasaTimeStr = Utility.doubleToStringDateDDMMYY(element.dasaTime, "/")
            arrayList.add(element)
        }
        return arrayList
    }

    private fun getPratyantraDasaFormmattedData(pos: Int): ArrayList<DasaBean> {
        val arrayList = ArrayList<DasaBean>()
        val lastEndDate = mahaDasaArr[selectedVimPlanet].dasaTime
        pratyantraDasaArr = getVimPratyantraDasa(pos, antaraDasaArr, lastEndDate)
        val plaNo = mahaDasaArr[selectedVimPlanet].planetNo
        val plaNo1 = antaraDasaArr[pos].planetNo
        for (element in pratyantraDasaArr) {
            element.planetName =
                arrVimDasa[plaNo] + "/" + arrVimDasa[plaNo1] + "/" + arrVimDasa[element.planetNo]
            element.dasaTimeStr = Utility.doubleToStringDateDDMMYY(element.dasaTime, "/")
            arrayList.add(element)
        }
        return arrayList
    }

    private fun getSookshmaDasaFormmattedData(pos: Int): ArrayList<DasaBean> {
        val arrayList = ArrayList<DasaBean>()
        val lastEndDate = if (pos == 0) {
            if (selectedVimPlanet > 0) {
                antaraDasaArr[selectedVimPlanet - 1].dasaTime
            } else {
                mahaDasaArr[selectedVimPlanet].dasaTime
            }
        } else {
            pratyantraDasaArr[pos - 1].dasaTime
        }
        sookshamDasaArr = getVimPratyantraDasa(pos, pratyantraDasaArr, lastEndDate)
        val plaNo = mahaDasaArr[selectedVimPlanet].planetNo
        val plaNo1 = antaraDasaArr[selectedAntPlanet].planetNo
        val plaNo2 = pratyantraDasaArr[pos].planetNo
        for (element in sookshamDasaArr) {
            element.planetName =
                arrVimDasa[plaNo] + "/" + arrVimDasa[plaNo1] + "/" + arrVimDasa[plaNo2] + "/" + arrVimDasa[element.planetNo]
            element.dasaTimeStr = Utility.doubleToStringDateDDMMYY(element.dasaTime, "/")
            arrayList.add(element)
        }
        return arrayList
    }

    private fun getPranaDasaFormmattedData(pos: Int): ArrayList<DasaBean> {
        val arrayList = ArrayList<DasaBean>()
        val lastEndDate = if (pos == 0) {
            if (selectedPraPlanet > 0) {
                pratyantraDasaArr[selectedPraPlanet - 1].dasaTime
            } else {
                if (selectedAntPlanet > 0) {
                    antaraDasaArr[selectedAntPlanet - 1].dasaTime
                } else {
                    mahaDasaArr[selectedVimPlanet].dasaTime
                }
            }
        } else {
            sookshamDasaArr[pos - 1].dasaTime
        }
        pranaDasaArr = getVimPratyantraDasa(pos, sookshamDasaArr, lastEndDate)
        val plaNo = mahaDasaArr[selectedVimPlanet].planetNo
        val plaNo1 = antaraDasaArr[selectedAntPlanet].planetNo
        val plaNo2 = pratyantraDasaArr[selectedPraPlanet].planetNo
        val plaNo3 = sookshamDasaArr[selectedSookPlanet].planetNo
        for (element in pranaDasaArr) {
            element.planetName =
                arrVimDasa[plaNo] + "/" + arrVimDasa[plaNo1] + "/" + arrVimDasa[plaNo2] + "/" + arrVimDasa[plaNo3] + "/" + arrVimDasa[element.planetNo]
            element.dasaTimeStr = Utility.doubleToStringDateDDMMYY(element.dasaTime, "/")
            arrayList.add(element)
        }
        return arrayList
    }


}