package com.bhakti_sangrahalay.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.ViewPagerFragmentAdapter
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.databinding.AartiDescActivityBinding
import com.bhakti_sangrahalay.fragment.AartiDescFragmentNew
import com.bhakti_sangrahalay.viewmodel.AartiDescActivityViewModel
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject

class AartiDescActivityNew : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var binding: AartiDescActivityBinding
    lateinit var viewModel: AartiDescActivityViewModel
    lateinit var viewPager: ViewPager
    lateinit var idList: IntArray
    private var fragmentArrayList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initFont()
        binding = AartiDescActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(resources.getString(R.string.aarti))
        attachViewModel()
    }

    override fun attachViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        )[AartiDescActivityViewModel::class.java]

        viewModel.resources = resources
        if (MyApp.applicationContext().dataHoler.aartiListSize == 0) {
            viewModel.readResourceFile()
        }
        setUpViewPager(viewModel.getImages())
    }

    override fun setTypeface() {
    }


    private fun setUpViewPager(imageList: ArrayList<Int>) {

        val viewPagerFragmentAdapter =
            ViewPagerFragmentAdapter(supportFragmentManager, getFragList(imageList))
        binding.viewPager.adapter = viewPagerFragmentAdapter
        var position = 0
        if (intent != null) {
            position = intent.getIntExtra("fragNum", 0)
        }
        binding.viewPager.currentItem = position
        binding.viewPager.offscreenPageLimit = 1
    }

    private fun getFragList(imageList: ArrayList<Int>): ArrayList<Fragment> {
        idList = viewModel.getIdList()!!
        for (i in idList.indices) {
            fragmentArrayList.add(AartiDescFragmentNew.newInstance(idList[i], imageList[i]))
        }
        return fragmentArrayList
    }

    fun goToNextFrag() {
        var currentPos = viewPager.currentItem
        if (currentPos < MyApp.applicationContext().dataHoler.aartiListSize) {
            viewPager.currentItem = ++currentPos
        }

    }

    fun goToPrevFrag() {
        var currentPos = viewPager.currentItem
        if (currentPos > 0) {
            viewPager.currentItem = --currentPos
        }

    }
}