package com.bhakti_sangrahalay.ui.activitynew

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.KathaAdapter
import com.bhakti_sangrahalay.databinding.KathaActivityLayoutBinding
import com.bhakti_sangrahalay.model.KathaBean
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.util.Parser
import com.bhakti_sangrahalay.util.Utility

class KathaActivity : BaseActivity() {
    lateinit var binding: KathaActivityLayoutBinding
    override fun attachViewModel() {

    }

    override fun setTypeface() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = KathaActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
    }

    private fun getIntentData() {
        val bundle = intent.extras
        val resId = bundle?.getInt("resId")
        val title = bundle?.getString("title")
        if (resId != null) {
            setRVData(resId)
        }
        super.setTitle(title)
    }

    private fun setRVData(resId: Int) {
        val kathaList = Parser.kathaListParser(Utility.readFromFile(resources, resId))
        setRVAdapter(kathaList)
    }

    private fun setRVAdapter(arrayList: ArrayList<KathaBean>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val horizontalDecoration = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        )
        val horizontalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider)
        horizontalDecoration.setDrawable(horizontalDivider!!)
        binding.recyclerView.addItemDecoration(horizontalDecoration)
        title = title
        val adapter = KathaAdapter(this, arrayList)
        binding.recyclerView.adapter = adapter
    }
}