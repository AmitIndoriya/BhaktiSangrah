package com.bhakti_sangrahalay.ui.activitynew

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.MantraAdapter
import com.bhakti_sangrahalay.databinding.MantraLayoutBinding
import com.bhakti_sangrahalay.model.MantraBean
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.util.Parser
import com.bhakti_sangrahalay.util.Utility

class MantraActivity : BaseActivity() {
    lateinit var binding: MantraLayoutBinding
    override fun attachViewModel() {

    }

    override fun setTypeface() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFont()
        binding = MantraLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(resources.getString(R.string.mantra))
        setRVAdapter()
    }

    private fun setRVAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val arrayList: java.util.ArrayList<MantraBean> =
            Parser.mantraListParser(Utility.readFromFile(resources, R.raw.mantra_list))
        val adapter = MantraAdapter(this, arrayList, getImages())
        binding.recyclerView.adapter = adapter
    }

    fun getImages(): ArrayList<Int> {
        val imagesSource = ArrayList<Int>()
        imagesSource.add(R.drawable.gayatri_maa_image)
        imagesSource.add(R.drawable.ganeshji_aarti_image)
        imagesSource.add(R.drawable.shivji_aarti_image)
        imagesSource.add(R.drawable.hanuman_chalisha)
        imagesSource.add(R.drawable.chandra_grah_image)
        imagesSource.add(R.drawable.mangal_grah_image)
        imagesSource.add(R.drawable.budh_grah_image)
        imagesSource.add(R.drawable.guru_grah_image)
        imagesSource.add(R.drawable.sukra_grah_image)
        imagesSource.add(R.drawable.shani_dev_image)
        imagesSource.add(R.drawable.surya_dev_image)
        imagesSource.add(R.drawable.rahu_grah_image)
        imagesSource.add(R.drawable.ketu_grah_image)
        //imagesSource.add(R.drawable.others1);
        return imagesSource
    }
}