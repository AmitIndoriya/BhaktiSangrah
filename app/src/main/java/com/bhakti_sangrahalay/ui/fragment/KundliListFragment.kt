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

class KundliListFragment : Fragment() {
    lateinit var binding: FragKundliListLayoutBinding


    companion object {
        fun getInstance(): KundliListFragment {
            return KundliListFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragKundliListLayoutBinding.inflate(inflater, container, false)
        setListData()
        return binding.root
    }

    private fun setListData() {
        binding.recyclerView.isNestedScrollingEnabled = false
        val kundliListAdapter =
            KundliListAdapter(requireActivity())
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = mLayoutManager
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = kundliListAdapter


    }

}