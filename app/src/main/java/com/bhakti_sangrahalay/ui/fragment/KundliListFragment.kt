package com.bhakti_sangrahalay.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.adapter.KundliListAdapter
import com.bhakti_sangrahalay.databinding.FragKundliListLayoutBinding
import com.bhakti_sangrahalay.kundli.model.BirthDetailBean
import com.bhakti_sangrahalay.ui.activity.BirthDetailInputActivityNew

class KundliListFragment : Fragment() {
    lateinit var binding: FragKundliListLayoutBinding


    companion object {
        fun getInstance(): KundliListFragment {
            return KundliListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireActivity() is BirthDetailInputActivityNew) {
            (requireActivity() as BirthDetailInputActivityNew).viewModel.getBirthDetailInfoList()
            (requireActivity() as BirthDetailInputActivityNew).viewModel.birthDetailBeanListLiveData.observe(
                this
            ) { setListData(it) }
            (requireActivity() as BirthDetailInputActivityNew).viewModel.isNewBirthDetailInfoAdded.observe(
                this
            ) { if (it) (requireActivity() as BirthDetailInputActivityNew).viewModel.getBirthDetailInfoList() }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragKundliListLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setListData(birthDetailBeanList: ArrayList<BirthDetailBean>) {
        binding.recyclerView.isNestedScrollingEnabled = false
        val kundliListAdapter =
            KundliListAdapter(requireActivity(), birthDetailBeanList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = mLayoutManager
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = kundliListAdapter
    }

}