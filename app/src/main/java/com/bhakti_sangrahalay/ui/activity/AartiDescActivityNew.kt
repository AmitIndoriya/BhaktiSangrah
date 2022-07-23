package com.bhakti_sangrahalay.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.ViewPagerFragmentAdapter
import com.bhakti_sangrahalay.app.MyApp
import com.bhakti_sangrahalay.fragment.AartiDescFragmentNew
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.viewmodel.AartiDescActivityViewModel
import com.bumptech.glide.Glide
import java.util.*

public class AartiDescActivityNew : BaseActivity() {
    lateinit var viewModel: AartiDescActivityViewModel
    lateinit var viewPager: ViewPager
    lateinit var idList: IntArray
    var fragmentArrayList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aarti_desc_activity)
        setTitle(resources.getString(R.string.aarti))
        val bgIV = findViewById<ImageView>(R.id.bg_iv)
        Glide.with(this).load(R.drawable.bg15).into(bgIV)
        attachViewModel()
    }

    override fun attachViewModel() {
        val viewModelProvider = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
        viewModel = viewModelProvider[AartiDescActivityViewModel::class.java]
        viewModel.resources = resources
        if (MyApp.applicationContext().dataHoler.aartiListSize == 0) {
            viewModel.readResourceFile()
        }
        // MyApp.applicationContext().dataHoler.hashMapMutableLiveData.observe(this, Observer { hashMap -> setUpViewPager(hashMap, viewModel.getImages()) })
        setUpViewPager(viewModel.getImages())
    }

    override fun setTypeface() {
        TODO("Not yet implemented")
    }


    fun setUpViewPager(imageList: ArrayList<Int>) {
        viewPager = findViewById(R.id.view_pager)
        var viewPagerFragmentAdapter = ViewPagerFragmentAdapter(supportFragmentManager, getFragList(imageList))
        viewPager.setAdapter(viewPagerFragmentAdapter)
        var position = 0
        if (intent != null) {
            position = intent.getIntExtra("fragNum", 0)
        }
        viewPager.setCurrentItem(position)
        viewPager.setOffscreenPageLimit(1)
    }

    fun getFragList(imageList: ArrayList<Int>): ArrayList<Fragment> {
        idList = viewModel.getIdList()!!
        if (idList != null) {
            for (i in 0..idList.size - 1) {
                fragmentArrayList.add(AartiDescFragmentNew.newInstance(idList[i], imageList.get(i)))
            }
        }
        return fragmentArrayList
    }

    fun goToNextFrag() {
        var currentPos = viewPager.currentItem
        if (currentPos < MyApp.applicationContext().dataHoler.aartiListSize) {
            viewPager.setCurrentItem(++currentPos)
        }

    }

    fun goToPrevFrag() {
        var currentPos = viewPager.currentItem
        if (currentPos > 0) {
            viewPager.setCurrentItem(--currentPos)
        }

    }
}