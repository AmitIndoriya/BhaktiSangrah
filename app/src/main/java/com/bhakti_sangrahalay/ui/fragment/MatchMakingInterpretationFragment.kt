package com.bhakti_sangrahalay.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.MatchMakingInterpretationAdapter
import com.bhakti_sangrahalay.databinding.FragMatchMakingInterpretationLayoutBinding
import com.bhakti_sangrahalay.ui.activity.MatchMakingResultActivity

class MatchMakingInterpretationFragment : Fragment() {
    lateinit var binding: FragMatchMakingInterpretationLayoutBinding


    companion object {
        fun getInstance(): MatchMakingInterpretationFragment {
            return MatchMakingInterpretationFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MatchMakingResultActivity).viewModel.getInterpretationData(
            requireActivity()
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragMatchMakingInterpretationLayoutBinding.inflate(inflater, container, false)
        (requireActivity() as MatchMakingResultActivity).viewModel.interpretationLiveData.observe(
            requireActivity()
        ) {
            setListData(it)
        }
        return binding.root
    }

    private fun setListData(birthDetailBeanList: ArrayList<String>) {
        val monthName = resources.getStringArray(R.array.month_short_name_en)
        binding.recyclerView.isNestedScrollingEnabled = false
        val matchMakingInterpretationAdapter =
            MatchMakingInterpretationAdapter(requireActivity(), birthDetailBeanList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = mLayoutManager
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = matchMakingInterpretationAdapter
    }

}