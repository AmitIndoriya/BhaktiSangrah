package com.bhakti_sangrahalay.ui.activitynew

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.databinding.ActivityVartKathaHomeBinding
import com.bhakti_sangrahalay.ui.activity.BaseActivity

class VartKathaHomeActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: ActivityVartKathaHomeBinding
    override fun attachViewModel() {

    }

    override fun setTypeface() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFont()
        binding = ActivityVartKathaHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListener()
        setTitle(resources.getString(R.string.vart_kathayen1))
    }

    private fun setClickListener() {
        binding.cardview1.setOnClickListener(this)
        binding.cardview2.setOnClickListener(this)
        binding.cardview3.setOnClickListener(this)
        binding.cardview4.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val bundle = Bundle()
        when (v.id) {
            R.id.cardview1 -> {
                bundle.putInt("resId", R.raw.saptahik_vrat_list)
                bundle.putString("title", resources.getString(R.string.saptahik_vart_katha))
            }
            R.id.cardview2 -> {
                bundle.putInt("resId", R.raw.vishisht_vrat_katha_list)
                bundle.putString("title", resources.getString(R.string.vishisht_vart_katha))
            }
            R.id.cardview3 -> {
                bundle.putInt("resId", R.raw.ekadashi_vrat_list)
                bundle.putString("title", resources.getString(R.string.ekadashi_vart_katha))
            }
            R.id.cardview4 -> {
                bundle.putInt("resId", R.raw.navratri_katha_list)
                bundle.putString("title", resources.getString(R.string.navratri_vart_katha))
            }
        }
        val intent = Intent(this@VartKathaHomeActivity, KathaActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}