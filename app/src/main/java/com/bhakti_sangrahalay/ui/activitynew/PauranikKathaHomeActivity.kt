package com.bhakti_sangrahalay.ui.activitynew

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapternew.PauranikKathaAdapter
import com.bhakti_sangrahalay.databinding.ActivityPauranikKathaHomeBinding
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.util.Utility

class PauranikKathaHomeActivity : BaseActivity() {
    lateinit var binding: ActivityPauranikKathaHomeBinding
    override fun attachViewModel() {
    }

    override fun setTypeface() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFont()
        binding = ActivityPauranikKathaHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(resources.getString(R.string.pauranik_katha))
        setRVAdapter()
    }

    private fun setRVAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        val adapter = PauranikKathaAdapter(this, getName(), getImages(), getRowFile())
        binding.recyclerView.adapter = adapter

    }

    private fun getName(): Array<String> {
        return resources.getStringArray(R.array.pauranik_katha_name_list)
    }

    fun getImages(): IntArray {
        return Utility.getIntArray(resources, R.array.pauranik_katha_icon_list)
    }

    private fun getRowFile(): IntArray {
        return Utility.getIntArray(resources, R.array.pauranik_katha_row_file_list)
    }
}