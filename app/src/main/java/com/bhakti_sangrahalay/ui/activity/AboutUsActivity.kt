package com.bhakti_sangrahalay.activity

import android.os.Bundle
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.ui.activity.BaseActivity

class AboutUsActivity : BaseActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        setTitle(resources.getString(R.string.about_us))
    }

    override fun attachViewModel() {
    }

    override fun setTypeface() {
    }
}