package com.bhakti_sangrahalay.ui.activitynew

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.adapter.DescViewPagerAdapter
import com.bhakti_sangrahalay.databinding.SunderKaandActLayoutBinding
import com.bhakti_sangrahalay.model.SunderKaandBean
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.bhakti_sangrahalay.ui.fragment.SunderKandFragment
import com.bhakti_sangrahalay.ui.fragment.SunderKandHome
import com.bhakti_sangrahalay.util.Parser
import com.bhakti_sangrahalay.util.Utility

class SunderKandActivity : BaseActivity(), View.OnClickListener {
    lateinit var binding: SunderKaandActLayoutBinding
    private var totalPage = 0
    private var setCurrentItem: Int = 0
    override fun attachViewModel() {
    }

    override fun setTypeface() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFont()
        binding = SunderKaandActLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(resources.getString(R.string.Sunder_kand))
        setListener()
        setUpViewPager()
    }

    private fun setListener() {
        binding.nextBtn.setOnClickListener(this)
        binding.preBtn.setOnClickListener(this)
        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.pageCounter.text = (position + 1).toString() + "/" + totalPage
                setCurrentItem = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun setUpViewPager() {
        val sunderKaandBean: SunderKaandBean = Parser.sunderKandParser(
            this,
            resources,
            Utility.readFromFile(resources, R.raw.sunder_kand)
        )
        val pagerAdapter =
            DescViewPagerAdapter(this, supportFragmentManager, getFragmentList(sunderKaandBean))
        binding.viewPager.adapter = pagerAdapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.next_btn -> binding.viewPager.currentItem = ++setCurrentItem
            R.id.pre_btn -> binding.viewPager.currentItem = --setCurrentItem
        }
    }

    private fun getFragmentList(sunderKaandBean: SunderKaandBean): ArrayList<Fragment> {
        val arrayList = ArrayList<Fragment>()
        arrayList.add(SunderKandHome.newInstance())
        var sunderKandFragment: SunderKandFragment
        totalPage = sunderKaandBean.sunderKandArrayArrayList.size + 1
        for (i in sunderKaandBean.sunderKandArrayArrayList.indices) {
            sunderKandFragment =
                SunderKandFragment.newInstance(sunderKaandBean.sunderKandArrayArrayList[i])
            arrayList.add(sunderKandFragment)
        }
        return arrayList
    }
}