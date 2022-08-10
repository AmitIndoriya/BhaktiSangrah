package com.bhakti_sangrahalay.ui.activitynew

import android.os.Bundle
import com.bhakti_sangrahalay.databinding.KathaDescActivityBinding
import com.bhakti_sangrahalay.model.KathaBean
import com.bhakti_sangrahalay.ui.activity.BaseActivity

class KathaDescActivity : BaseActivity() {
    lateinit var binding: KathaDescActivityBinding
    override fun attachViewModel() {
    }

    override fun setTypeface() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFont()
        binding = KathaDescActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
    }

    fun getIntentData() {
        val bundle = intent.extras
        if (bundle != null) {
            val kathaBean = bundle.getSerializable("katha") as KathaBean?
            binding.titleTv.setText(kathaBean!!.title)
            binding.descTv.setText(kathaBean!!.desc)
            setTitle(kathaBean!!.title)
        }
    }
}