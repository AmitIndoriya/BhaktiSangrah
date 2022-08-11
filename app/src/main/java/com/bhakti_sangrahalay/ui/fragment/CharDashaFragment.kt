package com.bhakti_sangrahalay.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.adapter.CharAntarDashaAdapter
import com.bhakti_sangrahalay.adapter.CharDashaAdapter
import com.bhakti_sangrahalay.databinding.FragCharDashaLayoutBinding
import com.bhakti_sangrahalay.kundli.model.CharDashaBean
import com.bhakti_sangrahalay.ui.activity.KundliOutputActivity
import com.bhakti_sangrahalay.viewmodel.KundliOutputActivityViewModel

class CharDashaFragment : BaseFragment() {
    lateinit var viewModel: KundliOutputActivityViewModel
    lateinit var binding: FragCharDashaLayoutBinding
    override fun setTypeface() {
    }

    companion object {
        fun getInstance(): CharDashaFragment {
            return CharDashaFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(KundliOutputActivityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragCharDashaLayoutBinding.inflate(inflater, container, false)
        val kundliBean = (requireActivity() as KundliOutputActivity).birthDetailBean
        val dob =
            kundliBean.dateTimeBean.day + "/" + kundliBean.dateTimeBean.month + "/" + kundliBean.dateTimeBean.year
        val arrayList = viewModel.getCharDashaData(dob)
        setListData(arrayList)
        setAntarDashaListData(arrayList)
        return binding.root
    }

    private fun setListData(arrayList: ArrayList<CharDashaBean>) {
        binding.charMahaDashaRecyclerView.isNestedScrollingEnabled = false
        val charDashaAdapter = CharDashaAdapter(requireActivity(), arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        binding.charMahaDashaRecyclerView.layoutManager = mLayoutManager
        binding.charMahaDashaRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.charMahaDashaRecyclerView.adapter = charDashaAdapter
    }

    private fun setAntarDashaListData(arrayList: ArrayList<CharDashaBean>) {
        binding.charAntarDashaRecyclerView.isNestedScrollingEnabled = false
        val charAntarDashaAdapter = CharAntarDashaAdapter(requireActivity(), arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        binding.charAntarDashaRecyclerView.layoutManager = mLayoutManager
        binding.charAntarDashaRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.charAntarDashaRecyclerView.adapter = charAntarDashaAdapter
    }
}