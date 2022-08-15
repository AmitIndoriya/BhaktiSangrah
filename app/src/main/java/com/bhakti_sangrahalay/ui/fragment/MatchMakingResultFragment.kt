package com.bhakti_sangrahalay.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bhakti_sangrahalay.adapter.MatchMakingListAdapter
import com.bhakti_sangrahalay.databinding.FragMatchMakingResultLayBinding
import com.bhakti_sangrahalay.matchmaking.model.GunaListBean
import com.bhakti_sangrahalay.matchmaking.model.MatchMakingResultBean
import com.bhakti_sangrahalay.ui.activity.MatchMakingResultActivity


class MatchMakingResultFragment : Fragment() {
    lateinit var binding: FragMatchMakingResultLayBinding


    companion object {
        fun getInstance(): MatchMakingResultFragment {
            return MatchMakingResultFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MatchMakingResultActivity).viewModel.matchMakingResultLiveData.observe(
            requireActivity()
        ) {
            setData(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragMatchMakingResultLayBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun setData(matchMakingResultBean: MatchMakingResultBean) {
        binding.totalTv.text = matchMakingResultBean.total.toString()
        setListData(matchMakingResultBean.gunaList)
    }

    private fun setListData(arrayList: ArrayList<GunaListBean>) {
        binding.recyclerView.isNestedScrollingEnabled = false
        val matchMakingListAdapter = MatchMakingListAdapter(requireActivity(), arrayList)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = mLayoutManager
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = matchMakingListAdapter
    }

}