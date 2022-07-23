package com.bhakti_sangrahalay.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.HomeGridRVAdapter
import com.bhakti_sangrahalay.databinding.ActDashboardLayoutBinding
import com.bhakti_sangrahalay.viewmodel.DashBoardViewModel

class DashBoardActivity : BaseActivity() {
    lateinit var viewModel: DashBoardViewModel
    private lateinit var binding: ActDashboardLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActDashboardLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setHomeGridItems()
    }

    override fun attachViewModel() {
        val viewModelProvider =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
        viewModel = viewModelProvider[DashBoardViewModel::class.java]
    }

    override fun setTypeface() {
    }

    fun setHomeGridItems() {
        binding.gridRv.setLayoutManager(GridLayoutManager(this, 3))
        val nameList = ArrayList<String>()
        nameList.add("test1")
        nameList.add("test1")
        nameList.add("test1")
        nameList.add("test1")
        val drawableBgList = ArrayList<Int>()
        drawableBgList.add(R.drawable.rect_light_blue)
        drawableBgList.add(R.drawable.rect_light_green)
        drawableBgList.add(R.drawable.rect_light_pink)
        drawableBgList.add(R.drawable.rect_light_yellow)
        val drawableList = ArrayList<Int>()
        drawableList.add(R.drawable.panchang)
        drawableList.add(R.drawable.kundli)
        drawableList.add(R.drawable.muhurat)
        drawableList.add(R.drawable.horoscope)

        val adapter1 = HomeGridRVAdapter(this, nameList, drawableBgList, drawableList)
        binding.gridRv.setAdapter(adapter1)
    }
}