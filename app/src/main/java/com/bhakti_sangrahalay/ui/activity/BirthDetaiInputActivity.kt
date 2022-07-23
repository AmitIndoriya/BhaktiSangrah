package com.bhakti_sangrahalay.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.databinding.ActBirthdetailInputLayoutBinding
import com.bhakti_sangrahalay.viewmodel.BirthDetaiInputActivityViewModel


class BirthDetaiInputActivity : BaseActivity(), AdapterView.OnItemSelectedListener,
    View.OnClickListener {
    lateinit var viewModel: BirthDetaiInputActivityViewModel
    private lateinit var binding: ActBirthdetailInputLayoutBinding
    override fun attachViewModel() {
        val viewModelProvider =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
        viewModel = viewModelProvider[BirthDetaiInputActivityViewModel::class.java]
    }

    override fun setTypeface() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActBirthdetailInputLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(resources.getString(R.string.birth_detail))
        setListener()
        setAyanamsaSpinner()
        setDstSpinner()

    }

    private fun setListener() {
        binding.calculateBtn.setOnClickListener(this)
        binding.ayanmashaSpinner.onItemSelectedListener = this
        binding.dstSpinner.onItemSelectedListener = this
    }

    private fun setAyanamsaSpinner() {
        val ayanmasha = arrayOf("N.C. Lahiri", "KP New", "KP Old", "Raman", "KP Khullar", "Syan")
        val adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, ayanmasha)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.ayanmashaSpinner.adapter = adapter
    }

    private fun setDstSpinner() {
        val dst = arrayOf("0", "1", "2")
        val adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, dst)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.dstSpinner.adapter = adapter
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.calculate_btn -> {
                startActivity(Intent(this, KundliOutputActivity::class.java))
            }
        }
    }

}