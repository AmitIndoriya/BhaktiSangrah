package com.bhakti_sangrahalay.ui.activity

import android.R
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    var regularTypeface: Typeface? = null
    var boldTypeface: Typeface? = null
    var semiBoldTypeface: Typeface? = null
    var mediumTypeface: Typeface? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState, persistentState)
        attachViewModel()
        initFont()
    }

    abstract fun attachViewModel()
    abstract fun setTypeface()


    open fun initFont() {
        regularTypeface = Typeface.createFromAsset(assets, "fonts/Laila-Regular.ttf")
        boldTypeface = Typeface.createFromAsset(assets, "fonts/Laila-Bold.ttf")
        semiBoldTypeface = Typeface.createFromAsset(assets, "fonts/Laila-SemiBold.ttf")
        mediumTypeface = Typeface.createFromAsset(assets, "fonts/Laila-Medium.ttf")
    }

    open fun setTitle(title: String) {
        val tv = TextView(applicationContext)
        val lp = RelativeLayout.LayoutParams(
            ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        tv.layoutParams = lp
        tv.text = title
        tv.textSize = 20f
        tv.setTextColor(Color.parseColor("#FFFFFF"))
        tv.typeface = boldTypeface
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.customView = tv
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setStatusBarColor(color: Int) {
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.setStatusBarColor(this.resources.getColor(color))
    }
}