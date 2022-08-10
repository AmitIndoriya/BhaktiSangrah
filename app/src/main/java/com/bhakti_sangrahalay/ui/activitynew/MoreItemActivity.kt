package com.bhakti_sangrahalay.ui.activitynew

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapternew.MoreItemAdater
import com.bhakti_sangrahalay.contansts.GlobalVariables
import com.bhakti_sangrahalay.databinding.MoreItemActivityLayoutBinding
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.util.Utility
import kotlin.properties.Delegates

class MoreItemActivity : BaseActivity() {
    lateinit var binding: MoreItemActivityLayoutBinding
    var type by Delegates.notNull<Int>()
    override fun attachViewModel() {
    }

    override fun setTypeface() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFont()
        getIntentData()
        binding = MoreItemActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRVAdapter()
    }

    private fun getIntentData() {
        val bundle = intent.extras
        type = bundle!!.getInt("type")
        if (type == GlobalVariables.aarti) {
            setTitle(resources.getString(R.string.aartiyan))
        } else if (type == GlobalVariables.chalisha) {
            setTitle(resources.getString(R.string.chalisha))
        }
    }

    private fun setRVAdapter() {
        var source = getName()
        var imagesSource = getImages()
        var rowFile = getRowFile()
        if (type == GlobalVariables.chalisha) {
            source = getTitleForChalisha()
            imagesSource = getIconForChalisha()
            rowFile = getRowFileForChalisha()
        }
        val adapter = MoreItemAdater(this, source, imagesSource, rowFile, type)

        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)

    }

    private fun getName(): Array<String> {
        return resources.getStringArray(R.array.god_name_list)
    }

    fun getImages(): IntArray {
        return Utility.getIntArray(resources, R.array.god_icon_list)
    }

    private fun getRowFile(): IntArray {
        return Utility.getIntArray(resources, R.array.aarti_row_file_list)
    }

    private fun getTitleForChalisha(): Array<String> {
        return resources.getStringArray(R.array.chalish_title_list)
    }

    private fun getIconForChalisha(): IntArray {
        return Utility.getIntArray(resources, R.array.chalish_icon_list)
    }

    private fun getRowFileForChalisha(): IntArray {
        return Utility.getIntArray(resources, R.array.chalish_raw_file_list)
    }
}